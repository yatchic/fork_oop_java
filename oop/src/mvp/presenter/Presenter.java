package mvp.presenter;

import mvp.view.ConsoleTerminal;
import mvp.view.Win;

import javax.swing.*;

public class Presenter {

    public void win(int w,int h) {
        Win wn=new Win();
        JFrame frame = wn.window(w, h);

        // Создаем две кнопки
        JButton button1 = wn.button("Button 1");
        JButton button2 = wn.button("Button 2");

        // Создаем два многострочных текстовых поля
        JScrollPane textArea1 = wn.multiLineText("Text Area 1");
        JScrollPane textArea2 = wn.multiLineText("Text Area 2");

        // Добавляем кнопки и текстовые поля на окно
        wn.add(frame, button1, 20, 20, 100, 50);
        wn.add(frame, button2, 20, 80, 100, 50);
        wn.add(frame, textArea1, 140, 20, 480, 200);
        wn.add(frame, textArea2, 140, 240, 480, 200);

        // Добавляем обработчик событий для изменения размера окна
        wn.addResizeListener(frame, (width, height) -> {
            // Перерисовываем кнопки и текстовые поля в зависимости от нового размера окна
            button1.setBounds(20, 20, width / 2 - 30, 50);
            button2.setBounds(width / 2 + 10, 20, width / 2 - 30, 50);
            textArea1.setBounds(20, 80, width - 40, height / 2 - 90);
            textArea2.setBounds(20, height / 2 + 10, width - 40, height / 2 - 90);
        });
    }



}
