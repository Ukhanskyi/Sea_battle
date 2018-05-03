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
    public void isWin_ReturnFalse() { assertFalse(battlePlace.isWin());}

    @Test
    public void func_checkPlace_isAutoGen_false() {assertTrue(battlePlace.checkPlace(0, 0, 9, 9));}

    @Test
    public void checkPlace_isGood_isAutoGen_true() {assertFalse(bp.checkPlace(0, 0, 9, 9));}

    @Test
    public void checkPlace_isBad_isAutoGen_false() { assertFalse(battlePlace.checkPlace(0, 0, 10, 10));}

    @Test
    public void func_manualPlace_isAutoGen_false() {assertTrue(battlePlace.manualPlace(2,2, 5,2));}

    @Test
    public void func_manualPlace_isAutoGen_true() {assertFalse(bp.manualPlace(5,2, 5,6));}

    @Test
    public void getShipPlace() {assertNotNull(battlePlace.getShipPlace(1,2,1,5));}

    @Test
    public void getShipPlace_bad() {assertNotNull(bp.getShipPlace(1,2,4,2));}
}

