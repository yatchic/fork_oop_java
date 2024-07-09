package other_tree.tree;


import org.jetbrains.annotations.NotNull;
import other_tree.file.File;

import java.util.*;



public class Tree<A, B, C, D, E, F> implements TreeInterface, Iterable<TreeMap<Integer, Tree<A, B, C, D, E, F>>> {
    protected TreeMap<Integer, Tree<A, B, C, D, E, F>> tree = new TreeMap<>();
    private Integer id = 1;
    private A a;
    private B b;
    private C c;
    private D d;
    private E e;
    private F f;
    private String order;

    private Boolean invokeA=false;
    private Boolean invokeB=false;
    private Boolean invokeC=false;
    private Boolean invokeD=false;
    private Boolean invokeE=false;
    private Boolean invokeF=false;
    private Boolean invokeAll=false;
    private Tree(A a, B b, C c, D d, E e, F f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
public Tree(){}
    public void add(A a, B b, C c, D d, E e, F f) {
        Tree<A, B, C, D, E, F> node = new Tree<>(a, b, c, d, e, f);
        tree.put(id++, node);
    }


    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }

    public C getC() {
        return c;
    }

    public D getD() {
        return d;
    }

    public E getE() {
        return e;
    }

    public F getF() {
        return f;
    }
    @Override
    public void print(String fileName){
        File f= new File();
        f.print(fileName);
    }
    @Override
    public void write(String fileName){
        File f= new File();

        f.writeToFile(tree,fileName);
    }
    @Override
    public void read(String fileName){
        File f= new File();
         tree=f.readFromfileToTree(fileName);
    }


    @NotNull
    @Override
    public Iterator<TreeMap<Integer, Tree<A, B, C, D, E, F>>> iterator() {
        return Collections.singletonList(tree).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tree<A, B, C, D, E, F> personTree : tree.values()) {
            sb.append(personTree).append("\n");
        }
        return sb.toString();
    }

    public void traversByTree(String order) {
        for (Iterator<TreeMap<Integer, Tree<A, B, C, D, E, F>>> it = iterator(); it.hasNext();) {
            TreeMap<Integer, Tree<A, B, C, D, E, F>> subtree = it.next();
            if (subtree != null) {
                traversByTree(subtree, order);
            }
        }
    }
    /**
     * Проходит по treeMap: по возрастанию выводит в том порядке в котором были добавлены значения.
     */
    private void traversByTree(TreeMap<Integer, Tree<A, B, C, D, E, F>> subtree, String order) {
        List<Map.Entry<Integer, Tree<A, B, C, D, E, F>>> sortedEntries = new ArrayList<>(subtree.entrySet());
        /**
         * Проходит по treeMap.
         */
        if(invokeAll){
            /**
             * По возрастанию выводит в том порядке в котором были добавлены значения.
             */

            if (order.equals("по возрастанию")) {
                sortedEntries.sort(Comparator.comparingInt(Map.Entry::getKey));
            }
            /**
             * По убыванию выводит в обратном порядке в каком порядке были добавлены значения.
             */
            else if (order.equals("по убыванию")) {
                sortedEntries.sort((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()));
            }
        }
        /**
         * Здесь проверки типов данных, если числовой тип данных тогда сортировка будет по числовому значению,
         * если символьный тип данных тогда сортировка будет по алфавиту.
         */
        if (invokeA) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.a instanceof Number && tree2.a instanceof Number) {
                    Number value1 = (Number) tree1.a;
                    Number value2 = (Number) tree2.a;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeB) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.b instanceof Number && tree2.b instanceof Number) {
                    Number value1 = (Number) tree1.b;
                    Number value2 = (Number) tree2.b;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeC) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.c instanceof Number && tree2.c instanceof Number) {
                    Number value1 = (Number) tree1.c;
                    Number value2 = (Number) tree2.c;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeD) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.d instanceof Number && tree2.d instanceof Number) {
                    Number value1 = (Number) tree1.d;
                    Number value2 = (Number) tree2.d;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeE) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.c instanceof Number && tree2.e instanceof Number) {
                    Number value1 = (Number) tree1.e;
                    Number value2 = (Number) tree2.e;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (invokeF) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.f instanceof Number && tree2.a instanceof Number) {
                    Number value1 = (Number) tree1.f;
                    Number value2 = (Number) tree2.f;
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (invokeA) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.a instanceof String && tree2.a instanceof String) {
                    String value1 = (String) tree1.a;
                    String value2 = (String) tree2.a;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeB) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.b instanceof String && tree2.b instanceof String) {
                    String value1 = (String) tree1.b;
                    String value2 = (String) tree2.b;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (invokeC) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.c instanceof String && tree2.c instanceof String) {
                    String value1 = (String) tree1.c;
                    String value2 = (String) tree2.c;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeD) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.d instanceof String && tree2.d instanceof String) {
                    String value1 = (String) tree1.d;
                    String value2 = (String) tree2.d;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (invokeE) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.e instanceof String && tree2.e instanceof String) {
                    String value1 = (String) tree1.e;
                    String value2 = (String) tree2.e;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (invokeF) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.f instanceof String && tree2.f instanceof String) {
                    String value1 = (String) tree1.f;
                    String value2 = (String) tree2.f;
                    if (order.equals("по возрастанию")) {
                        return value1.compareTo(value2);
                    }
                    if (order.equals("по убыванию")) {
                        return value2.compareTo(value1);
                    }
                }
                return 0;
            };
            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if(invokeA){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.a);

            }
        }
        if(invokeB){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.b);
            }
        }
        if(invokeC){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.c);
            }
        }
        if(invokeD){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.d);            }
        }
        if(invokeE){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.e);
            }
        }
        if(invokeF){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.f);
            }
        }
            if(invokeAll) {
                for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                    Integer personId = entry.getKey();
                    Tree<A, B, C, D, E, F> personTree = entry.getValue();
                    System.out.println(personTree.a + ", " + personTree.b + ", " + personTree.c + ", " + personTree.d + ", " + personTree.e + ", " + personTree.f);
                }
            }

                  invokeA=false;
                  invokeB=false;
                  invokeC=false;
                  invokeD=false;
                  invokeE=false;
                  invokeF=false;
                  invokeAll=false;

    }
    /**
     * Выводит всю таблицу в прямом или обратном порядке.
     */
    public void getByAll(String order) {
        invokeAll=true;
        traversByTree(order);

    }
    /**
     * Выводит первую колонку в отсортированном виде.
     */
    public void getByA(  String order) {
        invokeA=true;
        traversByTree(order);

    }
    /**
     * Выводит вторую колонку в отсортированном виде.
     */
    public void getByB(  String order) {
        invokeB=true;
        traversByTree(order);

    }
    /**
     * Выводит третью колонку в отсортированном виде.
     */
    public void getByC( String order) {
        invokeC=true;
        traversByTree(order);
    }
    /**
     * Выводит четвертую колонку в отсортированном виде.
     */
    public void getByD( String order) {
        invokeD=true;
        traversByTree(order);
    }
    /**
     * Выводит пятую колонку в отсортированном виде.
     */
    public void getByE( String order) {
        invokeE=true;
        if(order.equals("по возрастанию")) {
            traversByTree("по убыванию");
        }

        if(order.equals("по убыванию")) {
            traversByTree("по возрастанию");
        }
    }
    /**
     * Выводит шестую колонку в отсортированном виде.
     */
    public void getByF( String order) {
        invokeF=true;
        traversByTree(order);
    }
}
















