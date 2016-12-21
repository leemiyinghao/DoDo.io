package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public abstract class AttackObject extends NonPlayer {
    Character player;
    int damage;
    long oldTime;
    int priority;

    protected AttackObject(int setID, CollideObjectManager cOManager, int className, Character setPlayer) {
        super(setID, null, cOManager, className);
        player = setPlayer;
        oldTime = date.getTime();
    }

    public int getDamage() {
        return damage;
    }

    public Character getPlayer() {
        return player;
    }

    @Override
    public void onCollide(CollideObject whichObjectCollideThis) {
        if (whichObjectCollideThis.isDead())
            return;
        if (whichObjectCollideThis instanceof AttackObject && ((AttackObject) whichObjectCollideThis).player.team == this.player.team || this.priority > ((AttackObject) whichObjectCollideThis).priority)
            return;
        this.dead();
    }
}
