import refactor_by_oop.mvp.model.for_text.text.RegexUtils
import refactor_by_oop.mvp.model.for_text.text.TextUtils
import refactor_by_oop.mvp.model.tables.file.FileManager
  import refactor_by_oop.mvp.model.tables.manager.TableManager
import refactor_by_oop.mvp.presenter.commands.processor_commands.CommandProcessor

 import refactor_by_oop.mvp.presenter.read.InputReader
import refactor_by_oop.mvp.view.errors.ErrorReporter
import refactor_by_oop.mvp.view.info.Info



fun main() {





    val commandProcessor = CommandProcessor(InputReader(),  RegexUtils(), TableManager(),  FileManager(TableManager(),
        Info()
    ),TextUtils(), ErrorReporter())
    commandProcessor.processCommands()
}







