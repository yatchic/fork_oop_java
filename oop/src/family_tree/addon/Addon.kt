package family_tree.addon

import java.util.LinkedList
import java.util.regex.Pattern

class Addon: AddonInterface {
   var errors="";
    val mutableList= mutableListOf<String>()

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