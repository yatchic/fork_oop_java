package family_tree_by_oop.node;



import java.io.Serializable;

  public class TreeNode<A, B, C, D, E, F> implements Serializable {


      private Integer id = 1;
    private A a;
    private B b;
    private C c;
    private D d;
    private E e;
    private F f;
    private String order;

    private Boolean invokeA;
    private Boolean invokeB ;
    private Boolean invokeC ;
    private Boolean invokeD ;
    private Boolean invokeE ;
    private Boolean invokeF ;
    private Boolean invokeAll ;

      public TreeNode(Integer id,A a,  B b, C c, D d, E e, F f, String order, Boolean invokeA, Boolean invokeB, Boolean invokeC, Boolean invokeD, Boolean invokeE, Boolean invokeF, Boolean invokeAll) {
          this.a = a;
          this.id = id;
          this.b = b;
          this.c = c;
          this.d = d;
          this.e = e;
          this.f = f;
          this.order = order;
          this.invokeA = invokeA;
          this.invokeB = invokeB;
          this.invokeC = invokeC;
          this.invokeD = invokeD;
          this.invokeE = invokeE;
          this.invokeF = invokeF;
          this.invokeAll = invokeAll;


      }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getInvokeAll() {
        return invokeAll;
    }

    public void setInvokeAll(Boolean invokeAll) {
        this.invokeAll = invokeAll;
    }



      public Boolean getInvokeF() {
        return invokeF;
    }

    public void setInvokeF(Boolean invokeF) {
        this.invokeF = invokeF;
    }

    public Boolean getInvokeE() {
        return invokeE;
    }

    public void setInvokeE(Boolean invokeE) {
        this.invokeE = invokeE;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public F getF() {
        return f;
    }

    public void setF(F f) {
        this.f = f;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Boolean getInvokeA() {
        return invokeA;
    }

    public void setInvokeA(Boolean invokeA) {
        this.invokeA = invokeA;
    }

    public Boolean getInvokeB() {
        return invokeB;
    }

    public void setInvokeB(Boolean invokeB) {
        this.invokeB = invokeB;
    }

    public Boolean getInvokeC() {
        return invokeC;
    }

    public void setInvokeC(Boolean invokeC) {
        this.invokeC = invokeC;
    }

    public Boolean getInvokeD() {
        return invokeD;
    }

    public void setInvokeD(Boolean invokeD) {
        this.invokeD = invokeD;
    }
}

