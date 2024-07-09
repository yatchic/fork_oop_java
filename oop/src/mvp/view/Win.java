package mvp.view;
import java.awt.*;
import java.awt.event.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.text.JTextComponent;

 public class Win implements View{





public JFrame window(int w, int h) {
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(w, h);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    @Override
    public   void add(JFrame window, JComponent component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        window.setLayout(null);
        window.getContentPane().add(component);
        window.validate();
        window.repaint();
    }
    @Override
    public   JButton button(String text) {
        JButton button = new JButton(text);
        return button;
    }
    @Override
    public   JScrollPane multiLineText(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        return scrollPane;
    }
    @Override
    public void mouseEvents(JComponent component, String typeButtonMouse, String action, Consumer<MouseEvent> retAction) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Integer.toString(e.getButton()).equals(typeButtonMouse)) {
                    switch (action) {
                        case "single click":
                            if (e.getClickCount() == 1) retAction.accept(e);
                            break;
                        case "double click":
                            if (e.getClickCount() == 2) retAction.accept(e);
                            break;
                        case "triple click":
                            if (e.getClickCount() == 3) retAction.accept(e);
                            break;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Integer.toString(e.getButton()).equals(typeButtonMouse) && action.equals("pressed")) retAction.accept(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (Integer.toString(e.getButton()).equals(typeButtonMouse) && action.equals("released")) retAction.accept(e);
            }
        });

        component.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (action.equals("movement")) retAction.accept(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (action.equals("drag")) retAction.accept(e);
            }
        });
    }

    public   void keyEvents(JComponent component, String key, Consumer<KeyEvent> retAction) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(key);
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, key);
        component.getActionMap().put(key, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KeyEvent keyEvent = new KeyEvent(component, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyStroke.getKeyCode(), keyStroke.getKeyChar());
                retAction.accept(keyEvent);
            }
        });
    }

    public   void setText(Component component, String text) {
        SwingUtilities.invokeLater(() -> {
            if (component instanceof JButton) {
                ((JButton) component).setText(text);
            } else if (component instanceof JLabel) {
                ((JLabel) component).setText(text);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setText(text);
            } else if (component instanceof JTextField) {
                ((JTextField) component).setText(text);
            } else if (component instanceof JFrame) {
                ((JFrame) component).setTitle(text);
            } else if (component instanceof JScrollPane) {
                Component viewportView = ((JScrollPane) component).getViewport().getView();
                if (viewportView instanceof JTextArea) {
                    ((JTextArea) viewportView).setText(text);
                } else {
                    throw new IllegalArgumentException("Unsupported component type");
                }
            } else {
                throw new IllegalArgumentException("Unsupported component type");
            }
        });
    }

    public   void addResizeListener(JFrame frame, BiConsumer<Integer, Integer> action) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();
                action.accept(newSize.width, newSize.height);
            }
        });
    }

    public   void resizeListenerForFrame(JFrame frame, BiConsumer<Integer, Integer> action) {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension newSize = e.getComponent().getSize();
                action.accept(newSize.width, newSize.height);
            }
        });
    }

    public   void setTextAndColor(Component component, String text, String color) {
        Color actualColor = color.isBlank() ? Color.BLACK : Color.decode("#" + color);
        SwingUtilities.invokeLater(() -> {
            if (component instanceof JButton) {
                ((JButton) component).setText(text);
                ((JButton) component).setForeground(actualColor);
            } else if (component instanceof JLabel) {
                ((JLabel) component).setText(text);
                ((JLabel) component).setForeground(actualColor);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setText(text);
                ((JTextArea) component).setForeground(actualColor);
            } else if (component instanceof JTextField) {
                ((JTextField) component).setText(text);
                ((JTextField) component).setForeground(actualColor);
            } else if (component instanceof JFrame) {
                ((JFrame) component).setTitle(text);
                // Цвет текста заголовка окна не может быть изменен в Swing
            } else if (component instanceof JScrollPane) {
                Component viewportView = ((JScrollPane) component).getViewport().getView();
                if (viewportView instanceof JTextArea) {
                    ((JTextArea) viewportView).setText(text);
                    ((JTextArea) viewportView).setForeground(actualColor);
                } else {
                    throw new IllegalArgumentException("Unsupported component type");
                }
            } else {
                throw new IllegalArgumentException("Unsupported component type");
            }
        });
    }

    public   String getTextFromScrollPane(JScrollPane scrollPane) {
        Component view = scrollPane.getViewport().getView();
        if (view instanceof JTextComponent) {
            return ((JTextComponent) view).getText();
        } else {
            throw new IllegalArgumentException("Unsupported component type");
        }
    }

    public   void changeTextColor(JComponent component, String color) {
        Color actualColor = color.isBlank() ? Color.BLACK : Color.decode(color.startsWith("#") ? color : "#" + color);
        SwingUtilities.invokeLater(() -> {
            if (component instanceof JButton) {
                ((JButton) component).setForeground(actualColor);
            } else if (component instanceof JLabel) {
                ((JLabel) component).setForeground(actualColor);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setForeground(actualColor);
            } else if (component instanceof JTextField) {
                ((JTextField) component).setForeground(actualColor);
            } else if (component instanceof JScrollPane) {
                Component view = ((JScrollPane) component).getViewport().getView();
                if (view instanceof JTextComponent) {
                    ((JTextComponent) view).setForeground(actualColor);
                } else {
                    throw new IllegalArgumentException("Unsupported component type");
                }
            } else {
                throw new IllegalArgumentException("Unsupported component type");
            }
        });
    }

    public   void changeTextSize(JComponent component, float size) {
        SwingUtilities.invokeLater(() -> {
            if (component instanceof JButton) {
                component.setFont(component.getFont().deriveFont(size));
            } else if (component instanceof JLabel) {
                component.setFont(component.getFont().deriveFont(size));
            } else if (component instanceof JTextArea) {
                component.setFont(component.getFont().deriveFont(size));
            } else if (component instanceof JTextField) {
                component.setFont(component.getFont().deriveFont(size));
            } else if (component instanceof JScrollPane) {
                Component view = ((JScrollPane) component).getViewport().getView();
                if (view instanceof JTextComponent) {
                    ((JTextComponent) view).setFont(view.getFont().deriveFont(size));
                } else {
                    throw new IllegalArgumentException("Unsupported component type");
                }
            } else {
                throw new IllegalArgumentException("Unsupported component type");
            }
        });
    }


}
