package refactor_by_solid_and_mvp_and_oop.presenter


import refactor_by_solid_and_mvp_and_oop.model.file.IFileManager
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.IRegexUtils
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.ITextUtils
import refactor_by_solid_and_mvp_and_oop.model.manager.ITableManager

import view.errors.IErrorReporter
import  view.read.IInputReader

class Service(
    private val inputReader: IInputReader,
    private val regexUtils: IRegexUtils,
     private val commandProcessor : ICommandProcessor,

    private val tableManager: ITableManager,
    private val fileManager: IFileManager,
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
                        regexUtils.matchesPattern(line, patternTableCreation) -> commandProcessor.handleTableCreation(line)
                        regexUtils.matchesPattern(line, patternAddEntry) -> commandProcessor.handleAddEntry(line)
                        regexUtils.matchesPattern(line, patternDeleteTable) -> commandProcessor.handleDeleteTable(line)
                        regexUtils.matchesPattern(line, patternSaveTable) -> commandProcessor.handleSaveTable(line)
                        regexUtils.matchesPattern(line, patternShowTable) -> commandProcessor.handleShowTable(line)
                        regexUtils.matchesPattern(line, patternShowColumn) -> commandProcessor.handleShowColumn(line)
                        else -> errorReporter.showError("неизвестная команда: $line")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorReporter.showError("An error occurred while processing commands: ${e.message}")
        }
    }
}