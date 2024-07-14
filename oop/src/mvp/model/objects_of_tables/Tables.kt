
/*
*
*              На джаве нет времени писать я с ней несильно дружу.
*              Здесь общие методы для создания TreeMap tree, удаления
*              и обращения к tree, также tree сохраняется временно в
*              другую TreeMap objects.
*
* */



package mvp.model.objects_of_tables

import mvp.model.tree.Tree
import java.lang.reflect.ParameterizedType

import java.util.*

class Tables {
    private val objects: TreeMap<String, Tree<*, *, *, *, *, *>> = TreeMap()

    fun getTreeByName(tableName: String): Tree<*, *, *, *, *, *>? {
        return objects[tableName]
    }

    fun getNameByTree(tree: Any): String? {
        for (entry in objects.entries) {
            if (entry.value == tree) {
                return entry.key
            }
        }
        return null
    }

    private fun removeTable(tree: Tree<*, *, *, *, *, *>) {
        val tableName = getNameByTree(tree)
        if (tableName != null) {
            objects.remove(tableName)
        }
    }

    fun remove(o: Any) {
        if (o is String) {
            objects.remove(o)
            val tree = getTreeByName(o)
            tree?.let { removeTable(it) }
        } else if (o is Tree<*, *, *, *, *, *>) {
            val nameKey = getNameByTree(o)
            nameKey?.let { objects.remove(it) }
        }
    }

    fun add(tree: Tree<*, *, *, *, *, *>, tableName: String) {
        objects[tableName] = tree
    }

    fun getTypes(tree: Tree<*, *, *, *, *, *>): String {
        val type = tree::class.java.genericSuperclass as? ParameterizedType
            ?: tree::class.java.genericInterfaces.find { it is ParameterizedType } as? ParameterizedType

        val typeArgs = type?.actualTypeArguments ?: return "Unknown Types"
        return typeArgs.joinToString(", ") { it.typeName.substringAfterLast('.') }
    }
}


