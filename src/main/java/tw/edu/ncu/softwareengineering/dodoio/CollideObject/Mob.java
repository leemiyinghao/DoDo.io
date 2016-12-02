package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public abstract class Mob extends NonPlayer {
	int radius;
	int exp;
	CircleCollider circleCollider;
	
	protected Mob(int inputID, BufferedImage image, Position setPosition) {
		super(inputID, image, setPosition);
		// TODO Auto-generated constructor stub
	}
	
	public CircleCollider getCircleCollider(){
		return circleCollider;
	}
	
}
