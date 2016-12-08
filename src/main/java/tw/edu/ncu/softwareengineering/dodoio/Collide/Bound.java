package tw.edu.ncu.softwareengineering.dodoio.Collide;

public class Bound {
    private int x;
    private int y;
    private int width;
    private int height;

    public Bound(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
