package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void checkArray() {
        assertNotNull(user.getBattlePlace());
    }

    @Test
    public void checkRetPost() {
        User.RetPos ret = new User.RetPos(2, 2);

        assertEquals(2, ret.x);
        assertEquals(2, ret.y);
    }

    @Test
    public void checkPossibleDirectionWithList() {
        List<User.Directions> failed = new ArrayList<>();
        failed.add(User.Directions.UP);
        failed.add(User.Directions.DOWN);
        failed.add(User.Directions.LEFT);
        failed.add(User.Directions.RIGHT);

        List<User.Directions> expected = new ArrayList<>();
        expected.add(User.Directions.DOWN);
        expected.add(User.Directions.LEFT);

        List<User.Directions> actual = user.possibleDirection(0, 9, failed);

        assertEquals(expected, actual);
    }

    @Test
    public void checkPossibleDirection() {
        List<User.Directions> expected = new ArrayList<>();
        expected.add(User.Directions.UP);
        expected.add(User.Directions.RIGHT);

        List<User.Directions> actual = user.possibleDirection(9, 0);

        assertEquals(expected, actual);
    }

    @Test
    public void checkPossibleDirections() {
        List<User.Directions> expected = new ArrayList<>();
        expected.add(User.Directions.DOWN);
        expected.add(User.Directions.LEFT);

        List<User.Directions> actual = user.possibleDirection(0, 9);

        assertEquals(expected, actual);
    }

    @Test
    public void attack_mode1() {
        assertNotNull(user.attack(1));
    }

    @Test
    public void attack_mode2() {
        assertNotNull(user.attack(2));
    }

}