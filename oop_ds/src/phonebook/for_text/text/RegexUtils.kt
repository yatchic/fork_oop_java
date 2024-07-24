package phonebook

 import java.util.ConcurrentModificationException
import java.util.regex.Pattern

class RegexUtils: IRegexUtils {

  override  fun findText(text: String?, pattern: String?): String? {
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
        catch( e: ConcurrentModificationException){errors.append("ConcurrentModificationException    "+e)}
        return res

    }
    override  fun replaceTextAll(text: String?, pattern: String?, replacement: String?): String? {
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
        catch( e: ConcurrentModificationException){errors.append("ConcurrentModificationException    "+e)}

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

    override fun matchesPattern(line: String, pattern: String): Boolean {
        return line.matches(pattern.toRegex())
    }


}