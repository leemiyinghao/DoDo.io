package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class MagicBall extends AttackObject{
	Magician player;
	int radius = 12;
	private double traversal = 0;
	int FPS = 20;//隨便設定的

	protected MagicBall(int inputID, BufferedImage image, Position setPosition, Magician setPlayer) {
		super(inputID, image, setPosition);
		collider = new CircleCollider(position, radius);
		player = setPlayer;
		super.player = setPlayer;
		damage = (int) player.damagePoint;
	}

	/**fly through until its traversal out of range
	 * 
	 */
	@Override
	public void run() {
		while(traversal <= player.range && !isDead()){
			try {
				double magicBallSpeed = player.speed*4;
				traversal+=magicBallSpeed;
				position = Position.projection(magicBallSpeed, position);
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dead();
	}
	
}
