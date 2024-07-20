package refactor_by_solid_and_mvp_and_oop.model.for_text.text

 import java.util.ConcurrentModificationException
import java.util.regex.Pattern

class RegexUtils: IRegexUtils {


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



    override fun matchesPattern(line: String, pattern: String): Boolean {
        return line.matches(pattern.toRegex())
    }


}