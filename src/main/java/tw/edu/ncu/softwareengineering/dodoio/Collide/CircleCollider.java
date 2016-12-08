package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public class CircleCollider implements ICollider {
    private int radius;
    private Position position = new Position(0, 0, 0);

    /**
     * Create a CircleCollider with position and radius
     *
     * @param position
     * @param radius
     */
    public CircleCollider(Position position, int radius) {
        this.position.setPosition(position.getX(), position.getY(), position.getDirection());
        this.radius = radius;
    }

    /**
     * return the radius of the collider
     *
     * @return
     */
    public int getRadius() {
        return radius;
    }

    /**
     * return the  position of this CircleCollider
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    @Override
    public void update(Position position) {
        this.position.setPosition(position.getX(), position.getY(), position.getDirection());
    }
}
