package phonebook

interface IRegexUtils {
    fun findTextB(text: String?, pattern: String?): Boolean
    fun replaceTextAll(text: String?, pattern: String?, replacement: String?): String?
    fun findTextAll(text: String?, pattern: String?): Array<String>
    fun findText(text: String?, pattern: String?): String?
    fun matchesPattern(line: String, pattern: String): Boolean
}