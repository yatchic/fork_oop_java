package refactor_by_solid_and_mvp_and_oop.model.tree

class TreeIterator(
    private val data: ITreeData<*, *, *, *, *, *>,
    private val sorter: ITreeSorter,
    private val orderSettings: IOrderSettings
) : ITreeIterator,Iterator<MutableList<*>> {
    private val lists = listOf(data.a, data.b, data.c, data.d, data.e, data.f)
    private var index = 0

    init {
        lists.forEach { sorter.sortList(it as MutableList<Comparable<Any>>, orderSettings.getSortType()) }
    }

    override fun hasNext(): Boolean {
        return index < lists.size
    }

    override fun next(): MutableList<*> {
        return lists[index++]
    }
}
