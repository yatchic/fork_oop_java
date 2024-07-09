package family_tree.family;

import family_tree.iterator.Ascending_order;
import family_tree.iterator.Default_order;
import family_tree.iterator.Descending_order;
import family_tree.addon.Addon;
import family_tree.date.DateClass;
import family_tree.file.FileClass;
import family_tree.show.Show;

import java.io.*;

import java.util.*;

public class FamilyTree  extends FamilyTreeAbstract  implements Relation ,Serializable {
    protected HashMap<Integer, FamilyTree> familyTree = new HashMap<>();

    public String person = "";
    public String relationType = "";
    public String relatedTo = "";
    private Integer id = 1;
    protected String otvet = "";
    private StringBuilder sb = new StringBuilder();
    public Integer key = 0;
    public FamilyTree value = null;

    @Override
    public void deb(Object obj) {
        System.out.println("...."+ obj);
    }

    public FamilyTree(String person, String relationType, String relatedTo) {
        this.relatedTo = relatedTo;
        this.relationType = relationType;
        this.person = person;
    }

    public FamilyTree() {
    }

    /**
     * Читает из файла и этим перезаписывает hashMap.
     */

    public void read(String file) {
        FileClass f = new FileClass();
        familyTree = f.readFromfileToFamilyTree(file);
    }
    /**
     * Из hashMap записывает в файл.
     */
    public void write(String file) {
        FileClass f = new FileClass();
        f.writeToFile(familyTree, file);
    }
    /**
     * Читает из файла и выводит в консоль не перезаписывает hashMap.
     */
    public void print(String file) {
        FileClass f = new FileClass();
        f.print(file);
    }

    /**
     * Добавляет родственную связь в базу данных созданной конструктором.
     */
    @Override
    public void addRelation(String person, String relationType, String relatedTo) {

        familyTree.put(id++, new FamilyTree(person, relationType, relatedTo));
    }


