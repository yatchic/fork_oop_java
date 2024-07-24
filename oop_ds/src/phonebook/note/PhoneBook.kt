package phonebook.note


import java.util.*


  class PhoneBook() {
      private val phoneBook = TreeMap<String, String>()

      private val emailBook = TreeMap<String, String>()

      data class Person(val userName:String, val userPhone:String, val userEmail:String)
      private var users=Person("","","")
fun setPerson(name:String,number:String, email:String) {
            users=Person(name,number,email)

      }
      fun getPerson(): Array<String> {
          val ml= mutableListOf<String>()

          ml.add(users.userName)
          ml.add(users.userPhone)
          ml.add(users.userEmail)
          return ml.toTypedArray()
      }


      fun getPhoneBook( ): TreeMap<String, String> {

          return phoneBook
      }
      fun getEmailBook( ): TreeMap<String, String> {

          return emailBook
      }

      private val syntax="синтаксис:\n" +
              "add Вася +6767776\n" +
              "add Федя 56566665656\n" +
              "add Вася вася_v_4455@mail.ru\n" +
              "add Федя федор455_ааа@яндекс.ру\n"
          fun getSyntax(): String {return syntax}
   }












