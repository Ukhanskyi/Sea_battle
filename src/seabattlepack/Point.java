package seabattlepack;

/**
 * Created by Iwan_ on 12.12.2017.
 */
public class Point {
    private int x;
    private int y;

    Point() {
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