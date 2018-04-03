package seabattlepack.logic;

import static seabattlepack.logic.User.res.Bad;
import static seabattlepack.logic.User.res.Good;
import static seabattlepack.logic.User.res.Miss;

public class Bot {
    protected BattlePlace place;

    public User.res attack(int i, int j) {
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
                return Bad;
            case Miss:
                return Bad;
            case Border:
                place.attack(i, j);
                return Miss;
        }
        return Bad;
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
                if (place.getCellState(i, j) == BattlePlace.CellState.ShipKilled) {
                    place.attack(i + 1, j + 1);
                    place.attack(i + 1, j - 1);
                    place.attack(i - 1, j + 1);
                    place.attack(i - 1, j - 1);
                }
            }
        }
    }

}
