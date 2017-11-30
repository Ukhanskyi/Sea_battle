/*
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
    private Image background, ship, killed, sea, icon;
    private JButton btnNewGame, btnExit, btnIGiveUp, btnMusic;
    private Bot computer;
    private User user;
    private Audio music = new Audio();
    int mode;
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
        btnNewGame.setBounds(130, 460, 150, 30);
        btnNewGame.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                user = new User();
                computer = new Bot();
                clear();
                NewGame new_game = new NewGame(me);
                mode = new_game.getMode();
                user.setBattlePlace(new_game.getPlace());
                Utils.refreshBattlePlace(user.getBattlePlace(),userPlaseArea);
                isGameStarted = true;
            }
        });
        add(btnNewGame);

// Кнопка " Я здаюсь "

        btnIGiveUp = new JButton();
        btnIGiveUp.setText("Я здаюсь");
        btnIGiveUp.setForeground(Color.BLUE);
        btnIGiveUp.setFont(new Font("serif", 0, 20));
        btnIGiveUp.setBounds(370, 460, 150, 30);
        btnIGiveUp.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку "Я здаюсь"

            public void actionPerformed(ActionEvent arg0) {
                if(!isGameStarted) return;
                clear();
                Utils.refreshBattlePlace(computer.getBattlePlace(),compPlaceArea);
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
        btnExit.setForeground(new Color(205, 0, 0));
        btnExit.setFont(new Font("monospace", Font.PLAIN, 20));
        btnExit.setBounds(610, 460, 150, 30);
        btnExit.addActionListener(new ActionListener() {

// обробник події при натиску на кнопку Вихід

            public void actionPerformed(ActionEvent arg0) {

// Вихід із гри -завершення роботи програми

                System.exit(0);
            }
        });
        add(btnExit);

        //Створюємо кнопку Music

        btnMusic = new JButton();
        btnMusic.setIcon( new ImageIcon("img/audio.jpg") );
        btnMusic.setForeground(Color.RED);
        btnMusic.setFont(new Font("serif", 0, 20));;
        btnMusic.setBounds(820, 10, 30, 30);
        btnMusic.addActionListener(new ActionListener() {
            public final void actionPerformed(ActionEvent arg0) {
                music.music();
            }
        });
        add(btnMusic);

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
                        if(userAttack(_i,_j)==0) {
                            WinChecker();
                            botAttack();
                        }
                        WinChecker();
                    }
                });
                add(compPlaceArea[i][j]);

                userPlaseArea[i][j] = new JButton();
                userPlaseArea[i][j].setForeground(Color.BLUE);
                userPlaseArea[i][j].setFont(new Font("serif", 0, 2));
                userPlaseArea[i][j].setBounds(490+j*30, 100+i*30, 30, 30);
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
        gr.setFont(new Font("Helvetica",Font.BOLD,30));
//Встановлення кольору
        gr.setColor(new Color(106, 90, 205));
//Виведення на дисплей
        gr.drawString("Комп'ютер", 150, 50);
        gr.drawString("Гравець", 590, 50);
//Встановлення шрифту
        gr.setFont(new Font("monospace", Font.PLAIN,15));
//Установка цвета
        gr.setColor(new Color(0, 0, 0));
//Введення цифр і букв
        for (int i = 1; i <= 10; i++)
        {
// Вивід цифр
            gr.drawString(""+i, 73, 93+i*30);
            gr.drawString(""+i, 463, 93+i*30);
// Вивід букв
            gr.drawString(""+(char)('A'+i-1), 78+i*30, 93);
            gr.drawString(""+(char)('A'+i-1), 468+i*30, 93);
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
        if(computer.isWin()){
            JOptionPane.showMessageDialog(null,
                    "You WIN",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if(user.isWin()){
            JOptionPane.showMessageDialog(null,
                    "You Loser",
                    "",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void botAttack(){
        switch (user.attack(mode)){
            case Miss:
                break;
            case Good:
                botAttack();
                break;
            case Bad:
                break;
        }
        drawStateUser();
    }

    private void drawStateUser(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                switch (user.getBattlePlace().getCellState(i,j)){
                    case Sea:
                        break;
                    case Ship:
                        break;
                    case ShipDamaged:
                        break;
                    case ShipKilled:
                        try {
                            Image img = ImageIO.read(new File("img/killed.png"));
                            userPlaseArea[i][j].setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case Miss:
                        try {
                            Image img = ImageIO.read(new File("img/miss.png"));
                            userPlaseArea[i][j].setIcon(new ImageIcon(img));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case Border:
                        break;
                }
            }
        }
    }

    private int userAttack(int i, int j){
        int state = 0;
        switch (computer.attack(i,j)){
            case Miss:
                state = 0;
                break;
            case Good:
                state = 1;
                break;
            case Bad:
                state = 2;
                break;
        }
        try {
            Image img;
            switch (computer.getBattlePlace().getCellState(i,j)){
                case Sea:
                    img = ImageIO.read(new File("img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Ship:
                    img = ImageIO.read(new File("img/ship.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case ShipDamaged:
                    img = ImageIO.read(new File("img/killed.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case ShipKilled:
                    img = ImageIO.read(new File("img/killed.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Miss:
                    img = ImageIO.read(new File("img/miss.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Border:
                    img = ImageIO.read(new File("img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return state;
    }
}*/
