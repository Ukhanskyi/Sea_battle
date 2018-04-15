package seabattlepack.logic;

public class RandomPoint {
    private int x;
    private int y;

    RandomPoint() {
        this.x = (int) (Math.random() * 10);
        this.y = (int) (Math.random() * 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}