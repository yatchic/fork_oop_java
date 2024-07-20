package refactor_by_solid_and_mvp_and_oop.model.tree

import java.io.Serializable

class TreeData<A : Comparable<A>, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>, E : Comparable<E>, F : Comparable<F>> : Serializable,ITreeData<A, B, C, D, E, F> {
    override val a = mutableListOf<A>()
    override val b = mutableListOf<B>()
    override val c = mutableListOf<C>()
    override val d = mutableListOf<D>()
    override val e = mutableListOf<E>()
    override val f = mutableListOf<F>()

 override fun add(a: A, b: B, c: C, d: D, e: E, f: F) {
        this.a.add(a)
        this.b.add(b)
        this.c.add(c)
        this.d.add(d)
        this.e.add(e)
        this.f.add(f)
    }
}