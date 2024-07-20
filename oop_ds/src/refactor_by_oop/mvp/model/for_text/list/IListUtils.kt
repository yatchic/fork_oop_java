package refactor_by_oop.mvp.model.for_text.list

import java.util.*

interface IListUtils {
    fun add( value:String)
    fun clear( value:String)
    fun<E> joinToString(list:List<E>, separator:String): String
    fun<E> joinToString(collection:Collection<E>, separator:String): String
    fun joinToString(separator:String):String
    fun  joinToString(arr:Array<String>, separator:String):String
    fun joinToString(link: LinkedList<Int>, separator:String):String
}

