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
    public void isWin_should_return_False() {
        assertFalse(bot.isWin());
    }

    @Test
    public void getBattlePlace_should_be_NotNull() {assertNotNull(bot.getBattlePlace());}

    @Test
    public void hint_should_be_NotNull() {

    }

    @Test
    public void attack() {
        assertNotNull(bot.attack(2,5));
    }
}