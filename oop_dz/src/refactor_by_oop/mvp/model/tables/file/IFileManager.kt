package refactor_by_oop.mvp.model.tables.file

interface IFileManager {
    fun saveToFile(fileName: String,tableName:String)
    fun readFromFile(fileName: String)
    fun printFromFile(fileName: String)
}