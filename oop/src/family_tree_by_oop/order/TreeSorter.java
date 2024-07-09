package family_tree_by_oop.order;



import family_tree_by_oop.node.TreeNode;
import family_tree_by_oop.tree.Tree;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;


public class TreeSorter<A, B, C, D, E, F> implements InterfaceForTreeSorter, Iterable<TreeMap<Integer, Tree<A, B, C, D, E, F>>>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private TreeMap<Integer, Tree<A, B, C, D, E, F>> treeSorter;

    private TreeNode treeNode= new TreeNode(1,null, null,null,null,null,null, "",false,false,false,false,false,false,false);

    public Integer getid(){ return treeNode.getId();}

    public void setid(Integer id){treeNode.setId(id);}

    public Object get_a(){return  treeNode.getA();}
    public Object get_b(){return  treeNode.getB();}
    public Object get_c(){return  treeNode.getC();}
    public Object get_d(){return  treeNode.getD();}
    public Object get_e(){return  treeNode.getE();}
    public Object get_f(){return  treeNode.getF();}

    public Boolean get_invoke_all(){ return  treeNode.getInvokeAll();}
    public Boolean get_invoke_a(){ return  treeNode.getInvokeA();}
    public Boolean get_invoke_b(){ return  treeNode.getInvokeB();}
    public Boolean get_invoke_c(){ return  treeNode.getInvokeC();}
    public Boolean get_invoke_d(){ return  treeNode.getInvokeD();}
    public Boolean get_invoke_e(){ return  treeNode.getInvokeE();}
    public Boolean get_invoke_f(){ return  treeNode.getInvokeF();}

    public void set_invoke_all(Boolean all){    treeNode.setInvokeAll(all);}
    public void set_invoke_a(Boolean a){    treeNode.setInvokeA(a);}
    public void set_invoke_b(Boolean b){    treeNode.setInvokeB(b);}
    public void set_invoke_c(Boolean c){    treeNode.setInvokeC(c);}
    public void set_invoke_d(Boolean d){    treeNode.setInvokeD(d);}
    public void set_invoke_e(Boolean e){    treeNode.setInvokeE(e);}
    public void set_invoke_f(Boolean f){    treeNode.setInvokeF(f);}








    public void set_a(A a){   treeNode.setA(a);}
    public void set_b(B b){   treeNode.setB(b);}
    public void set_c(C c){   treeNode.setC(c);}
    public void set_d(D d){   treeNode.setD(d);}
    public void set_e(E e){   treeNode.setE(e);}
    public void set_f(F f){   treeNode.setF(f);}

    public void set_all(Boolean b){   treeNode.setInvokeAll(b);}

    public TreeSorter( ) {
        this.treeSorter =new TreeMap<Integer, Tree<A, B, C, D, E, F>>();
    }

    public TreeMap<Integer, Tree<A, B, C, D, E, F>> getTreeSorter() {
        return treeSorter;
    }

    public void setTreeSorter(TreeMap<Integer, Tree<A, B, C, D, E, F>> treeSorter) {
        this.treeSorter = treeSorter;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public void setTreeNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }



