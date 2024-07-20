package refactor_by_solid_and_mvp_and_oop.model.tree


import java.io.Serializable

class Tree<A : Comparable<A>, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>, E : Comparable<E>, F : Comparable<F>> : Serializable, Iterable<MutableList<*>>,
    ITree {
    private val data = TreeData<A, B, C, D, E, F>()
    private val sorter = TreeSorter()
    private val orderSettings = OrderSettings()

    override  fun add(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any) {
        data.add(a as A, b as B, c as C, d as D, e as E, f as F)
    }

    override fun iterator(): Iterator<MutableList<*>> {
        return TreeIterator(data, sorter, orderSettings)
    }


      fun setSortType(sortType: Boolean) {
        orderSettings.setSortType(sortType)
    }

    override fun getA(): MutableList<A> {
        return data.a
    }

    override fun getB(): MutableList<B> {
        return data.b
    }

    override  fun getC(): MutableList<C> {
        return data.c
    }

    override fun getD(): MutableList<D> {
        return data.d
    }

    override fun getE(): MutableList<E> {
        return data.e
    }

    override fun getF(): MutableList<F> {
        return data.f
    }
}