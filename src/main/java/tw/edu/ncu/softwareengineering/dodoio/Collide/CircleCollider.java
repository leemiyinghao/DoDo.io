package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public class CircleCollider extends Collider {
    private int radius;

    /**
     * Create a CircleCollider with position and radius
     *
     * @param position
     * @param radius
     */
    public CircleCollider(Position position, int radius) {
        super();
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

    @Override
    public void update(Position position) {
        this.position.setPosition(position.getX(), position.getY(), position.getDirection());
    }
}
