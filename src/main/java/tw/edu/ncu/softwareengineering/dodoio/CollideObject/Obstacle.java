package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Obstacle extends NonPlayer {
    public final int width;
    public final int height;

    public Obstacle(int setID, Position setPosition, CollideObjectManager cOManager,
                    int className, int setWidth, int setHeight, boolean destroyable) {
        super(setID, setPosition, cOManager, className);
        width = setWidth;
        height = setHeight;

        collider = new RectangleCollider(position, width, height);
        isInvincible = !destroyable;
        healthPoint = 1000;
    }


    /**
     * Only player do "attack" by using attack or skill, others do "collide"
     *
     * @param attacker
     */
    public void beAttacked(CollideObject attacker) {
        if (attacker instanceof AttackObject)
            this.beHarmed(((AttackObject) attacker).damage);
        else
            this.beHarmed(attacker.collideDamage);
    }

    @Override
    public void move(Position nextPosition) {
        position = nextPosition;
        collider.update(nextPosition);
    }

    ;

    @Override
    public void update() {

    }

    @Override
    public void onCollide(CollideObject whichObjectCollideThis) {
        if(whichObjectCollideThis.isDead())
            return;
        if (whichObjectCollideThis instanceof Obstacle)
            return;
        this.beAttacked(whichObjectCollideThis);
    }
}
