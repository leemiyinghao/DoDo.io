package tw.edu.ncu.softwareengineering.dodoio.Collide;

public class Vector implements Comparable<Vector> {
    private double x;
    private double y;

    /**
     * create a vector
     *
     * @param x horizontal component of the vector
     * @param y vertical component of the vector
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return horizontal component of the vector
     */
    public double getX() {
        return x;
    }

    /**
     * @return vertical component of the vector
     */
    public double getY() {
        return y;
    }

    /**
     * @param v the vector to dot product with
     * @return the dot product value of the two vector
     */
    public double dotProduct(Vector v) {
        return x * v.getX() + y * v.getY();
    }


    /**
     * @return the magnitude of the vector
     */
    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * @return the normal of the vector
     */
    public Vector normal() {
        return new Vector(-y, x);
    }

    /**
     * @return the unit vector of the vector
     */
    public Vector normalized() {
        return new Vector(x / magnitude(), y / magnitude());
    }

    @Override
    public int compareTo(Vector o) {
        if (x < o.x)
            return -1;
        else if (x > o.x)
            return 1;
        else {
            if (y < o.y)
                return -1;
            else if (y > o.y)
                return 1;
            else
                return 0;
        }
    }
}
