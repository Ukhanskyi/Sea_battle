package seabattlepack.logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Utils {
    public void refreshBattlePlace(BattlePlace place, JButton[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Image img;
                    switch (place.getCellState(i, j)) {
                        case Sea:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Ship:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case ShipDamaged:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case ShipKilled:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/killed.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Miss:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Border:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printHint(BattlePlace place, JButton[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Image img;
                    switch (place.getCellState(i, j)) {
                        case Miss:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}