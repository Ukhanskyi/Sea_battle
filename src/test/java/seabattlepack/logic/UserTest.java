package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUser(){
        user = new User();
    }

    @Test
    public void isWin() {
        assertFalse(user.isWin());
    }

    @Test
    public void possibleDirection() {assertNotNull(user.possibleDirection(2,5));
    }
}