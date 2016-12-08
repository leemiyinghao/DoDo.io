package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public abstract class Collider {
    protected Position position = new Position(0, 0, 0);

    /**
     * return the  position of this Collider
     *
     * @return
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Use to update the collider's position
     *
     * @param position
     */
    public abstract void update(Position position);
}
