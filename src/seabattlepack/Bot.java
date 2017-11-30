package seabattlepack;

import static seabattlepack.User.res.Bad;
import static seabattlepack.User.res.Good;
import static seabattlepack.User.res.Miss;

public class Bot {
    private BattlePlace place;

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

    public Bot(){
        place = new BattlePlace(true);
    }

    public boolean isWin(){
        return place.isWin();
    }

    public BattlePlace getBattlePlace(){
        return place;
    }
}

