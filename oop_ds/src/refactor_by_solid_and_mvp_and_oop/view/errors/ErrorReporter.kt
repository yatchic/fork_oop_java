package view.errors

class ErrorReporter : IErrorReporter {
    override fun showError(message: String) {
        toString()+message
    }

    override fun toString(): String {
        return "ошибка: "
    }
}