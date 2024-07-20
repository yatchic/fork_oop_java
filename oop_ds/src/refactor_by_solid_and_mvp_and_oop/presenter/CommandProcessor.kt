package refactor_by_solid_and_mvp_and_oop.presenter


import refactor_by_solid_and_mvp_and_oop.model.file.IFileManager
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.IMockTextUtils
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.IRegexUtils
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.ITextUtils
import refactor_by_solid_and_mvp_and_oop.model.manager.ITableManager
import refactor_by_solid_and_mvp_and_oop.model.tree.Tree
import view.errors.IErrorReporter

class CommandProcessor(
    private val tableManager: ITableManager,
    private val fileManager: IFileManager,
    private val textUtils: ITextUtils,
    private val regexUtils: IRegexUtils,
    private val errorReporter: IErrorReporter,
    private val mockTextUtils: IMockTextUtils
):ICommandProcessor {

  override  fun handleTableCreation(line: String) {
        val tableInfo = textUtils.tokenize(line)
        val tableName = tableInfo[0]
        val typeList = tableInfo.subList(1, 7)

        if (typeList.all { it in listOf("Int", "String", "Double", "Boolean") }) {
            val tree = createTree(typeList)
            if (tree != null) {
                tableManager.addTable(tableName, tree)
                mockTextUtils.debug("таблица \"$tableName\" создана")
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

    override fun handleAddEntry(line: String) {
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
            mockTextUtils.debug("значения добавлены в таблицу $tableName: $parsedValues")
        }
    }

    override fun handleDeleteTable(line: String) {
        val delete = textUtils.tokenize(line)
        val tableName = delete[1]

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            tableManager.removeTable(tableName)
            mockTextUtils.debug("удалена таблица: $tableName")
        }
    }

    override fun handleSaveTable(line: String) {
        val save = textUtils.tokenize(line)
        val tableName = save[1]
        val path = save[2].replace("|/", "\\")

        val tree = tableManager.getTable(tableName)
        if (tree == null) {
            errorReporter.showError("таблица не найдена: $tableName")
        } else {
            fileManager.saveToFile(path, tableName)
            mockTextUtils.debug("таблица $tableName сохранена в \"$path\"")
        }
    }

    override fun handleShowTable(line: String) {
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
            mockTextUtils.debug("  $tableName отсортирована $order")
        }
    }

    override fun handleShowColumn(line: String) {
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
            mockTextUtils.debug("колонок $columnIndex в таблице $tableName ")
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