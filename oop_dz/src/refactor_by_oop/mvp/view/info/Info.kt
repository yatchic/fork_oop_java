package refactor_by_oop.mvp.view.info

class Info:IInfo {
    private var `object`: Any? = null



    override fun infoShow(`object`: Any?) {
        this.`object` = `object`
    }

    override fun toString(): String {
        if (`object` is String) {
            println(`object`)
            return ""
        } else if (`object` is Number) {
            println(`object`)
            return ""
        } else if (`object` is Collection<*>) {
            for (item in `object` as Collection<*>) {
                System.out.println(item)
            }
            return ""
        } else {
            println("Объект в неизвестном виде=$`object`")
            return ""
        }
    }
}