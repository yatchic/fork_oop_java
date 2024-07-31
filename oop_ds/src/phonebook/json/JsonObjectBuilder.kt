package phonebook.json

@JsonContext
class JsonObjectBuilder {
    private val map = mutableMapOf<String, Any>()

    infix fun String.to(value: Any) {
        map[this] = value
    }

    fun build(): Map<String, Any> = map
}