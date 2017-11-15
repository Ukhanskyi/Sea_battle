package seabattlepack;

public class Bot {
    private BattlePlace place;

    public int attack(int i, int j){
        int old_value = place.getPosValue(i,j);
        if(old_value>=0){
            place.setPosValue(i,j,old_value==0?-10:-old_value);
        }
        return old_value;
    }

    public Bot(){
        place = BattlePlace.getAutoGenBatlePlace();
    }

    public boolean IsWin(){
        return place.IsWin();
    }

    public BattlePlace getBattlePlace(){
        return place;
    }
}

