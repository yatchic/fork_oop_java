package refactor_by_oop.mvp.view.errors

class ErrorReporter : IErrorReporter {
    override fun showError(message: String) {
        println("ошибка: $message")
    }
}