package seabattlepack.logic;


import java.util.*;

import static seabattlepack.logic.User.res.GOOD;
import static seabattlepack.logic.User.res.MISS;

/**
 * Created by Dzykan on 13.09.2017.
 */
public class User {

    private int dI;
    private int dJ;

    private BattlePlace place;

    public User() {
        place = new BattlePlace();
    }

    public void setBattlePlace(BattlePlace battlePlace) {
        place = battlePlace;
    }

    public BattlePlace getBattlePlace() {
        return place;
    }

    class RetPos {
        private int x;
        private int y;

        RetPos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum res {
        MISS, GOOD, BAD
    }

    public enum Directions {
        UP, DOWN, LEFT, RIGHT
    }

    List<Directions> availableDirections = new ArrayList<>();

    Directions direction;

    Deque<RetPos> posStack = new ArrayDeque<>();

    List<Directions> possibleDirection(int i, int j) {
        List<Directions> ret = new ArrayList<>();
        if (i > 0) ret.add(Directions.UP);
        if (i < 9) ret.add(Directions.DOWN);
        if (j > 0) ret.add(Directions.LEFT);
        if (j < 9) ret.add(Directions.RIGHT);
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
                            return MISS;
                        case SHIP:
                            place.attack(i, j);
                            dI = i;
                            dJ = j;
                            availableDirections = possibleDirection(i, j);
                            posStack.push(new RetPos(i, j));
                            return GOOD;
                        case SHIP_DAMAGED:
                            place.attack(i, j);
                            dI = i;
                            dJ = j;
                            posStack.push(new RetPos(i, j));
                            availableDirections = possibleDirection(i, j);
                            return GOOD;
                        case BORDER:
                            place.attack(i, j);
                            return MISS;
                        case SHIP_KILLED:
                            continue;
                        case MISS:
                    }
                }
            }
        if (availableDirections.isEmpty()) {
            while (posStack.size() != 1) posStack.pop();
            RetPos p = posStack.pop();
            dI = p.x;
            dJ = p.y;
            if (direction == null) return autoAttack();
            switch (direction) {
                case UP:
                    availableDirections.add(Directions.DOWN);
                    break;
                case DOWN:
                    availableDirections.add(Directions.UP);
                    break;
                case LEFT:
                    availableDirections.add(Directions.RIGHT);
                    break;
                case RIGHT:
                    availableDirections.add(Directions.LEFT);
                    break;
            }
            availableDirections = possibleDirection(dI, dJ, availableDirections);
            if (availableDirections.isEmpty())
                return autoAttack();
        }
        while (true) {
            if (availableDirections.isEmpty()) return autoAttack();
            Random r = new Random();
            int n = r.nextInt(availableDirections.size());
            int i = 0;
            int j = 0;
            switch (availableDirections.get(n)) {
                case UP:
                    i = dI - 1;
                    j = dJ;
                    break;
                case DOWN:
                    i = dI + 1;
                    j = dJ;
                    break;
                case LEFT:
                    j = dJ - 1;
                    i = dI;
                    break;
                case RIGHT:
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
                    return MISS;
                case SHIP:
                    posStack.push(new RetPos(i, j));
                    dI = i;
                    dJ = j;
                    direction = availableDirections.get(n);
                    availableDirections.removeIf(directions -> (direction != directions));
                    place.attack(i, j);
                    return GOOD;
                case SHIP_DAMAGED:
                    posStack.push(new RetPos(i, j));
                    dI = i;
                    dJ = j;
                    direction = availableDirections.get(n);
                    availableDirections.removeIf(directions -> (direction != directions));
                    if (availableDirections.isEmpty()) {
                        return autoAttack();
                    }
                    place.attack(i, j);
                    return GOOD;
                case SHIP_KILLED:
                    posStack.isEmpty();
                    availableDirections.clear();
                    return autoAttack();
                case MISS:
                    availableDirections.remove(n);
                    continue;
                case BORDER:
                    availableDirections.remove(n);
                    place.attack(i, j);
                    return MISS;
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
                    return MISS;
                case SHIP:
                    place.attack(i, j);
                    return GOOD;
                case SHIP_DAMAGED:
                    place.attack(i, j);
                    return GOOD;
                case SHIP_KILLED:
                    continue;
                case MISS:
                    continue;
                case BORDER:
                    place.attack(i, j);
                    return MISS;
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