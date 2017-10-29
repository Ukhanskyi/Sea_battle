package seabattlepack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Zen on 13.09.2017.
 */
public class BattlePlace {

    class InvalidPosition extends Throwable{
        InvalidPosition(){}
    }

    private int[][] place = new int[10][10];

    private int s[] = new int[4];

    void BattlePlace(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                place[i][j]=0;
        for(int i =0;i < 4;i++)
            s[i] = 0;
    }


    public void SetSized(int x, int y, int size, boolean direction) throws InvalidPosition{
        if(s[size-1]>=4 - (size-1)) throw new InvalidPosition();
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
        this.s[size-1]++;
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

    public int getPosValue(int i, int j){return this.place[i][j];}
    public void setPosValue(int i, int j, int value){this.place[i][j] = value;}

    void debug_print(JButton[][] array){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    if (place[i][j] == 0) {
                        Image img = ImageIO.read(new File("img/sea.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    }
                    else if(place[i][j] > 0){
                        Image img = ImageIO.read(new File("img/ship.png"));
                        array[i][j].setIcon(new ImageIcon(img));
                    } else if(place[i][j] != -10){
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

    public boolean isFull(){
        return s[0]==4 && s[1]==3 && s[2]==2 && s[3]==1;
    }

    boolean IsWin(){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(place[i][j] > 0) return false;
            }
        }
        return true;
    }
}
