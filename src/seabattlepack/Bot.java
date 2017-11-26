package seabattlepack;

import static seabattlepack.User.Res.Bad;
import static seabattlepack.User.Res.Good;
import static seabattlepack.User.Res.Miss;

public class Bot {
    private BattlePlace place;

    public User.Res attack(int i, int j) {
        switch (place.getCellState(i, j)) {
            case Sea:
                place.Attack(i, j);
                return Miss;
            case Ship:
                place.Attack(i, j);
                return Good;
            case ShipDamaged:
                place.Attack(i, j);
                return Good;
            case ShipKilled:
                return Bad;
            case Miss:
                return Bad;
            case Border:
                place.Attack(i, j);
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

