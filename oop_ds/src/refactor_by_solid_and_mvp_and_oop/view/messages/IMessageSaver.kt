package refactor_by_solid_and_mvp_and_oop.view.messages

interface IMessageSaver {
    fun addMessage(message: String)
    fun getMessages(): List<String>
}