package refactor_by_solid_and_mvp_and_oop.model.for_text.text

interface IRegexUtils {
    fun findTextB(text: String?, pattern: String?): Boolean
   fun matchesPattern(line: String, pattern: String): Boolean
}