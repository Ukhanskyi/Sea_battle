package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlaceTest {

    private BattlePlace battlePlace;

    @Before
    public void setBattlePlace(){
        BattlePlace battlePlace = new BattlePlace();
    }

    @Test
    public void isFull() { assertFalse(battlePlace.isFull());}

    @Test
    public void isWin() { assertFalse(battlePlace.isWin());}

    @Test
    public void checkPlace_isGood() {
        assertTrue(battlePlace.checkPlace(0, 0, 9, 9));
    }

    @Test
    public void checkPlace_isBad() {
        assertFalse(battlePlace.checkPlace(0, 0, 10, 10));
    }
}

