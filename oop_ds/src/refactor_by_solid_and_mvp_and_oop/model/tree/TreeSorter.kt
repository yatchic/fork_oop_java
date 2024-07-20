package refactor_by_solid_and_mvp_and_oop.model.tree

class TreeSorter:ITreeSorter {
   override fun <T : Comparable<T>> sortList(list: MutableList<T>, ascending: Boolean) {
        if (ascending) {
            list.sort()
        } else {
            list.sortDescending()
        }
    }
}