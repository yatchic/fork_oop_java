package family_tree.file;

import family_tree.family.FamilyTree;

public class Main {
    public static void main(String[] args) {


        FamilyTree romanovy = new FamilyTree();


        romanovy.addRelation("Мария", "дочь", "Иван");  //--- Этот код добавляет отношение между двумя людьми. В этом примере “Мария” является “дочерью” “Ивана”.
        romanovy.addRelation("Иван", "отец", "Мария");    //---Иван отец Марии.
        romanovy.addRelation("Иван", "рожден", "12.08.1956"); //--- Дата рождения Ивана.
        romanovy.addRelation("Мария", "рожден", "13.07.1982");  //--- Дата рождения Марии.
        romanovy.addRelation("Барсик", "кот", "Мария");  //--- Владельцем кота Барсика является Мария.
        romanovy.addRelation("шерсть", "длинная", "Барсик");  //--- У кота Барсика длинная шерсть.
        romanovy.addRelation("жигули", "собственность", "Иван");   //--- У Ивана есть машина.

       romanovy.write("D:\\загрузки\\familyTrees\\romanovy.txt");    //---Этот код записывает текущее состояние семейного дерева в файл. Путь к файлу указывается в качестве аргумента.
        //romanovy.read("D:\\загрузки\\familyTrees\\romanovy.txt");      //---Этот код читает семейное дерево из файла и этим содержимым пепрезаписывает hashmap, ранее содержимое в hashmap теряется. Путь к файлу указывается в качестве аргумента.
          romanovy.print("D:\\загрузки\\familyTrees\\romanovy.txt");      //---Этот код читает семейное дерево из файла и выводит в консоль. Путь к файлу указывается в качестве аргумента.





    }





}


