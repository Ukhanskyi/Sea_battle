package seabattlepack.logic;

import static seabattlepack.logic.User.res.BAD;
import static seabattlepack.logic.User.res.GOOD;
import static seabattlepack.logic.User.res.MISS;

public class Bot {
    protected BattlePlace place;

    public User.res attack(int i, int j) {
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
                return BAD;
            case MISS:
                return BAD;
            case BORDER:
                place.attack(i, j);
                return MISS;
        }
        return BAD;
    }

    public Bot() {
        place = new BattlePlace(true);
    }

    public boolean isWin() {
        return place.isWin();
    }

    public BattlePlace getBattlePlace() {
        return place;
    }

    public void hint() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (place.getCellState(i, j) == BattlePlace.CellState.SHIP_KILLED) {
                    place.attack(i + 1, j + 1);
                    place.attack(i + 1, j - 1);
                    place.attack(i - 1, j + 1);
                    place.attack(i - 1, j - 1);
                }
            }
        }
    }

}
