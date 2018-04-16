package seabattlepack.logic;

import java.util.Random;

public class RandomPoint {
    private int x;
    private int y;

    RandomPoint() {
        Random r = new Random();
        this.x = r.nextInt(10);
        this.y = r.nextInt(10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}