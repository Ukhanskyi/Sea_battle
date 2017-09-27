import javax.swing.*;

/**
 * Created by Zen on 13.09.2017.
 */
public class Bot {
    private BattlePlace BotBattlePlace = new BattlePlace();

    void Bot(){
        GenerateSized(4);
        GenerateSized(3);
        GenerateSized(3);
        GenerateSized(2);
        GenerateSized(2);
        GenerateSized(2);
        GenerateSized(1);
        GenerateSized(1);
        GenerateSized(1);
        GenerateSized(1);
    }

    private void GenerateSized(int size){
        while(true){
            int x_max,y_max;
            boolean direction = ((int)(Math.random()*100))>50;
            if(direction) {x_max = 10; y_max=10-(size-1);} else{x_max=10-(size-1);y_max=10;}
            int x = (int)(Math.random()*x_max);
            int y = (int)(Math.random()*y_max);
            try {
                BotBattlePlace.SetSized(x, y, size, direction);
            }
            catch (BattlePlace.InvalidPosition e){
                continue;
            }
            break;
        }
    }

    public void debug_print(JButton[][] array){
        BotBattlePlace.debug_print(array);
    }
}
