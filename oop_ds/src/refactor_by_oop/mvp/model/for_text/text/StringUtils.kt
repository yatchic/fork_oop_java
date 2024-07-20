package refactor_by_oop.mvp.model.for_text.text

import java.util.LinkedHashSet

class StringUtils: IStringUtils {
    /**
     *   Удаляет повторяющиеся строковые значения, при запросе в HashMap по ключам или значению выбираются отношения с одинаковыми значениями
     *   повторяющиеся значения удаляются, остается только одно.
     */
    override  fun removeDuplicates(text: String): String {
        val lines = text.split("\n")
        val uniqueLines = LinkedHashSet(lines)
        return uniqueLines.joinToString("\n")
    }


}