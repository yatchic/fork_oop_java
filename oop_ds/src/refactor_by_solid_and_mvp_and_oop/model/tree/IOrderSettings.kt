package refactor_by_solid_and_mvp_and_oop.model.tree

interface IOrderSettings {
    fun getSortType(): Boolean
    fun setSortType(sortType: Boolean)
}