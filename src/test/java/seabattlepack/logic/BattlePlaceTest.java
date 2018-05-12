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
    public void isWin_should_Return_False() { assertFalse(battlePlace.isWin());}

    @Test
    public void func_checkPlace_isAutoGen_should_return_false() {assertTrue(battlePlace.checkPlace(0, 0, 9, 9));}

    @Test
    public void checkPlace_isGood_isAutoGen_should_return_true() {assertFalse(bp.checkPlace(0, 0, 9, 9));}

    @Test
    public void checkPlace_isBad_isAutoGen_should_return_false() { assertFalse(battlePlace.checkPlace(0, 0, 10, 10));}

    @Test
    public void func_manualPlace_isAutoGen_should_return_false() {assertTrue(battlePlace.manualPlace(2,2, 5,2));}

    @Test
    public void func_manualPlace_isAutoGen_should_return_true() {assertFalse(bp.manualPlace(5,2, 5,6));}

    @Test
    public void getShipPlace_should_be_NotNull() {assertNotNull(battlePlace.getShipPlace(1,2,1,5));}

    @Test
    public void getShipPlace_should_be_NotNull_different_cord() {assertNotNull(bp.getShipPlace(1,2,4,2));}

    @Test
    public void atackTest_should_return_true_if_MISS(){
        BattlePlace.Cell cell = new BattlePlace.Cell();
        cell.attack();
        assertEquals(BattlePlace.CellState.MISS, cell.getMyState());
    }

    @Test
    public void atackTest_should_KILL_ship_size1(){
        BattlePlace.Cell cell = new BattlePlace.Cell();
        BattlePlace.Ship ship = battlePlace.getShipByCoord(1, 0, 0 , 0, 0);
        cell.setShip(ship);
        cell.attack();
        assertEquals(BattlePlace.CellState.SHIP_KILLED, cell.getMyState());
    }

   /* @Test
    public void atack_Test_should_DAMAGE_ship_size2(){
        BattlePlace.Cell cell = new BattlePlace.Cell();
        BattlePlace.Ship ship = battlePlace.getShipByCoord(2, 0, 0, 1, 0);
        cell.setShip(ship);
        cell.attack();
        assertEquals(BattlePlace.CellState.SHIP_KILLED, cell.getMyState());
    }

    @Test
    public void atack_Test_should_DAMAGE_ship_size3(){
        BattlePlace.Cell cell = new BattlePlace.Cell();
        BattlePlace.Ship ship = battlePlace.getShipByCoord(3, 0, 0, 2, 0);
        cell.setShip(ship);
        cell.attack();
        assertEquals(BattlePlace.CellState.SHIP_KILLED, cell.getMyState());
    }

    @Test
    public void atack_Test_should_DAMAGE_ship_size4(){
        BattlePlace.Cell cell = new BattlePlace.Cell();
        BattlePlace.Ship ship = battlePlace.getShipByCoord(4, 0, 0, 3, 0);
        cell.setShip(ship);
        cell.attack();
        assertEquals(BattlePlace.CellState.SHIP_KILLED, cell.getMyState());
    }*/
}

