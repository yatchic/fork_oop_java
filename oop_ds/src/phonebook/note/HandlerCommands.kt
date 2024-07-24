package phonebook.note

import phonebook.IRegexUtils
import phonebook.ITextUtils
import phonebook.RegexUtils


class HandlerCommands(private val regexUtils: IRegexUtils, private val textUtils: ITextUtils,private val phoneBook:PhoneBook) {

    sealed interface ICommand {
        fun isValid(): Boolean
    }

    sealed class ValidatorByExit(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils=RegexUtils()
           return regexUtils.findTextB(line, "^\\s*exit\\s*$")
        }
    }
     class RefValidatorByExit(line: String) : ValidatorByExit(line)

    sealed class ValidatorByHelp(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils=RegexUtils()
            return regexUtils.findTextB(line, "^\\s*help\\s*$")
        }
    }
    class RefValidatorByHelp(line: String) : ValidatorByHelp(line)

    sealed class ValidatorByShow(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils=RegexUtils()
            return regexUtils.findTextB(line, "^\\s*show\\s*$")
        }
    }
    class RefValidatorByShow(line: String) : ValidatorByShow(line)


    sealed class ValidatorByNumber(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils=RegexUtils()
            return   (regexUtils.findTextB(line, "^\\s*add\\s+[aA-zZаА-яЯ]+\\s+\\+?\\d+\\s*"))
        }
    }
    class RefValidatorByNumber(line: String) : ValidatorByNumber(line)

    sealed class ValidatorByEmail(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils=RegexUtils()
            return (regexUtils.findTextB( line,"\\s*add\\s+[aA-zZаА-яЯ]+\\s+[aA-zZаА-яЯ_\\d]+@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*"))
        }
    }
    class RefValidatorByEmail(line: String) : ValidatorByEmail(line)

    fun readCommand() {

        var repeat = true
        while (repeat) {

            val line:String  = readlnOrNull().toString()

            val validatorByExit =  RefValidatorByExit(line )
            val validatorByHelp =  RefValidatorByHelp(line )
            val validatorByShow =  RefValidatorByShow(line )
            val validatorByNumber =  RefValidatorByNumber(line )
            val validatorByEmail =  RefValidatorByEmail(line )
            line.let {
                when {
                    validatorByExit.isValid() ->  repeat = false

                    validatorByHelp.isValid() ->println(phoneBook.getSyntax())

                    validatorByNumber.isValid() -> {
                        val name=regexUtils.findText(line,"\\s*(?<=add)\\s+[aA-zZаА-яЯ]+")
                       val number=regexUtils.findText(line,"\\+?\\d+\\s*$")
                        phoneBook.setPerson(name!!,number!!,"")
                      }

                    validatorByEmail.isValid()->{
                        val name=regexUtils.findText(line,"\\s*(?<=add)\\s+[aA-zZаА-яЯ]+")
                        val email=regexUtils.findText(line,"[aA-zZаА-яЯ_\\d]+@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*")
                        phoneBook.setPerson(name!!,"",email!!)
                            }

                    validatorByShow.isValid() ->{
                        val arr=phoneBook.getPerson()
                        if(arr[0]==""&&arr[1]==""&&arr[2]==""){println("Not initialized")}
                        else{ println("${arr[0]} ${arr[1]} ${arr[2]}") }
                    }
                    else -> println(phoneBook.getSyntax())
                }

            }
         }
    }
}









