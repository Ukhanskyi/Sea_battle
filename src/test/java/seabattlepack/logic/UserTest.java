package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    User user = new User();

    @Test
    public void isWin() {
        boolean actual = user.isWin();
        boolean expected = false;
        assertEquals(expected,actual);
    }
}