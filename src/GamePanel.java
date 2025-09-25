import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    public class Tile extends JButton{
        public Tile(int i){
            super(String.valueOf(i)); // присваиваем плитке значение
            setFont(new Font("Arial", Font.PLAIN, 56 )); // устанавливаем шрифт
            setFocusable(false);
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            // addActionListener(new ActionListener() {
            //     public void actionPerformed(ActionEvent e){
            //         if ((Math.abs(x-emptyTileX) == 1 && (y == emptyTileY)) || (Math.abs(y-emptyTileY) == 1 && (x == emptyTileX)))
            //         swap(x,y);
            //     }
            // });

        }
    }

    private int emptyTileX; // индекс пустой плитки
    private int emptyTileY; // индекс пустой плитки
    private static int size = 4;
    private Tile[][] tiles; // массив плиток
    private int[] values; // массив, хранящий значения плиток

    public GamePanel() {
        super(new GridLayout (size,size)); // задаём менеджер компановки
        tiles = new Tile[size][size]; // массив плиток
        values = new int[16]; // массив значений всех плиток
        for (int i = 0; i<16 ; i++) // заполняем массив значеий значениями от 1 до 15
            values[i] = i;
        initTiles(); // инициализируем плитки
    }
    private void initTiles(){
        for (int i = 0; i<16 ; i++){ // перемешиваем массив значений
            int n = new Random().nextInt(15); // определяем индекс рандомного эл-та с которым будем менять местами текущий эл-т
            int temp = values[i];
            values[i] = values[n];
            values[n] = temp;
        }
        for (int i = 0; i<16 ; i++){ // для каждой плитки
            int x = i / size; // определяем индекс строки эл-та
            int y = i % size; // определяем индекс столбца эл-та
            tiles[x][y] = new Tile (values[i]); // в массиве плиток на найденном месте создаём новую плитку с текущим значением i 
            tiles[x][y].addActionListener(e -> {
                if ((Math.abs(x-emptyTileX) == 1 && (y == emptyTileY)) || (Math.abs(y-emptyTileY) == 1 && (x == emptyTileX)))
                    swap(x,y);
            }); // добавляем обработчик события, при срабатываниии которого эта плитка меняется с пустой

            if (values[i] == 0){ // если текущее значение i окзывается равным 0
                emptyTileX = i/size; // сохраняем в переменную строку и столбец пустой плитки
                emptyTileY = i%size;
                tiles[x][y].setVisible(false); // скрываем эту плитку 
            }
            add(tiles[x][y]); // добавляем созданную кнопку в контейнер
        }
        // после создания всех кнопок, меняем пустую плитку с плиткой, стоящей в правом нижнем углу
        swap(3,3);

        addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_LEFT && emptyTileY+1<size) // если нажата <- и плитка справа существует
                    swap(emptyTileX,emptyTileY+1); // то плитку справа от пустой двигаем ВЛЕВО
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && emptyTileY-1>=0) // если нажата -> и плитка слева существует
                    swap(emptyTileX,emptyTileY-1); // то плитку слева от пустой двигаем ВПРАВО
                if (e.getKeyCode() == KeyEvent.VK_UP && emptyTileX+1<size)  // если нажата /\ и плитка снизу существует
                    swap(emptyTileX+1,emptyTileY); //  то плитку снизу от пустой двигаем ВВЕРХ
                if (e.getKeyCode() == KeyEvent.VK_DOWN && emptyTileX-1>=0) // если нажата \/ и плитка сверху существует
                    swap(emptyTileX-1,emptyTileY); //  то плитку сверху от пустой двигаем ВНИЗ
            }});
        setFocusable(true);
    }

    private void swap(int x0, int y0) { // метод, меняющий пустую плитку с плиткой, координаты которой поданы в параметры
        tiles[emptyTileX][emptyTileY].setText(tiles[x0][y0].getText()); // плитки меняются значениями
        tiles[x0][y0].setText("0");
        tiles[emptyTileX][emptyTileY].setVisible(true); // бывшая пустой становится видимой
        tiles[x0][y0].setVisible(false); // новая пустая становится невидимой
        emptyTileX = x0; // обновляем координаты пустой плитки
        emptyTileY = y0;
        if (itsVictory()) // если это победа, выводим окно с поздравлением
            JOptionPane.showMessageDialog(null, "Congrats!", "You won!", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean itsVictory(){ // метод проверяющий победная ли эта позиция
        for (int i = 0; i<15; i++){ // с 1й по предпоследнюю включительно
            int x = i / size;
            int y = i % size;
            if (tiles[x][y].getText().equals(String.valueOf(i+1))==false) // если её индекс (начиная с единицы) не равен её значению
                return false; //это позиция точно не победная
        }
        return true; // если пройдя все плитки от 1й до предпоследней не вернулось false, то эта позиция победная 
    }

    public void makeVictory(){ // метод, расставляющий плитки попорядку
        for (int i = 0; i<15; i++){ // с 1й по предпоследнюю включительно
            int x = i / size;
            int y = i % size;
            tiles[x][y].setText(String.valueOf(i+1)); // устанавливаем значение плитки равное её индексу+1
            tiles[x][y].setVisible(true); // делаем видимой
        }
        tiles[size-1][size-1].setText("0"); // последняя плитка - нулевая
        tiles[size-1][size-1].setVisible(false); // делаем её невидимой
        emptyTileX = 3; // меняем индекс пустой плитки
        emptyTileY = 3;
        setFocusable(true); 
    }

    public void startNewGame(){
        for (int i = 0; i<16 ; i++){ // перемешиваем массив значений
            int n = new Random().nextInt(15); // определяем индекс рандомного эл-та с которым будем менять местами текущий эл-т
            int temp = values[i];
            values[i] = values[n];
            values[n] = temp;
        }
        for (int i = 0; i<16 ; i++){ // для каждой плитки
            int x = i / size; // определяем индекс строки эл-та
            int y = i % size; // определяем индекс столбца эл-та
            tiles[x][y].setText(String.valueOf(values[i]));
            tiles[x][y].setVisible(true);

            if (values[i] == 0){ // если текущее значение i окзывается равным 0
                emptyTileX = i/size; // сохраняем в переменную строку и столбец пустой плитки
                emptyTileY = i%size;
                tiles[x][y].setVisible(false); // скрываем эту плитку 
            }
        }
        swap(3,3);
    }
}