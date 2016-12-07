package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public abstract class AttackObject extends CollideObject implements Runnable{

	int damage;
	
	protected AttackObject(int inputID, BufferedImage image,
			Position setPosition) {
		super(inputID, image, setPosition);
	}
	
}
