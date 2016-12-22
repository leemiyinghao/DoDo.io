package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.Vector;

public abstract class Mob extends NonPlayer {
    int radius;
    int exp;

    protected Mob(int setID, Position setPosition, CollideObjectManager cOManager, int className) {
        super(setID, setPosition, cOManager, className);
        // TODO Auto-generated constructor stub
    }

    /**
     * Only player do "attack" by using attack or skill, others do "collide"
     * collide module will use as :
     * collideObjectManage.collideObjectList[attackerIndx] = collideObjectList[attackedMobIndx].beAttacked(Character attacker);
     *
     * @param attacker
     */
    public void beAttacked(CollideObject attacker) {
        if (attacker instanceof AttackObject) {
            this.beHarmed(((AttackObject) attacker).damage);
            if (isDead()) {
                ((AttackObject) attacker).player.addExp(exp);
            }
        } else if (attacker instanceof Character) {
            this.beHarmed(attacker.collideDamage);
            if (isDead()) {
                ((Character) attacker).addExp(exp);
            }
        } else
            this.beHarmed(attacker.collideDamage);
    }

    @Override
    public void move(Position nextPosition) {
        position = nextPosition;
        collider.update(nextPosition);
    }

    @Override
    public void onCollide(CollideObject whichObjectCollideThis) {
        if(whichObjectCollideThis.isDead())
            return;
        this.beAttacked(whichObjectCollideThis);
        //the unit vector of whichObject to this object
        Vector v = new Vector(this.position.getX() - whichObjectCollideThis.position.getX(), this.position.getY() - whichObjectCollideThis.position.getY()).normalized();
        this.move(new Position((int) (5 * v.getX()), (int) (+5 * v.getY()), this.position.getDirection()));
    }
}
