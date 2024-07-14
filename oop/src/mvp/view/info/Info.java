package mvp.view.info;

import java.util.Collection;

public class Info implements InterfaceForInfo{
       private Object object;

    public Info() {
        this.object =null;
    }

    @Override
    public void show(Object object) {
            this.object=object;
    }

    @Override
    public String toString() {
        if (object instanceof String) {
            System.out.println(object);
            return null;
        } else if (object instanceof Number) {
            System.out.println(object);
            return null;
        } else if (object instanceof Collection<?> collection) {
            for (Object item : collection) {
                System.out.println(item);
            }
            return null;
        } else {
            System.out.println("Объект в неизвестном виде="+object);
            return null;
        }
    }

}
