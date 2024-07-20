package refactor_by_solid_and_mvp_and_oop.model.tree

interface ITree {
    fun add(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any)
    fun iterator(): Iterator<MutableList<*>>
    fun getA(): MutableList<*>
    fun getB(): MutableList<*>
    fun getC(): MutableList<*>
    fun getD(): MutableList<*>
    fun getE(): MutableList<*>
    fun getF(): MutableList<*>
}
