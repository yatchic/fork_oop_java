package refactor_by_solid_and_mvp_and_oop.model.file


import refactor_by_solid_and_mvp_and_oop.model.manager.ITableManager
import refactor_by_solid_and_mvp_and_oop.model.manager.TableManager
import refactor_by_solid_and_mvp_and_oop.view.info.IObjectDisplayer
import java.io.*

class FileManager  (
    private val tableManager: ITableManager,
    private val objectDisplayer: IObjectDisplayer
) : IFileManager, Serializable {

    override fun saveToFile(fileName: String, tableName: String) {
        ObjectOutputStream(FileOutputStream(fileName)).use { objectOutputStream ->
            objectOutputStream.writeObject(tableManager.getTables())
        }
    }

    override fun readFromFile(fileName: String) {
        ObjectInputStream(FileInputStream(fileName)).use { objectInputStream ->
            val restoredManager = objectInputStream.readObject() as? TableManager
            restoredManager?.let {
                tableManager.clearTables()
                tableManager.addAllTables(restoredManager)
            }
        }
    }

  override  fun printFromFile(fileName: String) {
        ObjectInputStream(FileInputStream(fileName)).use { objectInputStream ->
            val restoredManager = objectInputStream.readObject() as? TableManager
            restoredManager?.let {
                restoredManager.listTables().forEach { objectDisplayer.displayObject(it) }
                restoredManager.listTables().forEach {
                    val tree = restoredManager.getTable(it)
                    tree?.forEach { }
                }
            }
        }
    }
}