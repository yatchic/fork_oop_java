package mvp.model.tree;



/*
*
*                       Здесь создаётся таблица TreeMap tree.
*
* */
import mvp.model.order.TreeSorter;

import java.io.Serializable;


public   class Tree<A, B, C, D, E, F> implements Serializable {
    private static final long serialVersionUID = 1L;
    TreeSorter treeSorter;

    public Tree() {
       treeSorter=new TreeSorter();
    }

    public Tree(A a, B b, C c, D d, E e, F f) {
        treeSorter=new TreeSorter();
        treeSorter.set_a(a);
        treeSorter.set_b(b);
        treeSorter.set_c(c);
        treeSorter.set_d(d);
        treeSorter.set_e(e);
        treeSorter.set_f(f);
    }



     public Object get_a(){return treeSorter.get_a();}
    public Object get_b(){return treeSorter.get_b();}
    public Object get_c(){return treeSorter.get_c();}
    public Object get_d(){return treeSorter.get_d();}
    public Object get_e(){return treeSorter.get_e();}
    public Object get_f(){return treeSorter.get_f();}



    public Boolean get_invokeAll(){return treeSorter.get_invoke_all();}
    public Boolean get_invokeA(){return treeSorter.get_invoke_a();}
    public Boolean get_invokeB(){return treeSorter.get_invoke_b();}
    public Boolean get_invokeC(){return treeSorter.get_invoke_c();}
    public Boolean get_invokeD(){return treeSorter.get_invoke_d();}
    public Boolean get_invokeE(){return treeSorter.get_invoke_e();}
    public Boolean get_invokeF(){return treeSorter.get_invoke_f();}

    public void set_invokeAll(Boolean all){ treeSorter.set_invoke_all(all);}
    public void set_invokeA(Boolean a){ treeSorter.set_invoke_a(a);}
    public void set_invokeB(Boolean b){ treeSorter.set_invoke_b(b);}
    public void set_invokeC(Boolean c){ treeSorter.set_invoke_c(c);}
    public void set_invokeD(Boolean d){ treeSorter.set_invoke_d(d);}
    public void set_invokeE(Boolean e){ treeSorter.set_invoke_e(e);}
    public void set_invokeF(Boolean f){ treeSorter.set_invoke_f(f);}

    public TreeSorter getTreeSorter() {
        return treeSorter;
    }

    public void add(A a, B b, C c, D d, E e, F f) {

        Tree<A, B, C, D, E, F> node = new  Tree<>(a, b, c, d, e, f);

        Integer id = treeSorter.getid();

        treeSorter.setid(id);

        treeSorter.setid(id + 1);
        treeSorter.getTreeSorter().put(id, node);



    }


    @Override
    public String toString() {
        return super.toString();
    }






}




