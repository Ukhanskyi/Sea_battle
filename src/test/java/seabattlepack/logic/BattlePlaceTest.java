package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BattlePlaceTest {

    @Test
    public void isFull() {
        BattlePlace battlePlace = new BattlePlace();
        boolean actual = battlePlace.isFull();
        boolean expected = false;
        assertEquals(expected,actual);
    }
}