package mvp.view;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public interface View {
     JFrame window(int w, int h);

    void add(JFrame window, JComponent component, int x, int y, int width, int height);

    JButton button(String text);

    JScrollPane multiLineText(String text);

    void mouseEvents(JComponent component, String typeButtonMouse, String action, Consumer<MouseEvent> retAction);

}
