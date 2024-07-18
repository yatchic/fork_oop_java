import refactor_by_oop.mvp.model.for_text.text.RegexUtils
import refactor_by_oop.mvp.model.for_text.text.TextUtils
import refactor_by_oop.mvp.model.tables.file.FileManager
import refactor_by_oop.mvp.model.tables.manager.TableManager
import refactor_by_oop.mvp.presenter.service.Service
import refactor_by_oop.mvp.view.errors.ErrorReporter
import refactor_by_oop.mvp.view.info.Info
import refactor_by_oop.mvp.view.read.InputReader


fun main() {

 val commandProcessor = Service(InputReader(),  RegexUtils(), TableManager(),
     FileManager(TableManager(),Info()), TextUtils(), ErrorReporter())

    commandProcessor.processCommands()
}







