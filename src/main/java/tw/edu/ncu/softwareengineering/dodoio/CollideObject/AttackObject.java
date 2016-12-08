package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public abstract class AttackObject extends CollideObject implements Runnable{
	Character player;
	int damage;
	
	protected AttackObject(int inputID, BufferedImage image,
			Position setPosition) {
		super(inputID, image, setPosition);
	}
	
	public int getDamage() {
		return damage;
	}
	
	public Character getPlayer() {
		return player;
	}
}
