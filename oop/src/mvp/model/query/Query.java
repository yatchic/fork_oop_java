
/*
*                         Здесь методы для чтения информации из TreeMap tree
*
*
*
* */




package mvp.model.query;
import mvp.model.order.TreeSorter;
public class Query implements InterfaceForQuery {
    TreeSorter treeSorter;
    public void setTreeSorter(TreeSorter treeSorter) {
        this.treeSorter = treeSorter;
    }


    /**
     * Выводит всю таблицу в прямом или обратном порядке.
     */
    @Override
    public void getByAll(String order) {
        treeSorter.set_invoke_all(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит первую колонку в отсортированном виде.
     */
    @Override
    public void getByA(String order) {
        treeSorter.set_invoke_a(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит вторую колонку в отсортированном виде.
     */
    @Override
    public void getByB(String order) {
        treeSorter.set_invoke_b(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит третью колонку в отсортированном виде.
     */
    @Override
    public void getByC(String order) {
        treeSorter.set_invoke_c(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит четвертую колонку в отсортированном виде.
     */
    @Override
    public void getByD(String order) {
        treeSorter.set_invoke_d(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит пятую колонку в отсортированном виде.
     */
    @Override
    public void getByE(String order) {
        treeSorter.set_invoke_e(true);
        treeSorter.traversByTree(order);
    }

    /**
     * Выводит шестую колонку в отсортированном виде.
     */
    @Override
    public void getByF(String order) {
        treeSorter.set_invoke_f(true);
        treeSorter.traversByTree(order);
    }


}

