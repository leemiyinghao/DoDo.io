package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public abstract class Collider {
    protected Position position = new Position(0, 0, 0);

    /**
     * Use to update the collider's position
     *
     * @param position the new position
     */
    public abstract void update(Position position);
}
