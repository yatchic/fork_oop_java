package refactor_by_oop.mvp.presenter.service

import refactor_by_oop.mvp.model.for_text.text.IRegexUtils
import refactor_by_oop.mvp.model.for_text.text.ITextUtils
import refactor_by_oop.mvp.model.tables.file.FileManager
import refactor_by_oop.mvp.model.tables.manager.ITableManager
import refactor_by_oop.mvp.model.tables.table.Tree
import refactor_by_oop.mvp.view.read.IInputReader
import refactor_by_oop.mvp.view.errors.IErrorReporter

class Service(
    private val inputReader: IInputReader,
    private val regexUtils: IRegexUtils,
    private val tableManager: ITableManager,
    private val fileManager: FileManager,
    private val textUtils: ITextUtils,
    private val errorReporter: IErrorReporter
) : IService {

    override fun processCommands() {
        val patternTableCreation = "[А-Яа-яA-Za-z]+\\s+(Int|String|Double|Boolean)\\s+(Int|String|Double|Boolean)\\s+(Int|String|Double|Boolean)\\s+(Int|String|Double|Boolean)\\s+(Int|String|Double|Boolean)\\s+(Int|String|Double|Boolean)"
        val patternAddEntry = "добавить\\s+[А-Яа-яA-Za-z]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s+[А-Яа-яA-Za-z\\d\\.]+\\s*"
        val patternDeleteTable = "удалить\\s+[А-Яа-яA-Za-z]+\\s*"
        val patternSaveTable = "сохранить\\s+[А-Яа-яA-Za-z]+\\s+.+\\s*"
        val patternShowTable = "показать\\s+[А-Яа-яA-Za-z]+\\s+(по_возрастанию|по_убыванию)\\s*"
        val patternShowColumn = "показать\\s+[А-Яа-яA-Za-z]+\\s+(1колонку|2колонку|3колонку|4колонку|5колонку|6колонку)\\s*"

        try {
            while (true) {
                val line = inputReader.readLine()
                if (line != null) {
                    when {
                        regexUtils.matchesPattern(line, patternTableCreation) -> handleTableCreation(line)
                        regexUtils.matchesPattern(line, patternAddEntry) -> handleAddEntry(line)
                        regexUtils.matchesPattern(line, patternDeleteTable) -> handleDeleteTable(line)
                        regexUtils.matchesPattern(line, patternSaveTable) -> handleSaveTable(line)
                        regexUtils.matchesPattern(line, patternShowTable) -> handleShowTable(line)
                        regexUtils.matchesPattern(line, patternShowColumn) -> handleShowColumn(line)
                        else -> errorReporter.showError("неизвестная команда: $line")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorReporter.showError("An error occurred while processing commands: ${e.message}")
        }
    }

    private fun handleTableCreation(line: String) {
        val tableInfo = textUtils.tokenize(line)
        val tableName = tableInfo[0]
        val typeList = tableInfo.subList(1, 7)

        if (typeList.all { it in listOf("Int", "String", "Double", "Boolean") }) {
            val tree = createTree(typeList)
            if (tree != null) {
                tableManager.addTable(tableName, tree)
                textUtils.debug("таблица \"$tableName\" создана")
            } else {
              errorReporter.showError("из-за ограниченности такой набор типов не допустимый: $typeList\n" +
                      "возможны варианты:\n" +
                      "Int String Double Int Double Boolean  \n" +
                      "String String String String Double String  \n" +
                      "Int Double Double Double Double Boolean  \n" +
                      "Int Double String String String String")
            }
        } else {
            errorReporter.showError("неверные типы в списке типов: $typeList")
        }
    }

    private fun handleAddEntry(line: String) {
        val added = textUtils.tokenize(line)
        val tableName = added[1]
        val values = added.subList(2, 8)

        val parsedValues = values.map { value ->
            when {
                regexUtils.matchesPattern(value, "^\\d+$") -> value.toInt()
                regexUtils.matchesPattern(value, "^\\d+\\.\\d+$") -> value.toDouble()
                regexUtils.matchesPattern(value, "^[А-Яа-яA-Za-z\\d\\.]+$") -> value
                regexUtils.matchesPattern(value, "^false|False|FALSE|true|True|TRUE$") -> value.toBoolean()
                else -> throw IllegalArgumentException("неизвестные типовые значения: $value")
            }
        }

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("Table not found: $tableName")
        } else {

            val anyValues = parsedValues.map { it as Any }
            tree.add(anyValues[0], anyValues[1], anyValues[2], anyValues[3], anyValues[4], anyValues[5])
            textUtils.debug("значения добавлены в таблицу $tableName: $parsedValues")
        }
    }

    private fun handleDeleteTable(line: String) {
        val delete = textUtils.tokenize(line)
        val tableName = delete[1]

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            tableManager.removeTable(tableName)
            textUtils.debug("удалена таблица: $tableName")
        }
    }

    private fun handleSaveTable(line: String) {
        val save = textUtils.tokenize(line)
        val tableName = save[1]
        val path = save[2].replace("|/", "\\")

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            fileManager.saveToFile(path, tableName)
            textUtils.debug("таблица $tableName сохранена в \"$path\"")
        }
    }

    private fun handleShowTable(line: String) {
        val show = textUtils.tokenize(line)
        val tableName = show[1]
        val order = when (show[2]) {
            "по_возрастанию" -> "ascending"
            "по_убыванию" -> "descending"
            else -> {
                errorReporter.showError("не верная сортировка: ${show[2]}")
                return
            }
        }

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            tree.setSortType(order == "ascending")
            tree.forEach { list -> list.forEach { println(it) } }
            textUtils.debug("  $tableName отсортирована $order")
        }
    }

    private fun handleShowColumn(line: String) {
        val showColumns = textUtils.tokenize(line)
        val tableName = showColumns[1]
        val column = showColumns[2]

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            val columnIndex = when (column) {
                "1колонку" -> 1
                "2колонку" -> 2
                "3колонку" -> 3
                "4колонку" -> 4
                "5колонку" -> 5
                "6колонку" -> 6
                else -> {
                    errorReporter.showError("такой колонки нет: $column")
                    return
                }
            }

            when (columnIndex) {
                1 -> tree.getA().forEach { println(it) }
                2 -> tree.getB().forEach { println(it) }
                3 -> tree.getC().forEach { println(it) }
                4 -> tree.getD().forEach { println(it) }
                5 -> tree.getE().forEach { println(it) }
                6 -> tree.getF().forEach { println(it) }
            }
            textUtils.debug("колонок $columnIndex в таблице $tableName ")
        }
    }

    private fun createTree(typeList: List<String>): Tree<*, *, *, *, *, *>? {
        return when (typeList) {
            listOf("Int", "String", "Double", "Int", "Double", "Boolean") -> Tree<Int, String, Double, Double, Double, Boolean>()
            listOf("String", "String", "String", "String", Double, "String") -> Tree<String, String, String, String, Double, String>()
            listOf("Int", "Double", "Double", "Double", "Double", "Boolean") -> Tree<Int, Double, Double, Double, Double, Boolean>()
            listOf("Int", "Double", "String", "String", "String", "String") -> Tree<Int, Double, String, String, String, String>()
            else -> null
        }
    }








}