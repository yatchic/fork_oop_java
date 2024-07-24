package phonebook

class TextUtils: ITextUtils {
    fun deb(text:String){println(text)}
    override  fun tokens(input: String): List<String> {

        return input.split("\\s+".toRegex())
    }


    override  fun stringToInteger(text: String): Int {
        return try {
            text.toInt()
        } catch (e: Exception) {
            System.out.println(e)
            return 0
        }
    }

    override   fun stringToDouble(text: String): Double {
        return try {
            text.toDouble()
        } catch (e: Exception) {
            System.out.println(e)
            return 0.0
        }
    }
    override   fun stringToBoolean(text: String): Boolean {
        var res=false
        try {
            val regexUtils=RegexUtils()
            if(regexUtils.findTextB(text,"false|False|FALSE")) {res=false}
            if(regexUtils.findTextB(text,"true|True|TRUE")) {res=true}
        } catch (e: Exception) {
            System.out.println(e)
            return false
        }
        return res
    }

    override fun tokenize(commandLine: String,separator:String): List<String> {
        return commandLine.split(separator.toRegex())
    }

    override fun debug(message: String) {
        println(message)
    }


}