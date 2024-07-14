package mvp.model.tree;


import mvp.model.file_maniger.TreeFileManager;
import mvp.model.order.TreeSorter;
import mvp.model.query.Query;

public class Main {

    public static void main(String[] args) throws Exception {


         Tree<Integer, String, String, Integer, String, Boolean> dogs = new Tree<>();//создается таблица
        //Tree<рег.номер,кличка,порода,вес,окрас шерсти,наличие прививки>

        dogs.add(1111, "Бобик", "дворняга", 4, "черный", false);
        dogs.add(1112, "Тузик", "дворняга", 6, "пятнистый", false);
         dogs.add(1113, "Тобик", "дворняга", 5, "серый", false);
        TreeSorter treeSorter= dogs.getTreeSorter();//Чтобы задавать вопросы и сохранить в файле надо treeSorter передать в Query и TreeFileManager.
        Query q=new Query();
        q.setTreeSorter(treeSorter);
        //q.getByA("по убыванию");

        TreeFileManager treeFileManager=new TreeFileManager();
        treeFileManager.setTreeSorter(treeSorter);


         treeFileManager.writeToFile("D:\\загрузки\\familyTrees\\dogs.txt");


          //q.getByAll("по возрастанию");



         Tree<Integer, String, String, String, Integer, Double> students = new Tree<>();
        //Tree<рег.номер,Ф.И.О.,Факультет ,Специальность ,Год обучения ,Средний балл>
        students.add(1111, "Авдеев Андрей Сергеевич", "экономический", "экономист", 4, 3.2);
        students.add(1112, "Федоров Иван Сергеевач", "юридический", "юриспруденция", 2, 4.2);
        students.add(1113, "Кнорина Мария Федоровна", "технологический", "технолог", 1, 5.1);
        students.add(1114, "Карпов Сергей Иванович", "машиностроительный", "конструктор", 3, 4.8);
        students.add(1114, "Бобриков Сергей Иванович", "химический", "химик", 4, 4.6);
        students.add(1114, "Eleanor Hawthorne Grace", "биологический", "биолог", 6, 1.2);
        students.add(1114, "Alexander Harrison Cole", "филологический", "филолог", 5, 4.3);

         Tree<Integer, String, String, String, String, String> petrovy = new Tree<>();
        //Tree<номер паспорта,Ф.И.О.,родственная связь ,кого,дата рождения ,дата смерти >
        petrovy.add(1111, "Петров Валентин Сергеевич", "сын", "Петров Сергей Иванович", "12.03.1988", "");
        petrovy.add(1112, "Петрова Мария Федоровна", "дочь", "Петров Сергей Иванович", "13.07.1987", "");
        petrovy.add(1113, "Петров Валентин Сергеевич", "сын", "Петрова Елена Александровна", "12.03.1988", "");
        petrovy.add(1112, "Петрова Мария Федоровна", "дочь", "Петрова Елена Александровна", "13.07.1987", "");

        //petrovy.getByAll("по убыванию");

        /*
         *                                         getByAll
         * Выводит всю таблицу в консоль:
         * по возрастанию - выведет все записи в том порядке в котором были добавлены,
         * по убыванию - выведет в противоположном порядке (от низа к верху).
         */

        //petrovy.getByA("по убыванию");

        /*
         *  Выводят номера колонок:
         *  getByA первая крлонка
         *  getByB вторая колонка
         *  getByC третья колонка
         *  getByD четвертая колонка
         *  getByE пятая колонка
         *  getByF шестая колонка
         *  Колонки будут сортироваться по возрастанию или по убыванию,
         * если тип данных числовой тогда сортировка будет по числовому значению,
         * если символьный тогда по алфавиту.
         */


    }

}

