package tw.edu.ncu.softwareengineering.dodoio.Collide;

public class Bound {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * create a bound
     *
     * @param x      the left-top x coordinate
     * @param y      the left-top y coordinate
     * @param width  the width of the bound
     * @param height the height of th bound
     */
    public Bound(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the left-top x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return the left-top y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * @return the width of the bound
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of the bound
     */
    public int getHeight() {
        return height;
    }
}
