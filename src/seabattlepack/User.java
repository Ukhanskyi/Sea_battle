package seabattlepack;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Zen on 13.09.2017.
 */
public class User {
    private BattlePlace place = new BattlePlace();

    public void setBattlePlace(BattlePlace battlePlace){
        place = battlePlace;
    }

    public BattlePlace getBattlePlace() {
        return place;
    }

    class pos{
        public int x,y;
        pos(int x, int y) {this.x = x; this.y = y;}
    }

    class point{
        pos P;
        int status;
        point(int i, int j, int status){
            P = new pos(i,j);
            this.status = status;
        }
    }

    public enum Directions {
        Up, Down, Left, Right
    }

    private boolean isFirst = true;
    private int I, J;

    List<Directions> availableDirections = new ArrayList<>();

    Directions direction;

    Stack<pos> posStack = new Stack<>();

    List<Directions> possibleDirection(int i, int j) {
        List<Directions> ret = new ArrayList<>();
        if (i > 0) ret.add(Directions.Up);
        if (i < 9) ret.add(Directions.Down);
        if (j > 0) ret.add(Directions.Left);
        if (j < 9) ret.add(Directions.Right);
        return ret;
    }

    List<Directions> possibleDirection(int i, int j, List<Directions> directions) {
        List<Directions> possibleDirection = possibleDirection(i,j);
        List<Directions> ret = new ArrayList<>();
        ret.addAll(directions);
        for(Directions dir : directions){
            if(!possibleDirection.contains(dir))
                ret.remove(dir);
        }
        return ret;
    }

    public point attack(int mode){
        if(mode == 0)
            return randomAttack();
        return  autoAttack();
    }

    @NotNull
    private point autoAttack() {
        if (availableDirections.isEmpty())
            if (posStack.isEmpty()) {
                while (true) {
                    int i = (int) (Math.random() * 10);
                    int j = (int) (Math.random() * 10);
                    int old_value = place.getPosValue(i, j);
                    if (old_value > 0) {
                        I = i;
                        J = j;
                        availableDirections = possibleDirection(i, j);
                        place.setPosValue(i, j, -1 * place.getPosValue(i, j));
                        return new point(I, J, old_value);
                    } else if (old_value != 0)
                        continue;
                    else {
                        place.setPosValue(i, j, -10);
                        return new point(i, j, place.getPosValue(i,j));
                    }
                }
            }
        if (availableDirections.isEmpty()) {
            while(posStack.size()!=1) posStack.pop();
            pos p = posStack.pop();
            I = p.x;
            J = p.y;
            switch (direction) {
                case Up:
                    availableDirections.add(Directions.Down);
                    break;
                case Down:
                    availableDirections.add(Directions.Up);
                    break;
                case Left:
                    availableDirections.add(Directions.Right);
                    break;
                case Right:
                    availableDirections.add(Directions.Left);
                    break;
            }
            availableDirections = possibleDirection(I,J,availableDirections);
            if(availableDirections.isEmpty())
                return autoAttack();
        }
        while (true) {
            int n = (int) (Math.random() * availableDirections.size());
            int i = 0, j = 0;
            switch (availableDirections.get(n)) {
                case Up:
                    i = I - 1;
                    j = J;
                    break;
                case Down:
                    i = I + 1;
                    j = J;
                    break;
                case Left:
                    j = J - 1;
                    i = I;
                    break;
                case Right:
                    j = J + 1;
                    i = I;
                    break;
            }
            int old_value = place.getPosValue(i, j);
            if (old_value < 0) {
                availableDirections.remove(n);
                if(availableDirections.isEmpty()){
                    return autoAttack();
                } else continue;
            } else if (old_value == 0) {
                availableDirections.remove(n);
                place.setPosValue(i, j, -10);
                return new point(i, j, place.getPosValue(i,j));
            } else {
                place.setPosValue(i, j, -1 * place.getPosValue(i, j));
                posStack.push(new pos(i, j));
                I = i;
                J = j;
                direction = availableDirections.get(n);
                availableDirections.removeIf(directions -> {
                    if (direction != directions)
                        return true;
                    return false;
                });
                availableDirections = possibleDirection(I, J);
                return new point(i, j, place.getPosValue(i,j));
            }
        }
    }

    @NotNull
    private point randomAttack() {
        while (true) {
            int i = (int) (Math.random() * 10);
            int j = (int) (Math.random() * 10);
            int old_value = place.getPosValue(i, j);
            if (old_value < 0) continue;
            if (old_value == 0) {
                place.setPosValue(i, j, -10);
                return new point(i, j, -10);
            }
            place.setPosValue(i, j, -1 * place.getPosValue(i, j));
            return new point(i, j, -1 * old_value);
        }
    }

    public boolean IsWin(){
        return place.IsWin();
    }
}
