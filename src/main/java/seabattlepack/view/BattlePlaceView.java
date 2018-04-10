package seabattlepack.view;

import seabattlepack.logic.Bot;
import seabattlepack.logic.User;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BattlePlaceView extends JPanel implements Serializable {

    private static final long serialVersionUID = 1905122041950251207L;


    private Timer tmDraw;
    private Image background;
    private JButton btnNewGame, btnExit, btnIGiveUp, btnMusicPlay, btnHint;
    private Bot computer;
    private User user;
    private Sound music = new Sound();
    private Utils utils = new Utils();
    private boolean playStop = false;
    private int mode;
    private JButton[][] compPlaceArea = new JButton[10][10];
    private JButton[][] userPlaseArea = new JButton[10][10];
    private boolean isGameStarted = false;
    private static final String EXEPT = "an exception was thrown";

    void playAudio() {
        music.play();
    }

    public BattlePlaceView(JFrame me) {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/img/background.png"));
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
                utils.refreshBattlePlace(user.getBattlePlace(), userPlaseArea);
                btnIGiveUp.setEnabled(true);
                btnHint.setEnabled(true);
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
        btnIGiveUp.setEnabled(false);
        btnIGiveUp.addActionListener(new ActionListener() {

//Обрабка події при натисканні на кнопку "Я здаюсь"

            public void actionPerformed(ActionEvent arg0) {
                if (!isGameStarted) return;
                clear();
                utils.refreshBattlePlace(computer.getBattlePlace(), compPlaceArea);
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
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        add(btnExit);

        btnHint = new JButton();
        btnHint.setIcon(new ImageIcon(getClass().getResource("/img/hint.png")));
        btnHint.setBounds(850, 520, 30, 30);
        btnHint.setEnabled(false);
        btnHint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                computer.hint();
                utils.printHint(computer.getBattlePlace(), compPlaceArea);
            }
        });
        add(btnHint);

//Створюємо кнопку Music
        btnMusicPlay = new JButton();
        btnMusicPlay.setIcon(new ImageIcon(getClass().getResource("/img/audio.jpg")));
        btnMusicPlay.setBounds(850, 10, 30, 30);
        btnMusicPlay.addActionListener(new ActionListener() {
            public final void actionPerformed(ActionEvent arg0) {
                if (playStop) {
                    music.play();
                    playStop = false;
                    btnMusicPlay.setIcon(new ImageIcon(getClass().getResource("/img/audio.jpg")));
                } else {
                    music.stop();
                    playStop = true;
                    btnMusicPlay.setIcon(new ImageIcon(getClass().getResource("/img/noaudio.jpg")));

                }
            }
        });
        add(btnMusicPlay);

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                compPlaceArea[i][j] = new JButton();
                compPlaceArea[i][j].setFont(new Font("serif", 0, 2));
                compPlaceArea[i][j].setBounds(100 + j * 30, 100 + i * 30, 30, 30);
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                }
                final int _i = i;
                final int _j = j;
                compPlaceArea[i][j].addActionListener(new ActionListener() {


                    public void actionPerformed(ActionEvent arg0) {
                        if (!isGameStarted) {
                            JOptionPane.showMessageDialog(me,
                                    "Game not started",
                                    "Game",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (userAttack(_i, _j) == 0) {
                            winChecker();
                            botAttack();
                        }
                        winChecker();
                    }
                });
                add(compPlaceArea[i][j]);

                userPlaseArea[i][j] = new JButton();
                userPlaseArea[i][j].setForeground(Color.BLUE);
                userPlaseArea[i][j].setFont(new Font("serif", 0, 2));
                userPlaseArea[i][j].setBounds(490 + j * 30, 100 + i * 30, 30, 30);
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                    userPlaseArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                }
                userPlaseArea[i][j].addActionListener(new ActionListener() {

//Обрабка події

                    public void actionPerformed(ActionEvent arg0) {

                    }
                });
                add(userPlaseArea[i][j]);
            }
    }

//Графічний метод

    public void paintComponent(Graphics gr) {
//Малювання фону
        gr.drawImage(background, 0, 0, 900, 600, null);
//Встановлення шрифта
        gr.setFont(new Font("Helvetica", Font.BOLD, 30));
//Встановлення кольору
        gr.setColor(new Color(106, 90, 205));
//Виведення на дисплей
        gr.drawString("Комп'ютер", 150, 50);
        gr.drawString("Гравець", 590, 50);
//Встановлення шрифту
        gr.setFont(new Font("monospace", Font.PLAIN, 15));
//Установка цвета
        gr.setColor(new Color(0, 0, 0));
//Введення цифр і букв
        for (int i = 1; i <= 10; i++) {
// Вивід цифр
            gr.drawString("" + i, 73, 93 + i * 30);
            gr.drawString("" + i, 463, 93 + i * 30);
// Вивід букв
            gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
            gr.drawString("" + (char) ('A' + i - 1), 468 + i * 30, 93);
        }
    }

    private void clear() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                try {
                    Image img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                }
    }

    private void winChecker() {
        if (computer.isWin()) JOptionPane.showMessageDialog(null,
                "You WIN",
                "",
                JOptionPane.PLAIN_MESSAGE);
        if (user.isWin()) JOptionPane.showMessageDialog(null,
                "You Loser",
                "",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void botAttack() {
        switch (user.attack(mode)) {
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

    public void drawStateUser() {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                switch (user.getBattlePlace().getCellState(i, j)) {
                    case Sea:
                        break;
                    case Ship:
                        break;
                    case ShipDamaged:
                        try {
                            Image img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                            userPlaseArea[i][j].setIcon(new ImageIcon(img));
                        } catch (Exception e) {
                            Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                        }
                        break;
                    case ShipKilled:
                        try {
                            Image img = ImageIO.read(getClass().getResourceAsStream("/img/killed.png"));
                            userPlaseArea[i][j].setIcon(new ImageIcon(img));
                        } catch (Exception e) {
                            Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                        }
                        break;
                    case Miss:
                        try {
                            Image img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            userPlaseArea[i][j].setIcon(new ImageIcon(img));
                        } catch (Exception e) {
                            Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                        }
                        break;
                    case Border:
                        break;
                }
    }

    public int userAttack(int i, int j) {
        int state = 0;
        switch (computer.attack(i, j)) {
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
            switch (computer.getBattlePlace().getCellState(i, j)) {
                case Sea:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Ship:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case ShipDamaged:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case ShipKilled:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/killed.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Miss:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
                case Border:
                    img = ImageIO.read(getClass().getResourceAsStream("/img/border.png"));
                    compPlaceArea[i][j].setIcon(new ImageIcon(img));
                    break;
            }
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
        }
        for (int I = 0; I < 10; I++)
            for (int J = 0; J < 10; J++)
                switch (computer.getBattlePlace().getCellState(I, J)) {
                    case Sea:
                        break;
                    case Ship:
                        break;
                    case ShipDamaged:
                        break;
                    case ShipKilled:
                        break;
                    case Miss:
                        try {
                            Image img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            compPlaceArea[I][J].setIcon(new ImageIcon(img));
                        } catch (Exception e) {
                            Logger.getAnonymousLogger().log(Level.SEVERE, EXEPT, e);
                        }
                        break;
                    case Border:
                        break;
                }
        return state;
    }
}