    /**
     * Выводит в консоль ответ относительно одного отношения в любой комбинации из базы данных созданной конструктором.
     */
    @Override
    public void question(String relation) {
        sb.setLength(0);
        otvet = "ничего";
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.person.equals(relation)) {
                sb.append("\n" + familyTree.relationType + "," + familyTree.relatedTo).toString();


            }
            if (familyTree.relationType.equals(relation)) {
                sb.append("\n" + familyTree.person + "," + familyTree.relatedTo).toString();

            }
            if (familyTree.relatedTo.equals(relation)) {
                sb.append("\n" + familyTree.person + "," + familyTree.relationType).toString();

            }
        }
        otvet = sb.toString();
        show();

    }


    /**
     * Выводит в консоль ответ относительно двух отношений в любой комбинации из базы данных созданной конструктором.
     */
    @Override
    public void question(String first, String second) {
        otvet = "ничего";
        sb.setLength(0);
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.person.equals(first) && familyTree.relationType.equals(second)) {
                sb.append("\n" + familyTree.relatedTo).toString();

            }
            if (familyTree.person.equals(first) && familyTree.relatedTo.equals(second)) {
                sb.append("\n" + familyTree.relationType).toString();

            }
            if (familyTree.relationType.equals(first) && familyTree.person.equals(second)) {
                sb.append("\n" + familyTree.relatedTo).toString();

            }
            if (familyTree.relationType.equals(first) && familyTree.relatedTo.equals(second)) {
                sb.append("\n" + familyTree.person).toString();

            }
            if (familyTree.relatedTo.equals(first) && familyTree.person.equals(second)) {
                sb.append("\n" + familyTree.relationType).toString();

            }
            if (familyTree.relatedTo.equals(first) && familyTree.relationType.equals(second)) {
                sb.append("\n" + familyTree.person).toString();

            }

        }
        otvet = sb.toString();
        show();
    }

    /**
     * Выводит в консоль ответ относительно трех отношений в любой комбинации из базы данных созданной конструктором.
     */
    @Override
    public void question(String first, String second, String third) {
        otvet = "ничего";
        sb.setLength(0);
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();
            if (familyTree.person.equals(first) && familyTree.relationType.equals(second) && familyTree.relatedTo.equals(third)) {
                sb.append("\n" + "да\n" + familyTree.person + "," + familyTree.relationType + "," + familyTree.relatedTo).toString();

            }
        }
        otvet = sb.toString();
        show();
    }

    @Override
    public void show() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return otvet;
    }

    /**
     * Выводит в консоль содержимое данной базы данных созданной конструктором.
     */
    @Override
    public void showAll() {
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            System.out.println(key + ", " + value.person + ", " + value.relationType + ", " + value.relatedTo);

        }
    }

    /**
     * Удаляет отношение или отношения по заданному имени или отношению данной базы данных созданной конструктором.
     */
    @Override
    public void deleteRelation(String value) {
        Set<Integer> keysToRemove = new HashSet<>();
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();
            if (familyTree.person.equals(value) || familyTree.relationType.equals(value) || familyTree.relatedTo.equals(value)) {
                keysToRemove.add(entry.getKey());
            }
        }
        for (Integer key : keysToRemove) {
            familyTree.remove(key);
        }
    }


    /**
     * Возвращает родителей данной базы данных созданной конструктором.
     */
    @Override
    public void getParents() {
        sb.setLength(0);
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.relationType.equals("дочь") || familyTree.relationType.equals("сын")) {
                sb.append("\n" + familyTree.relatedTo).toString();

            }

        }
        otvet = sb.toString();
        Addon ad = new Addon();
        otvet = ad.removeDuplicates(otvet);
        show();

    }

    /**
     * Возвращает возраст по заданному имени в данной базе данных созданной конструктором.
     */
    @Override
    public void getAge(String person) {

        sb.setLength(0);
        String birth = "";
        String die = "nothing";
        Integer age = 0;
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.person.equals(person) && familyTree.relationType.equals("рожден")) {
                birth = familyTree.relatedTo;


            }
            if (familyTree.person.equals(person) && familyTree.relationType.equals("умерший")) {
                die = familyTree.relatedTo;

            }

        }
        Addon a = new Addon();
        DateClass dt = new DateClass();
        if (a.findTextB(die, "")) {
            age = dt.raznitsaVremeni(birth, die);
        }
        if (a.findTextB(die, "nothing")) {
            age = dt.calculateAge(birth);
        }
        otvet = age.toString();

        Show s = new Show(otvet);
        s.toString();
    }

    @Override
    protected String getAgeCorporate(String person) {

        sb.setLength(0);
        String birth = "";
        String die = "nothing";
        Integer age = 0;
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.person.equals(person) && familyTree.relationType.equals("рожден")) {
                birth = familyTree.relatedTo;


            }
            if (familyTree.person.equals(person) && familyTree.relationType.equals("умерший")) {
                die = familyTree.relatedTo;

            }

        }
        Addon a = new Addon();
        DateClass dt = new DateClass();
        if (a.findTextB(die, "")) {
            age = dt.raznitsaVremeni(birth, die);
        }
        if (a.findTextB(die, "nothing")) {
            age = dt.calculateAge(birth);
        }
        return age.toString();


    }

    /**
     * Возвращает всех детей данной базы данных созданной конструктором.
     */
    @Override
    public void getChildren() {
        sb.setLength(0);
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.relationType.equals("дочь") || familyTree.relationType.equals("сын")) {
                sb.append("\n" + familyTree.person).toString();

            }

        }
        otvet = sb.toString();
        Addon a = new Addon();
        otvet = a.removeDuplicates(otvet);

        Show s = new Show(otvet);
        s.toString();

    }

    /**
     * Выводит все возрастные значения в консоль в отсортированном виде.
     */
    public void getAges(String sortType) {
        if (sortType.equals("по возрастанию")) {

            Ascending_order i = new Ascending_order();
            Addon a = new Addon();

            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                int age = a.stringToInteger(getAgeCorporate(familyTree.person));

                if (age != 0) {
                    i.add(age, familyTree.person);
                }
            }
            i.toString();
        }


        if (sortType.equals("по убыванию")) {

            Descending_order i = new Descending_order();
            Addon a = new Addon();
            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                int age = a.stringToInteger(getAgeCorporate(familyTree.person));

                if (age != 0) {
                    i.add(age, familyTree.person);
                }
            }
            i.toString();
        }


        if (sortType.equals("по умоланию")) {

            Default_order i = new Default_order();
            Addon a = new Addon();
            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                int age = a.stringToInteger(getAgeCorporate(familyTree.person));

                if (age != 0) {
                    i.add(age, familyTree.person);
                }
            }
            i.toString();
        }
    }

    /**
     * Выводит все дни рождения в консоль в отсортированном виде.
     */
    public void getBirths(String sortType) {
        DateClass d = new DateClass();
        if (sortType.equals("по возрастанию")) {
            Ascending_order i = new Ascending_order();
            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                if (familyTree.relationType.equals("рожден")) {
                    int days = d.calculateDays(familyTree.relatedTo);
                    i.add(days, familyTree.person + "  " + familyTree.relatedTo);

                }
            }
            i.toString();
        }


        if (sortType.equals("по убыванию")) {
            Descending_order i = new Descending_order();
            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                if (familyTree.relationType.equals("рожден")) {
                    int days = d.calculateDays(familyTree.relatedTo);
                    i.add(days, familyTree.person + "  " + familyTree.relatedTo);

                }
            }
            i.toString();
        }


        if (sortType.equals("по умолчанию")) {

            Default_order i = new Default_order();
            for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
                FamilyTree familyTree = entry.getValue();
                if (familyTree.relationType.equals("рожден")) {
                    int days = d.calculateDays(familyTree.relatedTo);
                    i.add(days, familyTree.person + "  " + familyTree.relatedTo);

                }
            }
            i.toString();
        }

    }
    @Override
    protected String questionByRelatedTo(String first) {
        String res = "";
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.person.equals(first)) {
                res = familyTree.relatedTo;

            }

        }

        return res;
    }

@Override
protected String questionUniversal(String first) {
        String answer = questionByRelatedTo(first);
        Addon a=new Addon();
        for (Map.Entry<Integer, FamilyTree> entry : familyTree.entrySet()) {
            FamilyTree familyTree = entry.getValue();

            if (familyTree.relatedTo.equals(answer) ) {
                a.add(familyTree.person+", "+familyTree.relationType+", "+familyTree.relatedTo);

            }

        }
        return a.joinToString("\n");


    }

    /**
     * Выводит все отношения связанные с заданным значением, работает тогда гогда третье значение одинаковое
     *               dogs.addRelation("Бобик", "кличка", "1111");
     *             dogs.addRelation("овчарка", "порода", "1111");
     *  третье значение "1111".
     */
    @Override
   public void  questionAll(String first) {

        otvet= questionUniversal(first);

        show();
    }


    }//конец класса

















