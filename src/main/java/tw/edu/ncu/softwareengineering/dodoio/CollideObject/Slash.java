package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Slash extends AttackObject{
	SwordMan player;
	int height = 35;
	int width = 25;
	int FPS = 20;// set for no error. this is not real
	final double slashTime = 0.5;
	
	/**
	 * move the slash region out of swordman's body
	 * 
	 * @param inputID
	 * @param image
	 * @param setPosition
	 * @param setPlayer
	 */
	protected Slash(int inputID, BufferedImage image, Position setPosition, SwordMan setPlayer) {
		super(inputID, image, setPosition);
		player = setPlayer;
		collider = new RectangleCollider(setPosition, width, height);
		
		move(Position.projection(player.getRadius(), setPosition));
	}

	@Override
	public void run() {
		double timeRested = slashTime;
		while(timeRested > 0 && !isDead()) {
			try {
				timeRested = timeRested - 1/FPS;
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dead();
	}

}
