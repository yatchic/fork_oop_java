package refactor_by_solid_and_mvp_and_oop.view

import refactor_by_solid_and_mvp_and_oop.model.file.FileManager
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.RegexUtils
import refactor_by_solid_and_mvp_and_oop.model.for_text.text.TextUtils
import refactor_by_solid_and_mvp_and_oop.model.manager.TableManager
import refactor_by_solid_and_mvp_and_oop.presenter.CommandProcessor
import refactor_by_solid_and_mvp_and_oop.presenter.Service
import refactor_by_solid_and_mvp_and_oop.view.info.ObjectDisplayer
import refactor_by_solid_and_mvp_and_oop.view.info.ObjectSaver
import view.errors.ErrorReporter
import view.read.InputReader

class Start {

     init {
          val service =  Service(
            InputReader(),
            RegexUtils(),
            CommandProcessor(TableManager(), FileManager(TableManager(), ObjectDisplayer(ObjectSaver())), TextUtils(), RegexUtils(), ErrorReporter(),TextUtils()),
            TableManager(),
            FileManager(TableManager(), ObjectDisplayer(ObjectSaver())),
            TextUtils(),
            ErrorReporter()
        )
        service.processCommands()
    }
}

