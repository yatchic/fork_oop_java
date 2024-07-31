package phonebook.json


class Json {

    fun obj(content: JsonObjectBuilder.() -> Unit): Map<String, Any> {
        val builder = JsonObjectBuilder()
        builder.content()
        return builder.build()
    }

    fun array(content: JsonArrayBuilder.() -> Unit): List<Any> {
        val builder = JsonArrayBuilder()
        builder.content()
        return builder.build()
    }
}