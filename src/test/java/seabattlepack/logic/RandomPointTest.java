package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomPointTest {

    private RandomPoint randomPoint;

    @Before
    public void setRandomPoint(){
        randomPoint = new RandomPoint();
    }

    @Test
    public void getX_should_be_NotNull() {assertNotNull(randomPoint.getX());}

    @Test
    public void getY_should_be_NotNull() {
        assertNotNull(randomPoint.getY());
    }
}