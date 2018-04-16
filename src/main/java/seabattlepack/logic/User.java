package seabattlepack.logic;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static seabattlepack.logic.User.res.Good;
import static seabattlepack.logic.User.res.Miss;

/**
 * Created by Dzykan on 13.09.2017.
 */
public class User {

    private int dI;
    private int dJ;

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
        private int x, y;

        ret_pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum res {
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
                    RandomPoint point = new RandomPoint();
                    int i = point.getX();
                    int j = point.getY();
                    switch (place.getCellState(i, j)) {
                        case SEA:
                            place.attack(i, j);
                            return Miss;
                        case SHIP:
                            place.attack(i, j);
                            dI = i;
                            dJ = j;
                            availableDirections = possibleDirection(i, j);
                            posStack.push(new ret_pos(i, j));
                            return Good;
                        case SHIP_DAMAGED:
                            place.attack(i, j);
                            dI = i;
                            dJ = j;
                            posStack.push(new ret_pos(i, j));
                            availableDirections = possibleDirection(i, j);
                            return Good;
                        case BORDER:
                            place.attack(i, j);
                            return Miss;
                        case SHIP_KILLED:
                            continue;
                        case MISS:
                    }
                }
            }
        if (availableDirections.isEmpty()) {
            while (posStack.size() != 1) posStack.pop();
            ret_pos p = posStack.pop();
            dI = p.x;
            dJ = p.y;
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
            availableDirections = possibleDirection(dI, dJ, availableDirections);
            if (availableDirections.isEmpty())
                return autoAttack();
        }
        while (true) {
            if (availableDirections.isEmpty()) return autoAttack();
            int n = (int) (Math.random() * availableDirections.size());
            int i = 0, j = 0;
            switch (availableDirections.get(n)) {
                case Up:
                    i = dI - 1;
                    j = dJ;
                    break;
                case Down:
                    i = dI + 1;
                    j = dJ;
                    break;
                case Left:
                    j = dJ - 1;
                    i = dI;
                    break;
                case Right:
                    j = dJ + 1;
                    i = dI;
                    break;
            }
            if (i < 0 || i > 9 || j < 0 || j > 9) {
                availableDirections.clear();
                return autoAttack();
            }
            switch (place.getCellState(i, j)) {
                case SEA:
                    availableDirections.remove(n);
                    place.attack(i, j);
                    return Miss;
                case SHIP:
                    posStack.push(new ret_pos(i, j));
                    dI = i;
                    dJ = j;
                    direction = availableDirections.get(n);
                    availableDirections.removeIf(directions -> {
                        if (direction != directions)
                            return true;
                        return false;
                    });
                    place.attack(i, j);
                    return Good;
                case SHIP_DAMAGED:
                    posStack.push(new ret_pos(i, j));
                    dI = i;
                    dJ = j;
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
                case SHIP_KILLED:
                    posStack.empty();
                    availableDirections.clear();
                    return autoAttack();
                case MISS:
                    availableDirections.remove(n);
                    continue;
                case BORDER:
                    availableDirections.remove(n);
                    place.attack(i, j);
                    return Miss;
            }
        }
    }

    private res randomAttack() {
        while (true) {
            RandomPoint point = new RandomPoint();
            int i = point.getX();
            int j = point.getY();
            switch (place.getCellState(i, j)) {
                case SEA:
                    place.attack(i, j);
                    return Miss;
                case SHIP:
                    place.attack(i, j);
                    return Good;
                case SHIP_DAMAGED:
                    place.attack(i, j);
                    return Good;
                case SHIP_KILLED:
                    continue;
                case MISS:
                    continue;
                case BORDER:
                    place.attack(i, j);
                    return Miss;
            }
        }
    }

    public boolean isWin() {
        return place.isWin();
    }

    /**
     * Created by Iwan_ on 12.12.2017.
     */
   }