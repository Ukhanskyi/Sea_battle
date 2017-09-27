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
        if(direction){
            if(x<0 || x>9-(size-1)) throw new InvalidPosition();
            if(y<0 || y>9) throw new InvalidPosition();
            for(int i=x-1;i<x+size+1;i++){
                for(int j = y-1;j<y+2;j++){
                    if(i<10 && j<10 && i>=0 && j>=0){
                        if(place[i][j]!=0) throw new InvalidPosition();
                    }
                }
            }
            for(int i=0;i<size;i++){
                place[x+i][y]=size;
            }
        } else{
            if(x<0 || x>9-(size-1)) throw new InvalidPosition();
            if(y<0 || y>6) throw new InvalidPosition();
            for(int i=x-1;i<x+2;i++){
                for(int j = y-1;j<y+size+1;j++){
                    if(i<10 && j<10 && i>=0 && j>=0){
                        if(place[i][j]!=0) throw new InvalidPosition();
                    }
                }
            }
            for(int i=0;i<size;i++){
                place[x][y+i]=size;
            }
        }
        this.place = place;
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
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
