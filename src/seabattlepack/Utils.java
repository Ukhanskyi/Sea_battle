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
                    if (place.getPosValue(i,j) == 0) {
                        Image img = ImageIO.read(new File("img/sea.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    }
                    else if(place.getPosValue(i,j) > 0){
                        Image img = ImageIO.read(new File("img/ship.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    } else if(place.getPosValue(i,j) != -10){
                        Image img = ImageIO.read(new File("img/ubit.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    } else{
                        Image img = ImageIO.read(new File("img/miss.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
