package refactor_by_oop.mvp.presenter.read

import java.util.*

class InputReader : IInputReader {
    private val scanner = Scanner(System.`in`)

    override fun readLine(): String? {
        return scanner.nextLine()
    }
}