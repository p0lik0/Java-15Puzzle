import java.awt.Color;

import javax.swing.JFrame;

public class CustomFrame extends JFrame {
    public CustomFrame() {
        GamePanel gamePanel = new GamePanel(); // создаём панель с плитками
        add (gamePanel); // добавляем панель с плитками в окно
        setJMenuBar(new Menu(this, gamePanel)); // добавляем в окно панель меню

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // устанавливаем завершение программы при закрытии
        setSize(400,400); // устанавливаем размер окна

        setLocationRelativeTo(null); // размещаем окно по центру
        setResizable(false); // запрещаем менять размер окна
        setVisible(true); // делаем окно видимым
    }
}