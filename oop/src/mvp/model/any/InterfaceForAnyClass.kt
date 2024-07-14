package mvp.model.any

import java.util.*

interface InterfaceForAnyClass {
    fun findTextB(text: String?, pattern: String?): Boolean
    fun findText(text: String?, pattern: String?): String?
    fun findTextAll(text: String?, pattern: String?): Array<String>
    fun joinToString(arr:Array<String>, separator:String):String
    fun joinToString(separator:String):String

    fun joinToString(link: LinkedList<Int>, separator:String):String
}