package refactor_by_oop.mvp.model.tables.order

interface IOrder {
    fun sortColumnAscending(column: Int)
    fun sortColumnDescending(column: Int)


}