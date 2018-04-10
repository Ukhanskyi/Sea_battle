package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlaceTest {
    BattlePlace battlePlace = new BattlePlace();

    @Test
    public void isFull() {

        boolean actual = battlePlace.isFull();
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    public void isWin() {

        boolean actual = battlePlace.isWin();
        boolean expected = false;
        assertEquals(expected,actual);
    }
}