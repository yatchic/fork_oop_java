package refactor_by_solid_and_mvp_and_oop.model.tree

interface ITreeSorter {
    fun <T : Comparable<T>> sortList(list: MutableList<T>, ascending: Boolean)
}