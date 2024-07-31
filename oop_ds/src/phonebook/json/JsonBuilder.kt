package phonebook.json

class JsonBuilder {
    private val map = mutableMapOf<String, Any?>()

    fun add(key: String, value: Any?) {
        map[key] = value
    }

    fun build(): String = buildJson(map, 0)

    private fun buildJson(value: Any?, indent: Int): String {
        return when (value) {
            is String -> "\"$value\""
            is Number, is Boolean -> value.toString()
            is List<*> -> buildJsonArray(value, indent)
            is Map<*, *> -> buildJsonObject(value, indent)
            else -> "null"
        }
    }

    private fun buildJsonObject(map: Map<*, *>, indent: Int): String {
        val entries = map.map { (key, value) ->
            val formattedKey = "\"${key.toString()}\""
            val formattedValue = buildJson(value, indent + 1)
            "$formattedKey: $formattedValue"
        }
        val indentStr = "  ".repeat(indent)
        return "{\n${entries.joinToString(",\n") { "  $indentStr$it" }}\n$indentStr}"
    }

    private fun buildJsonArray(list: List<*>, indent: Int): String {
        val items = list.map { buildJson(it, indent + 1) }
        val indentStr = "  ".repeat(indent)
        return "[\n${items.joinToString(",\n") { "  $indentStr$it" }}\n$indentStr]"
    }
}