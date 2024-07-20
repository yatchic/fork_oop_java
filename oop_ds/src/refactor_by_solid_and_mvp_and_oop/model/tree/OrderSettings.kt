package refactor_by_solid_and_mvp_and_oop.model.tree

class OrderSettings:IOrderSettings {
    private var sortType: Boolean = true

  override  fun getSortType(): Boolean {
        return this.sortType
    }

    override fun setSortType(sortType: Boolean) {
        this.sortType = sortType
    }
}