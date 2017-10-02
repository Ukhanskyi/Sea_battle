import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class Battlefield1 extends JPanel {

    private Timer tmDraw;
    private Image background, ship, ubit, sea, icon;
    private JButton btn1, btn2, btn3;
    private Bot myGP;
    private BattlePlace myBP;
    public JButton[][] placearea = new JButton[10][10];
    public JButton[][] myplacearea = new JButton[10][10];



    public Battlefield1() {
        try {
            background = ImageIO.read(new File("img/background.png"));
        } catch (Exception ex) {
        }

        tmDraw = new Timer(50, new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent arg0) {

                repaint();
            }
        });

        tmDraw.start();

        setLayout(null);

//Створюєм кнопку Нова гра

        btn1 = new JButton();
        btn1.setText("Нова гра");
        btn1.setForeground(Color.BLUE);
        btn1.setFont(new Font("serif", 0, 20));
        btn1.setBounds(130, 450, 150, 40);
        btn1.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку Нова гра

            public void actionPerformed(ActionEvent arg0) {
                myGP = new Bot();
                myGP.Bot();
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 10; j++){
                        try {
                            Image img = ImageIO.read(new File("img/sea.png"));
                            placearea[i][j].setIcon(new ImageIcon(img));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
                myGP.debug_print(myplacearea);
            }
        });
        add(btn1);

// Кнопка " Я здаюсь "

        btn3 = new JButton();
        btn3.setText("Я здаюсь");
        btn3.setForeground(Color.BLUE);
        btn3.setFont(new Font("serif", 0, 20));
        btn3.setBounds(375, 450, 150, 40);
        btn3.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку "Я здаюсь"

            public void actionPerformed(ActionEvent arg0) {

                myGP = new Bot();
                myGP.Bot();
                for (int i = 0; i < 10; i++){
                    for (int j = 0; j < 10; j++){
                        try {
                            Image img = ImageIO.read(new File("img/sea.png"));
                            placearea[i][j].setIcon(new ImageIcon(img));
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }

                myGP.debug_print(placearea);
            }
        });
        add(btn3);

//Створюєм кнопку Вихід

        btn2 = new JButton();
        btn2.setText("Вихід");
        btn2.setForeground(Color.RED);
        btn2.setFont(new Font("serif", 0, 20));
        btn2.setBounds(620, 450, 150, 40);
        btn2.addActionListener(new ActionListener() {

// обробник події при натиску на кнопку Вихід

            public void actionPerformed(ActionEvent arg0) {

// Вихід із гри -завершення роботи програми

                System.exit(0);
            }
        });
        add(btn2);

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                placearea[i][j] = new JButton();
                placearea[i][j].setFont(new Font("serif", 0, 2));
                placearea[i][j].setBounds(100+j*30, 100+i*30, 30, 30);
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    placearea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                placearea[i][j].addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку Нова гра

                    public void actionPerformed(ActionEvent arg0) {

                    }
                });
                add(placearea[i][j]);

                myplacearea[i][j] = new JButton();
                myplacearea[i][j].setForeground(Color.BLUE);
                myplacearea[i][j].setFont(new Font("serif", 0, 2));
                myplacearea[i][j].setBounds(500+j*30, 100+i*30, 30, 30);
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    myplacearea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                myplacearea[i][j].addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку Нова гра

                    public void actionPerformed(ActionEvent arg0) {

                    }
                });
                add(myplacearea[i][j]);
            }
        }
    }

//Графічний метод

        public void paintComponent(Graphics gr)
        {
//Малювання фону
            gr.drawImage(background,0,0,900,600,null);
//Встановлення шрифта
            gr.setFont(new Font("serif",2,40));
//Встановлення кольору
            gr.setColor(Color.BLACK);
//Виведення на дисплей
            gr.drawString("Комп'ютер", 150, 50);
            gr.drawString("Гравець", 590, 50);
//Встановлення шрифту
            gr.setFont(new Font("serif",0,20));
//Установка цвета
            gr.setColor(Color.RED);
//Введення цифр і букв
            for (int i = 1; i <= 10; i++)
            {
// Вивід цифр
                gr.drawString(""+i, 73, 93+i*30);
                gr.drawString(""+i, 473, 93+i*30);
// Вивід букв
                gr.drawString(""+(char)('A'+i-1), 78+i*30, 93);
                gr.drawString(""+(char)('A'+i-1), 478+i*30, 93);
            }
        }

}