@Override
    public void traversByTree(String order) {
        for (Iterator<TreeMap<Integer, Tree<A, B, C, D, E, F>>> it = iterator(); it.hasNext(); ) {
            TreeMap<Integer,Tree<A, B, C, D, E, F>> subtree = it.next();
            if (subtree != null) {
                traversByTree(subtree, order);
            }
        }
    }

    @NotNull
    @Override
    public Iterator<TreeMap<Integer, Tree<A, B, C, D, E, F>>> iterator() {
        return Collections.singletonList(treeSorter).iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Tree<A, B, C, D, E, F> personTree : treeSorter.values()) {
            sb.append(personTree).append("\n");
        }
        return sb.toString();
    }


    /**
     * Проходит по treeMap: по возрастанию выводит в том порядке в котором были добавлены значения.
     */
    private void traversByTree(TreeMap<Integer, Tree<A, B, C, D, E, F>> subtree, String order) {
        List<Map.Entry<Integer, Tree<A, B, C, D, E, F>>> sortedEntries = new ArrayList<>(subtree.entrySet());
        /**
         * Проходит по treeMap.
         */
        if(treeNode.getInvokeAll()){
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
        if (treeNode.getInvokeA()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_a() instanceof Number && tree2.get_a() instanceof Number) {
                    Number value1 = (Number) tree1.get_a();
                    Number value2 = (Number) tree2.get_a();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (treeNode.getInvokeB()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_b() instanceof Number && tree2.get_b() instanceof Number) {
                    Number value1 = (Number) tree1.get_b();
                    Number value2 = (Number) tree2.get_b();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (treeNode.getInvokeC()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_c() instanceof Number && tree2.get_c() instanceof Number) {
                    Number value1 = (Number) tree1.get_c();
                    Number value2 = (Number) tree2.get_c();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (treeNode.getInvokeD()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_d() instanceof Number && tree2.get_d() instanceof Number) {
                    Number value1 = (Number) tree1.get_d();
                    Number value2 = (Number) tree2.get_d();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }
        if (treeNode.getInvokeE()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_e() instanceof Number && tree2.get_e() instanceof Number) {
                    Number value1 = (Number) tree1.get_e();
                    Number value2 = (Number) tree2.get_e();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (treeNode.getInvokeF()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {

                if (tree1.get_f() instanceof Number && tree2.get_f() instanceof Number) {
                    Number value1 = (Number) tree1.get_f();
                    Number value2 = (Number) tree2.get_f();
                    if (order.equals("по возрастанию")) {return Double.compare(value1.doubleValue(), value2.doubleValue()); }
                    if (order.equals("по убыванию")) {return Double.compare(value2.doubleValue(), value1.doubleValue()); }

                }

                return 0;
            };

            sortedEntries.sort((entry1, entry2) -> valueComparator.compare(entry1.getValue(), entry2.getValue()));
        }

        if (treeNode.getInvokeA()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_a() instanceof String && tree2.get_a() instanceof String) {
                    String value1 = (String) tree1.get_a();
                    String value2 = (String) tree2.get_a();
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
        if (treeNode.getInvokeB()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_b() instanceof String && tree2.get_b() instanceof String) {
                    String value1 = (String) tree1.get_b();
                    String value2 = (String) tree2.get_b();
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

        if (treeNode.getInvokeC()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_c() instanceof String && tree2.get_c() instanceof String) {
                    String value1 = (String) tree1.get_c();
                    String value2 = (String) tree2.get_c();
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
        if (treeNode.getInvokeD()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_d() instanceof String && tree2.get_d() instanceof String) {
                    String value1 = (String) tree1.get_d();
                    String value2 = (String) tree2.get_d();
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

        if (treeNode.getInvokeE()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_e() instanceof String && tree2.get_e() instanceof String) {
                    String value1 = (String) tree1.get_e();
                    String value2 = (String) tree2.get_e();
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
        if (treeNode.getInvokeF()) {
            Comparator<Tree<A, B, C, D, E, F>> valueComparator = (tree1, tree2) -> {
                if (tree1.get_f() instanceof String && tree2.get_f() instanceof String) {
                    String value1 = (String) tree1.get_f();
                    String value2 = (String) tree2.get_f();
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

        if(treeNode.getInvokeA()){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_a());

            }
        }
        if(treeNode.getInvokeB()){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_b());
            }
        }
        if(treeNode.getInvokeC()){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_c());
            }
        }
        if(treeNode.getInvokeD()){
            for (Map.Entry<Integer,Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_d());            }
        }
        if(treeNode.getInvokeE()){
            for (Map.Entry<Integer, Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_e());
            }
        }
        if(treeNode.getInvokeF()){
            for (Map.Entry<Integer,Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
                Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(   personTree.get_f());
            }
        }
        if(treeNode.getInvokeAll()) {
            for (Map.Entry<Integer,Tree<A, B, C, D, E, F>> entry : sortedEntries) {
                Integer personId = entry.getKey();
               Tree<A, B, C, D, E, F> personTree = entry.getValue();
                System.out.println(personTree.get_a() + ", " + personTree.get_b() + ", " + personTree.get_c() + ", " + personTree.get_d() + ", " + personTree.get_e() + ", " + personTree.get_f());
            }
        }

        treeNode.setInvokeA(false);
        treeNode.setInvokeB(false);
        treeNode.setInvokeC(false);
        treeNode.setInvokeD(false);
        treeNode.setInvokeE(false);
        treeNode.setInvokeF(false);
        treeNode.setInvokeE(false);

    }


}

