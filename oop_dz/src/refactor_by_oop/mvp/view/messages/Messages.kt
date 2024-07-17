package refactor_by_oop.mvp.view.messages

class Messages:IMessages {
    private var message = ""



    override fun showMessage(message: String?) {
        if (message != null) {
            this.message = message
        }
        toString()
    }


    override fun toString(): String {
        println("сообщение:" + this.message)
        return ""
    }
}