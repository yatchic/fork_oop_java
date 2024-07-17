package refactor_by_oop.mvp.model.tables.table

import java.io.Serializable

class Tree<A : Comparable<A>, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>, E : Comparable<E>, F : Comparable<F>> : Serializable,
    Iterable<MutableList<*>>,Itree  {
    private var sortType: Boolean = true
    private val a = mutableListOf<A>()
    private val b = mutableListOf<B>()
    private val c = mutableListOf<C>()
    private val d = mutableListOf<D>()
    private val e = mutableListOf<E>()
    private val f = mutableListOf<F>()

   override fun add(a: Any, b: Any, c: Any, d: Any, e: Any, f: Any) {
        this.a.add(a as A)
        this.b.add(b as B)
        this.c.add(c as C)
        this.d.add(d as D)
        this.e.add(e as E)
        this.f.add(f as F)
    }

    private fun sortList(list: MutableList<*>) {
        if (sortType) {
            (list as MutableList<Comparable<Any>>).sort()
        } else {
            (list as MutableList<Comparable<Any>>).sortDescending()
        }
    }

    private inner class TreeIterator : Iterator<MutableList<*>> {
        private val lists = listOf(a, b, c, d, e, f)
        private var index = 0

        init {
            lists.forEach { sortList(it) }
        }

        override fun hasNext(): Boolean {
            return index < lists.size
        }

        override fun next(): MutableList<*> {
            return lists[index++]
        }
    }

    override fun iterator(): Iterator<MutableList<*>> {
        return TreeIterator()
    }

    fun getSortType(): Boolean {
        return this.sortType
    }

    fun setSortType(sortType: Boolean) {
        this.sortType = sortType
    }

    fun getA(): MutableList<A> {
        return this.a
    }

    fun getB(): MutableList<B> {
        return this.b
    }

    fun getC(): MutableList<C> {
        return this.c
    }

    fun getD(): MutableList<D> {
        return this.d
    }

    fun getE(): MutableList<E> {
        return this.e
    }

    fun getF(): MutableList<F> {
        return this.f
    }
}