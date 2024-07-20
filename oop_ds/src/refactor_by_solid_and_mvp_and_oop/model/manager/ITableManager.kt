package refactor_by_solid_and_mvp_and_oop.model.manager

import refactor_by_solid_and_mvp_and_oop.model.tree.Tree

interface ITableManager {
    fun addTable(name: String, tree: Tree<*, *, *, *, *, *>)
    fun getTable(name: String): Tree<*, *, *, *, *, *>?
    fun removeTable(name: String)
    fun listTables(): Set<String>
    fun clearTables()
    fun addAllTables(other: TableManager)
    fun getTables(): MutableMap<String, Tree<*, *, *, *, *, *>>


 }