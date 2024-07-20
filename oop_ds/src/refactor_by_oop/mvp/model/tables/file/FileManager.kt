package refactor_by_oop.mvp.model.tables.file

import refactor_by_oop.mvp.model.tables.manager.TableManager
import refactor_by_oop.mvp.view.info.Info
import java.io.*

class FileManager  (
    private val tableManager: TableManager,
    private val info: Info
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
                restoredManager.listTables().forEach { info.infoShow(it) }
                restoredManager.listTables().forEach {
                    val tree = restoredManager.getTable(it)
                    tree?.forEach { }
                }
            }
        }
    }
}