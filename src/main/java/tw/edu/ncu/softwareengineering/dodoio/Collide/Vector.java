package tw.edu.ncu.softwareengineering.dodoio.Collide;

public class Vector {
    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double dotProduct(Vector v) {
        return x * v.getX() + y * v.getY();
    }
}
