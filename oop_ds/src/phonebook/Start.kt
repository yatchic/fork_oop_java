package phonebook

import phonebook.notebook.*



class Start {
     private val  notebook=Notebook()
     init{ notebook.readCommand() }
}