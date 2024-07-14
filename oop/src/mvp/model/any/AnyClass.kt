
/*
*             Котлиновский класс создал потому что есть готовые наработки с джавой сильно заморачиваться не собираюсь.
*
*
*
*
* */




package mvp.model.any

import java.util.*
import java.util.regex.Pattern

class AnyClass: InterfaceForAnyClass {
    var errors="";
    val mutableList= mutableListOf<String>()

fun deb(text:String){println("...$text")}

    fun tokens(input: String): List<String> {

        return input.split("\\s+".toRegex())
    }



    /**
     * String приводит к int.
     */
    fun stringToInteger(text: String): Int {
        return try {
            text.toInt()
        } catch (e: Exception) {
            System.out.println(e)
            return 0
        }
    }

    fun stringToDouble(text: String): Double {
        return try {
            text.toDouble()
        } catch (e: Exception) {
            System.out.println(e)
            return 0.0
        }
    }
    fun stringToBoolean(text: String): Boolean {
         var res=false
          try {
            if(findTextB(text,"false|False|FALSE")) {res=false}
              if(findTextB(text,"true|True|TRUE")) {res=true}
        } catch (e: Exception) {
            System.out.println(e)
            return false
        }
        return res
    }

    /**
     * Добавляет строковое значение в mutableList.
     */
    fun add( value:String){
        mutableList.add(value)
    }
    /**
     *   Удаляет все элементы из mutableList.
     */
    fun clear( value:String){
        mutableList.clear()
    }

    /**
     *   Удаляет повторяющиеся строковые значения, при запросе в HashMap по ключам или значению выбираются отношения с одинаковыми значениями
     *   повторяющиеся значения удаляются, остается только одно.
     */
    fun removeDuplicates(text: String): String {
        val lines = text.split("\n")
        val uniqueLines = LinkedHashSet(lines)
        return uniqueLines.joinToString("\n")
    }

    fun replaceTextAll(text: String?, pattern: String?, replacement: String?): String? {
        val errors=StringBuilder()
        var res=""
        try {
            val ptrn = Pattern.compile(pattern!!)
            val match = ptrn.matcher(text!!)
            res= match.replaceAll(replacement!!)
        }catch( e:IndexOutOfBoundsException){errors.append("IndexOutOfBoundsException    "+e)}
        catch( e:IllegalArgumentException){errors.append("IllegalArgumentException    "+e)}
        catch( e:IllegalStateException){errors.append("IllegalStateException   "+e)}
        catch( e:NullPointerException){errors.append("NullPointerException   "+e)}
        catch( e:ConcurrentModificationException){errors.append("ConcurrentModificationException    "+e)}

        return res
    }
    /**
     *   В строке ищет строковые паттерны регулярных выражений.
     */
    override  fun findTextAll(text: String?, pattern: String?): Array<String> {

        val errors=StringBuilder()
        val builder=StringBuilder()
        val arr = mutableListOf<String>()
        try{

            val builder = StringBuilder()
            val ptrn = Pattern.compile(pattern!!)
            val match = ptrn.matcher(text!!)
            while (match.find()) {
                arr.add(match.group())
            }


        } catch (e: IndexOutOfBoundsException) {
            errors.append("Выход за размер массива:"+ e)
        } catch (e: IllegalArgumentException) {
            errors.append("IllegalArgumentException    " + e)
        } catch (e: IllegalStateException) {
            errors.append("IllegalStateException   " + e)
        } catch (e: NullPointerException) {
            errors.append("NullPointerException   " + e)
        } catch (e: ConcurrentModificationException) {
            errors.append("ConcurrentModificationException    " + e)
        }catch (e: NumberFormatException) {
            errors.append("NumberFormatException - if the string is not a valid representation of a number    " + e)

        }
        return arr.toTypedArray()

    }


       fun<E> joinToString(list:List<E>, separator:String): String {
        return list.joinToString(separator)

    }
         fun<E> joinToString(collection:Collection<E>, separator:String): String {
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
    override  fun joinToString(link:LinkedList<Int>, separator:String):String{
        return link.joinToString(separator)

    }
    /**
     *   Возвращает истину или ложь был найден паттерн или нет.
     */
    override fun findTextB(text: String?, pattern: String?): Boolean
    {
        val errors=StringBuilder()
        var res=false
        try {
            val ptrn = Pattern.compile(pattern!!)
            val match = ptrn.matcher(text!!)
            res=match.find()

        }catch( e:IndexOutOfBoundsException){errors.append("IndexOutOfBoundsException    "+e)}
        catch( e:IllegalArgumentException){errors.append("IllegalArgumentException    "+e)}
        catch( e:IllegalStateException){errors.append("IllegalStateException   "+e)}
        catch( e:NullPointerException){errors.append("NullPointerException   "+e)}
        catch( e:ConcurrentModificationException){errors.append("ConcurrentModificationException    "+e)}
        return res

    }
    /**
     *   Возвращает только первый найденный паттерн.
     */
    override fun findText(text: String?, pattern: String?): String? {
        val res=StringBuilder()
        val errors=StringBuilder()
        try{
            val builder = StringBuilder()
            val ptrn = Pattern.compile(pattern!!)
            val match = ptrn.matcher(text!!)
            if (match.find()) {
                res.append(match.group())
            }

        }catch( e:IndexOutOfBoundsException){errors.append("IndexOutOfBoundsException    "+e)}
        catch( e:IllegalArgumentException){errors.append("IllegalArgumentException    "+e)}
        catch( e:IllegalStateException){errors.append("IllegalStateException   "+e)}
        catch( e:NullPointerException){errors.append("NullPointerException   "+e)}
        catch( e:ConcurrentModificationException){errors.append("ConcurrentModificationException    "+e)}
        res.append(errors.toString())

        return res.toString()

    }


}