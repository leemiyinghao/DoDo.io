package Collide;

public class CircleCollider implements ICollider {
    private int radius;
    private Position position;

    public CircleCollider(Position position, int radius) {
        this.position = position;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public Position getPosition() {
        return position;
    }
}
