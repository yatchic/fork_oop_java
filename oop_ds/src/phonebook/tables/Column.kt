package phonebook.tables

import java.util.*

open class Column<T : Any> {
    private val columns = TreeMap<String, MutableList<MutableList<T>>>()

    fun createColumn(nameColumn: String) {
        if (columns.containsKey(nameColumn)) {
            println("колонка '$nameColumn' создалась.")
        } else {
            columns[nameColumn] = mutableListOf()
        }
    }

    fun addRow(nameColumn: String, vararg data: T) {
        val column = columns[nameColumn]
        if (column != null) {
            column.add(data.toMutableList())
        } else {
            println("такой колонки '$nameColumn' нет.")
        }
    }

    fun getColumn(nameColumn: String): MutableList<MutableList<T>>? {
        return columns[nameColumn]
    }

    fun getRow(nameColumn: String, rowIndex: Int): MutableList<T>? {
        val column = columns[nameColumn]
        return if (column != null && rowIndex in column.indices) {
            column[rowIndex]
        } else {
            null
        }
    }

    fun listColumns(): Array<String> {
        return columns.keys.toTypedArray()
    }
}