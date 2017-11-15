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

    static BattlePlace getAutoGenBatlePlace(){
        BattlePlace place = new BattlePlace();
        GenerateSized(place,4);
        GenerateSized(place,3);
        GenerateSized(place,3);
        GenerateSized(place,2);
        GenerateSized(place,2);
        GenerateSized(place,2);
        GenerateSized(place,1);
        GenerateSized(place,1);
        GenerateSized(place,1);
        GenerateSized(place,1);
        return place;
    }

    private static void GenerateSized(BattlePlace place, int size){
        while(true){
            int x_max,y_max;
            BattlePlace.Direction direction =
                    (((int)(Math.random()*100))>50)?
                            BattlePlace.Direction.Down:
                            BattlePlace.Direction.Left;
            if(direction == BattlePlace.Direction.Down)
            {x_max = 10; y_max=10-(size-1);}
            else{x_max=10-(size-1);y_max=10;}
            int x = (int)(Math.random()*x_max);
            int y = (int)(Math.random()*y_max);
            try {
                place.setSized(x, y, size, direction);
            }
            catch (BattlePlace.InvalidPosition e){
                continue;
            }
            break;
        }
    }

    enum Direction{
        Down,Left
    }

    class InvalidPosition extends Throwable{
        InvalidPosition(){}
    }

    private int[][] place = new int[10][10];

    private int numberShip[] = new int[4];

    BattlePlace(){
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                place[i][j]=0;
        for(int i =0;i < 4;i++)
            numberShip[i] = 0;
    }


    public void setSized(int x, int y, int size, Direction direction) throws InvalidPosition{
        if(numberShip[size-1]>=4 - (size-1)) throw new InvalidPosition();
        int[][] place = this.place;
        int x_max,y_max;
        if(direction==Direction.Left){
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
            if(direction==Direction.Left)
                place[x+i][y]=size;
            else
                place[x][y+i]=size;
        }
        this.place = place;
        this.numberShip[size-1]++;
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

    public boolean isFull(){
        return numberShip[0]==4 && numberShip[1]==3 && numberShip[2]==2 && numberShip[3]==1;
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
