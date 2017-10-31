package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class BattlePlaceView extends JPanel {

    private Timer tmDraw;
    private Image background, ship, ubit, sea, icon;
    private JButton btnNewGame, btnExit, btnIGiveUp;
    private User user;
    private Bot computer;
    public JButton[][] compPlaceArea = new JButton[10][10];
    public JButton[][] userPlaseArea = new JButton[10][10];

    private boolean isGameStarted = false;

    public BattlePlaceView(JFrame me) {
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

        btnNewGame = new JButton();
        btnNewGame.setText("Нова гра");
        btnNewGame.setForeground(Color.BLUE);
        btnNewGame.setFont(new Font("serif", 0, 20));
        btnNewGame.setBounds(130, 450, 150, 40);
        btnNewGame.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку Нова гра

            public void actionPerformed(ActionEvent arg0) {
                user = new User();
                computer = new Bot();
                clear();
                NewGame new_game = new NewGame(me);
                user.setBattlePlace(new_game.getPlace());
                user.debug_print(userPlaseArea);
                isGameStarted = true;
            }
        });
        add(btnNewGame);

// Кнопка " Я здаюсь "

        btnIGiveUp = new JButton();
        btnIGiveUp.setText("Я здаюсь");
        btnIGiveUp.setForeground(Color.BLUE);
        btnIGiveUp.setFont(new Font("serif", 0, 20));
        btnIGiveUp.setBounds(375, 450, 150, 40);
        btnIGiveUp.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку "Я здаюсь"

            public void actionPerformed(ActionEvent arg0) {
                if(!isGameStarted) return;
                clear();
                computer.debug_print(compPlaceArea);
                isGameStarted = false;
                JOptionPane.showMessageDialog(null,
                        "You Loser",
                        "",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        add(btnIGiveUp);

//Створюєм кнопку Вихід

        btnExit = new JButton();
        btnExit.setText("Вихід");
        btnExit.setForeground(Color.RED);
        btnExit.setFont(new Font("serif", 0, 20));
        btnExit.setBounds(620, 450, 150, 40);
        btnExit.addActionListener(new ActionListener() {

// обробник події при натиску на кнопку Вихід

            public void actionPerformed(ActionEvent arg0) {

// Вихід із гри -завершення роботи програми

                System.exit(0);
            }
        });
        add(btnExit);

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                compPlaceArea[i][j] = new JButton();
                compPlaceArea[i][j].setFont(new Font("serif", 0, 2));
                compPlaceArea[i][j].setBounds(100+j*30, 100+i*30, 30, 30);
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                final int _i = i;
                final int _j = j;
                compPlaceArea[i][j].addActionListener(new ActionListener() {


                    public void actionPerformed(ActionEvent arg0) {
                        if(!isGameStarted)
                        {
                            JOptionPane.showMessageDialog(me,
                                    "Game not started",
                                    "Game",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int res = computer.Attack(_i,_j);
                        if(res < 0) {WinChecker(); return;}
                        try {
                            if(res == 0) {
                                Image img = ImageIO.read(new File("img/miss.png"));
                                ((JButton) arg0.getSource()).setIcon(new ImageIcon(img));
                            }
                            else{
                                Image img = ImageIO.read(new File("img/ubit.png"));
                                ((JButton) arg0.getSource()).setIcon(new ImageIcon(img));
                                WinChecker();
                                return;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        while(true)
                        {
                            int i = (int)(Math.random() * 10);
                            int j = (int)(Math.random() * 10);
                            res = user.Attack(i,j);
                            if(res < 0) continue;
                            if(res == 0)
                                try {
                                    Image img = ImageIO.read(new File("img/miss.png"));
                                    userPlaseArea[i][j].setIcon(new ImageIcon(img));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            else
                                try {
                                    Image img = ImageIO.read(new File("img/ubit.png"));
                                    userPlaseArea[i][j].setIcon(new ImageIcon(img));
                                    continue;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            break;
                        }
                        WinChecker();
                    }
                });
                add(compPlaceArea[i][j]);

                userPlaseArea[i][j] = new JButton();
                userPlaseArea[i][j].setForeground(Color.BLUE);
                userPlaseArea[i][j].setFont(new Font("serif", 0, 2));
                userPlaseArea[i][j].setBounds(500+j*30, 100+i*30, 30, 30);
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    userPlaseArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                userPlaseArea[i][j].addActionListener(new ActionListener() {

//Обрабка події

                    public void actionPerformed(ActionEvent arg0) {

                    }
                });
                add(userPlaseArea[i][j]);
            }
        }
    }

//Графічний метод

    public void paintComponent(Graphics gr) {
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

    private void clear(){
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private void WinChecker(){
        if(computer.IsWin()){
            JOptionPane.showMessageDialog(null,
                    "You WIN",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if(user.IsWin()){
            JOptionPane.showMessageDialog(null,
                    "You Loser",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }
}