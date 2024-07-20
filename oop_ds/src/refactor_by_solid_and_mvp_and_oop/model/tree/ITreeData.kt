package refactor_by_solid_and_mvp_and_oop.model.tree

  interface ITreeData<A : Comparable<A>, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>, E : Comparable<E>, F : Comparable<F>> {
     val a: MutableList<A>
      val b: MutableList<B>
      val c: MutableList<C>
      val d: MutableList<D>
      val e: MutableList<E>
      val f: MutableList<F>
      fun add(a: A, b: B, c: C, d: D, e: E, f: F)
}