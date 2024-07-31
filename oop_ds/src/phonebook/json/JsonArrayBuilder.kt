package phonebook.json

@JsonContext
class JsonArrayBuilder {
    private val list = mutableListOf<Any>()

    operator fun Any.unaryPlus() {
        list.add(this)
    }

    fun build(): List<Any> = list
}