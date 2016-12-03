package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public interface ICollider {
    /**Use to update the collider's position
     * @param position
     */
    public void update(Position position);
}
