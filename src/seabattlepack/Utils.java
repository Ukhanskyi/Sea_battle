package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Utils {
    static void refreshBattlePlace(BattlePlace place, JButton[][] array){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Image img;
                    switch (place.getCellState(i,j)){
                        case Sea:
                            img = ImageIO.read(new File("img/sea.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Ship:
                            img = ImageIO.read(new File("img/ship.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case ShipDamaged:
                            img = ImageIO.read(new File("img/damaged.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case ShipKilled:
                            img = ImageIO.read(new File("img/ubit.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Miss:
                            img = ImageIO.read(new File("img/miss.png"));
                            array[i][j].setIcon(new ImageIcon(img));
                            break;
                        case Border:
                            img = ImageIO.read(new File("img/sea.png"));
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
