package phonebook

interface ITextUtils {
    fun tokens(input: String): List<String>
    fun stringToInteger(text: String): Int
    fun stringToDouble(text: String): Double
    fun stringToBoolean(text: String): Boolean
    fun tokenize(commandLine: String,separator:String): List<String>
    fun debug(message: String)
}