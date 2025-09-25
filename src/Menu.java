import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class Menu extends JMenuBar{ // наследуемся от JMenuBar, объекты класса которого содержат меню верхнего уровня для приложения
	public Menu(JFrame frame, GamePanel panel) {

		JMenu file = new JMenu("File"); // создаём подменю File главного меню
			file.setMnemonic('F'); // присваиваем мнемонику
			add(file); // добавляем в меню
			JMenuItem New = new JMenuItem("New"); // создаём пункт в подменю File
				New.setMnemonic('N'); // присваиваем пункту мнемонику 
				New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)); // присваиваем акселератор
				New.addActionListener(e -> panel.startNewGame()); // добавляем к пункту слушателя действия, который обрабатывая событие начинает новую игру
				file.add(New); // добавляем пункт New в подменю File
				file.addSeparator(); // добавляем разделитель
			JMenuItem exit = new JMenuItem("Exit");// создаём пункт в подменю File
				exit.setMnemonic('E'); // присваиваем пункту мнемонику
				exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK)); // присваиваем акселератор
				exit.addActionListener(e -> System.exit(0));// добавляем к пункту слушателя действия, который обрабатывая событие завершает приложение
				file.add(exit);// добавляем пункт exit в подменю File
				file.addSeparator(); // добавляем разделитель
			JMenuItem victory = new JMenuItem("Victory!");
				victory.setMnemonic('V'); // присваиваем пункту мнемонику
				victory.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));// присваиваем акселератор
				victory.addActionListener(e -> panel.makeVictory());// добавляем к пункту слушателя действия, который обрабатывая событие расставляет плитки в нужном для победы порядке
				file.add(victory);// добавляем пункт exit в подменю File

        JMenu help = new JMenu("Help");// создаём подменю help главного меню
			help.setMnemonic('H');// присваиваем пункту мнемонику
			add(help); // добавляем в меню
			JMenuItem about = new JMenuItem("About"); // создаём пункт в подменю help
				about.setMnemonic('A');// присваиваем пункту мнемонику
				about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));// присваиваем акселератор
				about.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Poliko"));
				// добавляем к пункту слушателя действия, который обрабатывая событие выводит модальное диалоговое окно с сообщением
				help.add(about);// добавляем пункт exit в подменю help
	}
}
