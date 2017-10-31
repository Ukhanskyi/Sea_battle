package seabattlepack;

import javax.swing.*;

/**
 * Created by Zen on 13.09.2017.
 */
public class Bot {
    protected BattlePlace BotBattlePlace = new BattlePlace();

    public Bot(){
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

    public BattlePlace battlePlace(){
        return BotBattlePlace;
    }

    private void GenerateSized(int size){
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
                BotBattlePlace.setSized(x, y, size, direction);
            }
            catch (BattlePlace.InvalidPosition e){
                continue;
            }
            break;
        }
    }

    public int Attack(int i, int j){
        int old_value = BotBattlePlace.getPosValue(i,j);
        if(old_value < 0) return old_value;
        if(old_value == 0) {
            BotBattlePlace.setPosValue(i,j,-10);
            return old_value;
        }
        BotBattlePlace.setPosValue(i,j,-1 * BotBattlePlace.getPosValue(i,j));
        return old_value;
    }

    public void debug_print(JButton[][] array){
        BotBattlePlace.debug_print(array);
    }

    public boolean IsWin(){
        return BotBattlePlace.IsWin();
    }
}
