package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Arrow extends AttackObject{
	Archer player;
	double traversal = 0;
	int height = 50;
	int width = 4;
	RectangleCollider rectangleCollider;
	int FPS = 20;// set for no error. this is not real
	
	Arrow(int inputID, BufferedImage image, Position setPosition, Archer setPlayer) {
		super(inputID, image, setPosition);
		player = setPlayer;
		damage = (int) player.damagePoint;
		rectangleCollider = new RectangleCollider(setPosition, width, height);
	}
	
	/**fly through until its traversal out of range
	 * 
	 */
	@Override
	public void run() {
		while(traversal <= player.range && !isDead()){
			try {
				double arrowSpeed = player.speed*4;
				traversal+=arrowSpeed;
				move(Position.projection(arrowSpeed, position));
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dead();
	}
	
	
	public RectangleCollider getRectangleCollider() {
		return rectangleCollider;
	}
	
}
