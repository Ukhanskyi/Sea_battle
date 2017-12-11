package seabattlepack;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static seabattlepack.User.res.Good;
import static seabattlepack.User.res.Miss;

/**
 * Created by Dzykan on 13.09.2017.
 */
public class User {

    private int I, J;

    private BattlePlace place = new BattlePlace();

    public User() {
        place = new BattlePlace();
    }

    public void setBattlePlace(BattlePlace battlePlace) {
        place = battlePlace;
    }

    public BattlePlace getBattlePlace() {
        return place;
    }

    class ret_pos {
        public int x, y;

        ret_pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum res {
        Miss, Good, Bad
    }

    public enum Directions {
        Up, Down, Left, Right
    }

    List<Directions> availableDirections = new ArrayList<>();

    Directions direction;

    Stack<ret_pos> posStack = new Stack<>();

    List<Directions> possibleDirection(int i, int j) {
        List<Directions> ret = new ArrayList<>();
        if (i > 0) ret.add(Directions.Up);
        if (i < 9) ret.add(Directions.Down);
        if (j > 0) ret.add(Directions.Left);
        if (j < 9) ret.add(Directions.Right);
        return ret;
    }

    List<Directions> possibleDirection(int i, int j, List<Directions> directions) {
        List<Directions> possibleDirection = possibleDirection(i, j);
        List<Directions> ret = new ArrayList<>();
        ret.addAll(directions);
        for (Directions dir : directions) {
            if (!possibleDirection.contains(dir))
                ret.remove(dir);
        }
        return ret;
    }

    public res attack(int mode) {
        if (mode == 1)
            return randomAttack();
        return autoAttack();
    }


    private res autoAttack() {
        if (availableDirections.isEmpty())
            if (posStack.isEmpty()) {
                direction = null;
                while (true) {
                    int i = place.getRandomPoint();
                    int j = place.getRandomPoint();
                    switch (place.getCellState(i, j)) {
                        case Sea:
                            place.attack(i, j);
                            return Miss;
                        case Ship:
                            place.attack(i, j);
                            I = i;
                            J = j;
                            availableDirections = possibleDirection(i, j);
                            posStack.push(new ret_pos(i, j));
                            return Good;
                        case ShipDamaged:
                            place.attack(i, j);
                            I = i;
                            J = j;
                            posStack.push(new ret_pos(i, j));
                            availableDirections = possibleDirection(i, j);
                            return Good;
                        case Border:
                            place.attack(i, j);
                            return Miss;
                        case ShipKilled:
                            continue;
                        case Miss:
                    }
                }
            }
        if (availableDirections.isEmpty()) {
            while (posStack.size() != 1) posStack.pop();
            ret_pos p = posStack.pop();
            I = p.x;
            J = p.y;
            if (direction == null) return autoAttack();
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
            availableDirections = possibleDirection(I, J, availableDirections);
            if (availableDirections.isEmpty())
                return autoAttack();
        }
        while (true) {
            if (availableDirections.isEmpty()) return autoAttack();
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
            if (i < 0 || i > 9 || j < 0 || j > 9) {
                availableDirections.clear();
                return autoAttack();
            }
            switch (place.getCellState(i, j)) {
                case Sea:
                    availableDirections.remove(n);
                    place.attack(i, j);
                    return Miss;
                case Ship:
                    posStack.push(new ret_pos(i, j));
                    I = i;
                    J = j;
                    direction = availableDirections.get(n);
                    availableDirections.removeIf(directions -> {
                        if (direction != directions)
                            return true;
                        return false;
                    });
                    place.attack(i, j);
                    return Good;
                case ShipDamaged:
                    posStack.push(new ret_pos(i, j));
                    I = i;
                    J = j;
                    direction = availableDirections.get(n);
                    availableDirections.removeIf(directions -> {
                        if (direction != directions)
                            return true;
                        return false;
                    });
                    if (availableDirections.isEmpty()) {
                        return autoAttack();
                    }
                    place.attack(i, j);
                    return Good;
                case ShipKilled:
                    posStack.empty();
                    availableDirections.clear();
                    return autoAttack();
                case Miss:
                    availableDirections.remove(n);
                    continue;
                case Border:
                    availableDirections.remove(n);
                    place.attack(i, j);
                    return Miss;
            }
        }
    }

    private res randomAttack() {
        while (true) {
            int i = place.getRandomPoint();
            int j = place.getRandomPoint();
            switch (place.getCellState(i, j)) {
                case Sea:
                    place.attack(i, j);
                    return Miss;
                case Ship:
                    place.attack(i, j);
                    return Good;
                case ShipDamaged:
                    place.attack(i, j);
                    return Good;
                case ShipKilled:
                    continue;
                case Miss:
                    continue;
                case Border:
                    place.attack(i, j);
                    return Miss;
            }
        }
    }

    public boolean isWin() {
        return place.isWin();
    }
}
