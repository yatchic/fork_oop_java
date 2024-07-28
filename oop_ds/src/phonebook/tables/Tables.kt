package phonebook.tables

import java.util.*

class Tables<T : Any> : Column<T>() {

    private val tables = TreeMap<String, TreeMap<String, MutableList<MutableList<T>>>>()

    fun createTable(tableName: String) {
        if (tables.containsKey(tableName)) {
            println("такая таблица'$tableName'уже есть.")
        } else {
            tables[tableName] = TreeMap()
            println("таблица'$tableName'создалась.")
        }
    }

    fun removeTable(tableName: String) {
        if (tables.containsKey(tableName)) {
            tables.remove(tableName)
            println("таблица'$tableName'удалена.")
        } else {
            println("такой таблицы'$tableName'нет.")
        }
    }

      fun createColumn(tableName: String, columnName: String) {
        val table = tables[tableName]
        if (table != null) {
            if (table.containsKey(columnName)) {
                println("такая колонка'$columnName'уже есть в таблице'$tableName'.")
            } else {
                table[columnName] = mutableListOf()
                println("колонка'$columnName'создалась в таблице'$tableName'.")
            }
        } else {
            println("такой таблицы'$tableName'нет.")
        }
    }

    fun removeColumn(tableName: String, columnName: String) {
        val table = tables[tableName]
        if (table != null) {
            if (table.containsKey(columnName)) {
                table.remove(columnName)
                println("колонка'$columnName'удалена из таблицы'$tableName'.")
            } else {
                println("такой колонки'$columnName'нет в таблице'$tableName'.")
            }
        } else {
            println("такой таблицы'$tableName'нет.")
        }
    }

    fun addRow(tableName: String, columnName: String, vararg data: T) {
        val table = tables[tableName]
        if (table != null) {
            val column = table[columnName]
            if (column != null) {
                column.add(data.toMutableList())
            } else {
                println("такой колонки'$columnName'нет в таблице'$tableName'.")
            }
        } else {
            println("такой таблицы'$tableName'нет.")
        }
    }

    fun deleteRowByValue(tableName: String, value: T) {
        val table = tables[tableName]
        if (table != null) {
            val indices = mutableSetOf<Int>()


            for ((_, column) in table) {
                for ((index, row) in column.withIndex()) {
                    if (row.contains(value)) {
                        indices.add(index)
                    }
                }
            }


            for ((_, column) in table) {
                val iterator = column.iterator()
                var currentIndex = 0
                while (iterator.hasNext()) {
                    iterator.next()
                    if (currentIndex in indices) {
                        iterator.remove()
                    }
                    currentIndex++
                }
            }

            println("строки со значением'$value'удалены из таблицы'$tableName'.")
        } else {
            println("такой таблицы'$tableName'нет.")
        }
    }


    fun getTable(tableName: String): TreeMap<String, MutableList<MutableList<T>>>? {
        return tables[tableName]
    }

    fun getColumn(tableName: String, columnName: String): MutableList<MutableList<T>>? {
        val table = tables[tableName]
        return table?.get(columnName)
    }

    fun getRow(tableName: String, columnName: String, rowIndex: Int): MutableList<T>? {
        val table = tables[tableName]
        val column = table?.get(columnName)
        return if (column != null && rowIndex in column.indices) {
            column[rowIndex]
        } else {
            null
        }
    }

    fun listTables(): Array<String> {
        return tables.keys.toTypedArray()
    }

    fun listColumns(tableName: String): Array<String>? {
        return tables[tableName]?.keys?.toTypedArray()
    }

    fun getRowsByValue(tableName: String, columnName: String, value: T): MutableList<MutableList<T>>? {
        val table = tables[tableName]
        if (table != null) {
            val result = mutableListOf<MutableList<T>>()
            val indices = mutableSetOf<Int>()


            for ((colName, column) in table) {
                for ((index, row) in column.withIndex()) {
                    if (row.contains(value)) {
                        indices.add(index)
                    }
                }
            }


            val targetColumn = table[columnName]
            if (targetColumn != null) {
                for (index in indices) {
                    targetColumn.getOrNull(index)?.let { result.add(it) }
                }
            } else {
                println("такай колонки'$columnName'в таблице'$tableName'нет.")
            }

            return result
        } else {
            println("такой таблицы'$tableName'нет.")
        }
        return null
    }

}
