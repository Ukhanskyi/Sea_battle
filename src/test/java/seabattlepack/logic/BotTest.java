package seabattlepack.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {
    private Bot bot;

    @Before
    public void setBot(){
        bot = new Bot();
    }

    @Test
    public void isWin() {
        assertFalse(bot.isWin());
    }

    @Test
    public void getBattlePlace() {assertNotNull(bot.getBattlePlace());
    }
}