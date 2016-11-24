package tw.edu.ncu.softwareengineering.dodoio.collideObject;

import java.awt.image.BufferedImage;

public class Obstacle extends NonPlayer {
	public final int width;
	public final int height;
	private RectangleCollider rectangleCollider;

	protected Obstacle(int inputID, BufferedImage image, Position setPosition,int setWidth, int setHeight, boolean destroyable) {
		super(inputID, image, setPosition);
		width = setWidth;
		height = setHeight;
		
		rectangleCollider = new RectangleCollider(position, width, height);
		isInvincible = !destroyable;
	}

	@Override
	public void onCollide(CollideObject whichObjectCollideThis) {
		if(whichObjectCollideThis.getClass().getName().equals("AttackObject")){
			//This part is not mine?
		}
		else{
			//
		}
	}
	
}
