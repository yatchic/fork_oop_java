package refactor_by_solid_and_mvp_and_oop.model.manager

import refactor_by_solid_and_mvp_and_oop.model.tree.Tree


class TableManager : ITableManager {
    private val tables = mutableMapOf<String, Tree<*, *, *, *, *, *>>()

    override fun getTables(): MutableMap<String, Tree<*, *, *, *, *, *>> {
        return tables
    }

    override fun addTable(name: String, tree: Tree<*, *, *, *, *, *>) {
        tables[name] = tree
    }



    override fun getTable(name: String): Tree<*, *, *, *, *, *>? {
        return tables[name]
    }

    override fun removeTable(name: String) {
        tables.remove(name)
    }

    override fun listTables(): Set<String> {
        return tables.keys
    }

    override fun clearTables() {
        tables.clear()
    }

    override fun addAllTables(other: TableManager) {
        tables.putAll(other.tables)
    }
}
