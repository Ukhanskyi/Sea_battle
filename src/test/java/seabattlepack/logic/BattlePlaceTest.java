package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlaceTest {

    private BattlePlace battlePlace;
    private BattlePlace bp;

    @Before
    public void setBattlePlace(){
        battlePlace = new BattlePlace(false);
        bp = new BattlePlace(true);
    }

    @Test
    public void func_isFull_when_isAutoGen_false() { assertFalse(battlePlace.isFull());}

    @Test
    public void func_isFull_when_isAutoGen_true() { assertTrue(bp.isFull());}

    @Test
    public void isWin() { assertFalse(battlePlace.isWin());}

    @Test
    public void checkPlace_isGood() {
        assertTrue(battlePlace.checkPlace(0, 0, 9, 9));
    }

    @Test
    public void checkPlace_isBad() { assertFalse(battlePlace.checkPlace(0, 0, 10, 10));}
}

