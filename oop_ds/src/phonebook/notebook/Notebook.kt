package phonebook.notebook


import phonebook.RegexUtils

import phonebook.tables.Tables


open class Notebook() {

    val regexUtils= RegexUtils()
    private var id=1
    private var numbers="numbers"
    private var number="number"
    private var emails="emails"
    private var email="email"
    private var name="name"
    data class Person(var name:String,var number:String,var email:String)
     val person=Person("","","")
    private val tables = Tables<Any>()
    init{
          tables.createTable(numbers)
        tables.createColumn(numbers,name)
        tables.createColumn(numbers,number)

        tables.createTable(emails)
        tables.createColumn(emails,name)
        tables.createColumn(emails,email)


    }
   private fun addRowToNumbers(name:String,number:String){
    tables.addRow(this.numbers,this.name,name)
    tables.addRow(this.numbers,this.number,number)
}
    private  fun addRowToEmails(name:String,email:String){
        tables.addRow(this.emails,this.name,name)
        tables.addRow(this.emails,this.email,email)
    }

    fun searchInNumbers(value:String): String {
         val numbers=(tables.getRowsByValue(this.numbers, this.number,value))
         return numbers!!.joinToString(",")
    }
    fun searchInEmails(value:String): String{
        val emails=tables.getRowsByValue(this.emails, this.email,value)
        return emails!!.joinToString(",")
    }
    fun searchInNames(value:String): String{
        val namesFromNambers=tables.getRowsByValue(this.numbers, this.name,value)
        val namesFromEmails=tables.getRowsByValue(this.emails, this.name,value)

        return namesFromNambers!!.joinToString(",")+namesFromEmails!!.joinToString(",")
    }
    private val syntax="синтаксис:\n" +
            "addPhone Vasja +465767\n" +
            "addPhone Вася 45565665767  \n" +
            "addEmail Vasja vasa@mail.ru\n" +
            "addEmail Вася вася_v_4455@mail.ru\n" +
            "show Vasja\n" +
            "find vasa@mail.ru\n" +
            "find 45565665767\n" +
            "help"

    sealed interface ICommand {
        fun isValid(): Boolean
    }
    sealed class ValidatorByFind(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            var res = false
            val regexUtils = RegexUtils()
            //"^\\s*find\\s+(\\+?\\d+\\|[aA-zZаА-яЯ_\\d]+@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+)\\s*$"
            if (regexUtils.findTextB(line,"^\\s*find\\s+\\+?\\d+\\s*$")||
                regexUtils.findTextB(line,"^\\s*find\\s+[aA-zZаА-яЯ_\\d]+@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*$")
            ) {

                res = true
            }

            return res

        }
    }
    class RefValidatorByFind(line: String) : ValidatorByFind(line)

    sealed class ValidatorByExit(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils= RegexUtils()
            return regexUtils.findTextB(line, "^\\s*exit\\s*$")
        }
    }
    class RefValidatorByExit(line: String) : ValidatorByExit(line)

    sealed class ValidatorByHelp(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils= RegexUtils()
            return regexUtils.findTextB(line, "^\\s*help\\s*$")
        }
    }
    class RefValidatorByHelp(line: String) : ValidatorByHelp(line)

    sealed class ValidatorByShow(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils= RegexUtils()
            return regexUtils.findTextB(line, "^\\s*show\\s+[aA-zZаА-яЯ]+\\s*$")
        }
    }
    class RefValidatorByShow(line: String) : ValidatorByShow(line)


    sealed class ValidatorByNumber(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils= RegexUtils()
            return   (regexUtils.findTextB(line, "^\\s*addPhone\\s+[aA-zZаА-яЯ]+\\s+\\+?\\d+\\s*"))
        }
    }
    class RefValidatorByNumber(line: String) : ValidatorByNumber(line)

    sealed class ValidatorByEmail(protected val line: String) : ICommand {
        override fun isValid(): Boolean {
            val regexUtils= RegexUtils()
            var res=false
if(regexUtils.findTextB( line,"\\s*addEmail\\s+[aA-zZаА-яЯ]+\\s+[aA-zZаА-яЯ_\\d]*@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*")) {
    res=true

}
   return res
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
            val validatorByFind =  RefValidatorByFind(line )
            line.let {
                when {
                    validatorByExit.isValid() ->  repeat = false

                    validatorByHelp.isValid() ->println(syntax)

                    validatorByNumber.isValid() -> {
                        var name=regexUtils.findText(line,"\\s*(?<=addPhone)\\s+[aA-zZаА-яЯ]+")
                        name=regexUtils.replaceTextAll(name,"\\s+","")
                        var number=regexUtils.findText(line,"\\+?\\d+\\s*$")
                        number=regexUtils.replaceTextAll(number,"\\s+","")
                        person.name=name!!
                        person.number=number!!
                        person.email=""
                        addRowToNumbers(person.name,person.number)


                    }

                    validatorByEmail.isValid()->{
                        var name=regexUtils.findText(line,"\\s*(?<=addEmail)\\s+[aA-zZаА-яЯ]+")
                        name=regexUtils.replaceTextAll(name,"\\s+","")

                        val email=regexUtils.findText(line,"[aA-zZаА-яЯ_\\d]*@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*")
                        person.name=name!!
                        person.number=""
                        person.email=email!!
                        addRowToEmails(person.name,person.email)
                    }

                    validatorByShow.isValid() ->{
                        var name=regexUtils.findText(line, "[aA-zZаА-яЯ]+\\s*$")
                          name=regexUtils.replaceTextAll(name, "\\s+","")
                        name=searchInNumbers(name!!)+"\n"+searchInEmails(name!!)
                        name=regexUtils.replaceTextAll(name, "\\[+|\\]","")

                        println(name)

                    }
                    validatorByFind.isValid() ->{
                         var numberOrEmail=regexUtils.findText(line, "\\+?[\\d]+\\s*$|[aA-zZаА-яЯ_\\d]+@[aA-zZаА-яЯ]+\\.[aA-zZаА-яЯ]+\\s*$")

                        numberOrEmail=regexUtils.replaceTextAll(numberOrEmail, "\\s+","")
                        numberOrEmail=searchInNames(numberOrEmail!!)
                         numberOrEmail=regexUtils.replaceTextAll(numberOrEmail, "\\[+|\\]","")
                        println(numberOrEmail)


                    }
                    else -> println(syntax)
                }

            }
        }
    }


}