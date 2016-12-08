package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class Chaser extends Mob{

	protected Chaser(int inputID, BufferedImage image, Position setPosition) {
		super(inputID, image, setPosition);
		radius = 15;
		collider = new CircleCollider(setPosition, radius);
	}

}
