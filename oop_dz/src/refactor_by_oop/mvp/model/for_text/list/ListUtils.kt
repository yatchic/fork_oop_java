package refactor_by_oop.mvp.model.for_text.list

import java.util.*

class ListUtils: IListUtils {
    val mutableList= mutableListOf<String>()



    /**
     * Добавляет строковое значение в mutableList.
     */
    override  fun add( value:String){
        mutableList.add(value)
    }

    /**
     *   Удаляет все элементы из mutableList.
     */
    override   fun clear( value:String){
        mutableList.clear()
    }

    override   fun<E> joinToString(list:List<E>, separator:String): String {
        return list.joinToString(separator)

    }
    override   fun<E> joinToString(collection:Collection<E>, separator:String): String {
        return collection.joinToString(separator)

    }

    /**
     *   Все элементы mutableList сохраняет в одну строковую переменную через заданный разделитель.
     */
    override  fun joinToString(separator:String):String{
        return mutableList.joinToString(separator)

    }
    /**
     *   Все элементы массива сохраняет в одну строковую переменную через заданный разделитель.
     */
    override  fun  joinToString(arr:Array<String>, separator:String):String{
        return arr.joinToString(separator)

    }

    /**
     *   Все элементы LinkedList сохраняет в одну строковую переменную через заданный разделитель.
     */
    override  fun joinToString(link: LinkedList<Int>, separator:String):String{
        return link.joinToString(separator)

    }

}