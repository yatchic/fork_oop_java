package refactor_by_solid_and_mvp_and_oop.view.messages

class MessageSaver:IMessageSaver {
    private val messages = mutableListOf<String>()

  override  fun addMessage(message: String) {
        messages.add(message)
    }

    override fun getMessages(): List<String> {
        return messages
    }
}
