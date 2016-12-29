package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class SlashBig extends Slash {

    public SlashBig(int setID, CollideObjectManager cOManager, int className, Character setPlayer) {
        super(setID, cOManager, className, setPlayer);
        damage = damage * 3;
        width = 52;
        collider = new RectangleCollider(this.position, width, height);
        priority = 5;
    }
}
