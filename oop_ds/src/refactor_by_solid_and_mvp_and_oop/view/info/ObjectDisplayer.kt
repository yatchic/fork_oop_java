package refactor_by_solid_and_mvp_and_oop.view.info

class ObjectDisplayer(private val objectSaver: IObjectSaver):IObjectDisplayer {

  override  fun displayObject(key: String) {
        val obj = objectSaver.getObject(key)
        when (obj) {
            is Int -> println("Integer: $obj")
            is String -> println("String: $obj")
            is Double -> println("Double: $obj")
            is Boolean -> println("Boolean: $obj")
            else -> println("Unknown type: $obj")
        }
    }
}