package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void isWin() {
        Bot bot = new Bot();
        boolean actual = bot.isWin();
        boolean expected = false;
        assertEquals(expected,actual);
    }
}