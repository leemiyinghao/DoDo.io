package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Obstacle extends NonPlayer {
	public final int width;
	public final int height;

	protected Obstacle(int inputID, BufferedImage image, Position setPosition,int setWidth, int setHeight, boolean destroyable) {
		super(inputID, image, setPosition);
		width = setWidth;
		height = setHeight;
		
		collider = new RectangleCollider(position, width, height);
		isInvincible = !destroyable;
		healthPoint = 1000;
	}


	/**Only player do "attack" by using attack or skill, others do "collide"
	 * 
	 * @param attacker
	 */
	public void beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
	}
	
}
