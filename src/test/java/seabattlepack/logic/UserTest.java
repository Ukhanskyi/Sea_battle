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
    public void isWin_should_Return_False() {
        assertFalse(user.isWin());
    }

    @Test
    public void possibleDirection_should_Be_NotNull() {assertNotNull(user.possibleDirection(2,5));
    }

    @Test
    public void getBattlePlace() {
        assertNotNull(user.getBattlePlace());

    }

    @Test
    public void attack_mode1() {
        assertNotNull(user.attack(1));
    }

    @Test
    public void attack_mode2() {
        assertNotNull(user.attack(2));
    }

    @Test
    public void attack_mode3() {
        assertNotNull(user.attack(3));
    }

    @Test
    public void attack_mode4() {
        assertNotNull(user.attack(4));
    }

}