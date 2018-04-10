package seabattlepack.view;

import seabattlepack.logic.BattlePlace;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private static final String EXEP = "an exception was thrown";
    public void refreshBattlePlace(BattlePlace place, JButton[][] array) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Image img;
                    switch (place.getCellState(i, j)) {
                        case sea:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case ship:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case shipDamaged:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/ship.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case shipKilled:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/killed.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case miss:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case border:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/sea.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                    }
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEP, e);
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
                        case miss:
                            img = ImageIO.read(getClass().getResourceAsStream("/img/miss.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                    }
                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, EXEP, e);
                }
            }
        }
    }
}