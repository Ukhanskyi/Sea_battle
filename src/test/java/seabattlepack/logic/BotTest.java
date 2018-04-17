package seabattlepack.logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {
    Bot bot = new Bot();

    @Test
    public void isWin() {
        boolean actual = bot.isWin();
        boolean expected = false;
        assertEquals(expected,actual);
    }
}