package phonebook

import phonebook.note.*


class Start {
    val handlerCommands= HandlerCommands(RegexUtils(),TextUtils(),PhoneBook())
    init{handlerCommands.readCommand()}

}