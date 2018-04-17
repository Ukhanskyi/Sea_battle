package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlaceTest {

    BattlePlace battlePlace = new BattlePlace();

    @Test
    public void isFull() {
        boolean actual = battlePlace.isFull();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void isWin() {
        boolean actual = battlePlace.isWin();
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    public void checkPlace_isGood() {
        assertEquals(true, battlePlace.checkPlace(0, 0, 9, 9));
    }

    @Test
    public void checkPlace_isBad() {
        assertEquals(false, battlePlace.checkPlace(0, 0, 10, 10));
    }
}

