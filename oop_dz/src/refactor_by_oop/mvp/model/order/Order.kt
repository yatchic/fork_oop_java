package refactor_by_oop.mvp.model.order

import refactor_by_oop.mvp.model.tables.table.Tree

class Order<A : Comparable<A>, B : Comparable<B>, C : Comparable<C>, D : Comparable<D>, E : Comparable<E>, F : Comparable<F>>(private val tree: Tree<A, B, C, D, E, F>) : IOrder {

 override fun sortColumnAscending(column: Int) {
  when (column) {
   1 -> tree.getA().sort()
   2 -> tree.getB().sort()
   3 -> tree.getC().sort()
   4 -> tree.getD().sort()
   5 -> tree.getE().sort()
   6 -> tree.getF().sort()
   else -> throw IllegalArgumentException("Invalid column number")
  }
 }

 override fun sortColumnDescending(column: Int) {
  when (column) {
   1 -> tree.getA().sortDescending()
   2 -> tree.getB().sortDescending()
   3 -> tree.getC().sortDescending()
   4 -> tree.getD().sortDescending()
   5 -> tree.getE().sortDescending()
   6 -> tree.getF().sortDescending()
   else -> throw IllegalArgumentException("Invalid column number")
  }
 }
}
