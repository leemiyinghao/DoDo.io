package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
        setFlag();
        position = nextPosition;
        collider.update(nextPosition);
    }
    
    protected BufferedImage scaleImage(java.awt.image.BufferedImage appearanece, int w, int h) {
    	// creates output image
        BufferedImage outputImage = new BufferedImage(w,
                h, appearanece.getType());
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(appearanece, 0, 0, 25, 25, null);
        g2d.dispose();
    	return outputImage;
    }

    @Override
    public java.awt.image.BufferedImage getImage() {
    	BufferedImage image = scaleImage(super.getImage(), this.width, this.height);
		return image;
    };
    
    @Override
    public void update() {

    }

    @Override
    public void onCollide(CollideObject whichObjectCollideThis) {
        setFlag();
        if(whichObjectCollideThis.isDead())
            return;
        if (whichObjectCollideThis instanceof Obstacle)
            return;
        this.beAttacked(whichObjectCollideThis);
    }

}
