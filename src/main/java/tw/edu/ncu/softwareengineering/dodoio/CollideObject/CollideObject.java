package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.util.Date;

import tw.edu.ncu.softwareengineering.dodoio.Collide.Collider;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public abstract class CollideObject {
    protected Position position;
    public final int className;
    public final int ID;
    public final int collideDamage;
    protected boolean isInvincible;
    protected int healthPoint;
    protected Collider collider;
    protected Date date;
    protected Client client;
    private boolean isDead;
    private boolean flag;// server reset it to false

    /**
     * set data of the object
     *
     * @param inputID
     * @param setPosition
     */
    protected CollideObject(int inputID, Position setPosition, CollideObjectManager cOManager, int setClassName) {
    	assert(inputID>=0);
    	assert(setPosition!=null);
        ID = inputID;
        position = setPosition;
        className = setClassName;
        collideDamage = 30;
        healthPoint = 1000;
        isInvincible = false;
        isDead = false;
        flag = false;
        date = new Date();
    }

    public abstract void onCollide(CollideObject whichObjectCollideThis);

    void setClient(Game game) {
    	assert(game!=null);
        client = game.getClient();
    }

    abstract public void update();

    /**
     * when player are attacked, check if it is invincible and change the healthPoint
     *
     * @param damage
     */
    protected void beHarmed(int damage) {
        if (this.isInvincible()) return;

        if (damage >= this.healthPoint) {
            this.dead();
        } else
            this.healthPoint -= damage;
    }

    protected void dead() {
        isDead = true;
    }
    
    /**
     * when move, onCollide set true.
     */
    void setFlag() {
    	flag = true;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public int getHP() {
        return healthPoint;
    }

    public Position getPosition() {
        return position;
    }
//
//    public BufferedImage getImage() {
//    	assert(CollideObjectManager.collideObjectImages[className]!=null);
//        BufferedImage appearanece = CollideObjectManager.collideObjectImages[className];
//        return appearanece;
//    }

    public Collider getCollider() {
        return collider;
    }

    public abstract void move(Position nextPosition);

    /**
     * To get "if the object is dead?"
     *
     * @return
     */
    public boolean isDead() {
        return isDead;
    }
    
    /**
     * server reset it to false
     */
    public void resetFlag() {
    	flag = false;
    }

    public boolean getFlag()
    {
    	return flag;
    }
}
