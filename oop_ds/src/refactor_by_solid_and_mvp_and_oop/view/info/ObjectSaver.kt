package refactor_by_solid_and_mvp_and_oop.view.info

class ObjectSaver:IObjectSaver {
    private val dataStore = mutableMapOf<String, Any>()



    override fun getObject(key: String): Any? {
        return dataStore[key]
    }
}
