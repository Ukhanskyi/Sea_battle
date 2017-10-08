package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Zen on 13.09.2017.
 */
public class BattlePlace {

    class InvalidPosition extends Throwable{
        InvalidPosition(){}
    }

    private int[][] place = new int[10][10];


    void BattlePlace(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                place[i][j]=0;
    }
    

    public void SetSized(int x, int y, int size, boolean direction) throws InvalidPosition{
        int[][] place = this.place;
        int x_max,y_max;
        if(direction){
            if(x<0 || x>9-(size-1)) throw new InvalidPosition();
            if(y<0 || y>9) throw new InvalidPosition();
            x_max = x+size;
            y_max = y+2;
        }
        else {
            if(x<0 || x>9) throw new InvalidPosition();
            if(y<0 || y>9-(size-1)) throw new InvalidPosition();
            x_max = x+2;
            y_max = y+size;
        }
        checkPlace(x, y, x_max, y_max);

        for(int i=0;i<size;i++){
            if(direction)
                place[x+i][y]=size;
            else
                place[x][y+i]=size;
        }
        this.place = place;
    }

    private void checkPlace(int x, int y, int x_max, int y_max) throws InvalidPosition{
        for(int i=x-1;i<x_max + 1;i++){
            for(int j = y-1;j<y_max + 1;j++){
                if(i<10 && j<10 && i>=0 && j>=0){
                    if(place[i][j]!=0) throw new InvalidPosition();
                }
            }
        }
    }

    void debug_print(JButton[][] array){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(place[i][j]!=0) {
                    try {
                        Image img = ImageIO.read(new File("img/ship.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }  else {
                    try {
                    Image img = ImageIO.read(new File("img/sea.png"));
                    array[i][j].setIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                }
            }
        }
    }
}
