package refactor_by_solid_and_mvp_and_oop.presenter

interface ICommandProcessor {
    fun handleTableCreation(line: String)
    fun handleAddEntry(line: String)
    fun handleDeleteTable(line: String)
    fun handleSaveTable(line: String)
    fun handleShowTable(line: String)
    fun handleShowColumn(line: String)







}