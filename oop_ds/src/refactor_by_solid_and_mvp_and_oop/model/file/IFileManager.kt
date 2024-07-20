package refactor_by_solid_and_mvp_and_oop.model.file

interface IFileManager {
    fun saveToFile(fileName: String,tableName:String)
    fun readFromFile(fileName: String)
    fun printFromFile(fileName: String)
}