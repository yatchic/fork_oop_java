package refactor_by_oop.mvp.model.for_text.text

interface ITextUtils {
    fun tokens(input: String): List<String>
    fun stringToInteger(text: String): Int
    fun stringToDouble(text: String): Double
    fun stringToBoolean(text: String): Boolean
    fun tokenize(line: String): List<String>
    fun debug(message: String)
}