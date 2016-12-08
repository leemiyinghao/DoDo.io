package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public class RectangleCollider extends Collider {
    private Point[] points = new Point[4];

    /**
     * Create a rotated RectangleCollider
     *
     * @param width
     * @param height
     * @param position
     */
    public RectangleCollider(Position position, int width, int height) {
        super();
        this.position.setPosition(position.getX(), position.getY(), position.getDirection());
        points[0] = rotatePoint(new Point(position.getX() - width / 2, position.getY() - height / 2), 2 * Math.PI * (1 - position.getDirection()));
        points[1] = rotatePoint(new Point(position.getX() + width / 2, position.getY() - height / 2), 2 * Math.PI * (1 - position.getDirection()));
        points[2] = rotatePoint(new Point(position.getX() - width / 2, position.getY() + height / 2), 2 * Math.PI * (1 - position.getDirection()));
        points[3] = rotatePoint(new Point(position.getX() + width / 2, position.getY() + height / 2), 2 * Math.PI * (1 - position.getDirection()));
    }

    /**
     * return the points of the collider
     *
     * @return
     */
    public Point[] getPoints() {
        return points;
    }

    @Override
    public void update(Position position) {
        int XOffset = position.getX() - this.position.getX();
        int YOffset = position.getY() - this.position.getY();
        for (Point point : points) {
            point.x += XOffset;
            point.y += YOffset;
        }
        this.position.setPosition(position.getX(), position.getY(), position.getDirection());
    }

    /**
     * Rotate a point with angle
     *
     * @param p
     * @param angle
     * @return a rotated point
     */
    private Point rotatePoint(Point p, double angle) {
        //change origin to current position
        double x = p.x - position.getX();
        double y = p.y - position.getY();
        //multiply rotate matrix
        x = Math.round(Math.cos(angle) * x - Math.sin(angle) * y);
        y = Math.round(Math.sin(angle) * x + Math.cos(angle) * y);
        //change origin to 0,0
        x += position.getX();
        y += position.getY();
        return new Point(x, y);
    }
}
