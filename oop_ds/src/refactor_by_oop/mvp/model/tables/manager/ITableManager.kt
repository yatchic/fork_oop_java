package refactor_by_oop.mvp.model.tables.manager

import refactor_by_oop.mvp.model.tables.table.Tree

interface ITableManager {
    fun addTable(name: String, tree: Tree<*, *, *, *, *, *>)
    fun getTable(name: String): Tree<*, *, *, *, *, *>?
    fun removeTable(name: String)
    fun listTables(): Set<String>
    fun clearTables()
    fun addAllTables(other: TableManager)
    fun getTables(): MutableMap<String, Tree<*, *, *, *, *, *>>


}