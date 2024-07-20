package refactor_by_solid_and_mvp_and_oop.model.for_text.text

class TextUtils: ITextUtils,IMockTextUtils {


    override fun tokenize(line: String): List<String> {
        return line.split("\\s+".toRegex())
    }

    override fun debug(message: String) {
        println(message)
    }


}