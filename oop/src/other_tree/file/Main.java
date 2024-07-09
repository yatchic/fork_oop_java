package other_tree.file;

import other_tree.tree.Tree;

public class Main {
    public static void main(String []args){

        Tree<Integer,String,String,Integer,String,Boolean> dogs=new Tree<>();
        //Tree<рег.номер,кличка,порода,вес,окрас шерсти,наличие прививки>

        dogs.add(1111,"Бобик","дворняга",4,"черный",false);
        dogs.add(1112,"Тузик","дворняга",6,"пятнистый",false);
        dogs.add(1113,"Тобик","дворняга",5,"серый",false);
        //dogs.getByAll("по убыванию");



        Tree<Integer,String,String,String,Integer,Double>students=new Tree<>();
        //Tree<рег.номер,Ф.И.О.,Факультет ,Специальность ,Год обучения ,Средний балл>
        students.add(1111,"Авдеев Андрей Сергеевич","экономический","экономист",4,3.2);
        students.add(1112,"Федоров Иван Сергеевач","юридический","юриспруденция",2,4.2);
        students.add(1113,"Кнорина Мария Федоровна","технологический","технолог",1,5.1);
        students.add(1114,"Карпов Сергей Иванович","машиностроительный","конструктор",3,4.8);
        students.add(1114,"Бобриков Сергей Иванович","химический","химик",4,4.6);
        students.add(1114,"Eleanor Hawthorne Grace","биологический","биолог",6,1.2);
        students.add(1114,"Alexander Harrison Cole","филологический","филолог",5,4.3);

        Tree<Integer,String,String,String,String,String>petrovy=new Tree<>();
        //Tree<номер паспорта,Ф.И.О.,родственная связь ,кого,дата рождения ,дата смерти >
        petrovy.add(1111,"Петров Валентин Сергеевич","сын","Петров Сергей Иванович","12.03.1988","");
        petrovy.add(1112,"Петрова Мария Федоровна","дочь","Петров Сергей Иванович","13.07.1987","");
        petrovy.add(1113,"Петров Валентин Сергеевич","сын","Петорова Елена Александровна","12.03.1988","");
        petrovy.add(1112,"Петрова Мария Федоровна","дочь","Петорова Елена Александровна","13.07.1987","");





        //students.write("D:\\загрузки\\familyTrees");
        //students.print("D:\\загрузки\\familyTrees");



    }

}
