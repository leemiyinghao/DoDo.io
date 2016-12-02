package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class MagicBall extends CollideObject implements Runnable{
	private Magician player;
	private int damage;
	private double traversal = 0;
	private CircleCollider circleCollider;
	int FPS = 20;//隨便設定

	protected MagicBall(int inputID, BufferedImage image, Position setPosition) {
		super(inputID, image, setPosition);
		// TODO Auto-generated constructor stub
	}

	/**fly through until its traversal out of range
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(traversal <= player.range){
			try {
				double magicBallSpeed = player.speed*4;
				traversal+=magicBallSpeed;
				casting(traversal);
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dead();
	}
	
	/**把距離投射成在地圖上實際改變的位置
	 * 
	 * @param setTraversal
	 */
	void casting(double setTraversal){
		int x = (int) ((Math.cos(position.getDirection()*(2*Math.PI)))*setTraversal);
		int y = (int) ((Math.sin(position.getDirection()*(2*Math.PI)))*setTraversal);
		position.setPosition(position.getX()+x, position.getY()+y, position.getDirection());
	}
	
	public CircleCollider getCircleCollider(){
		return circleCollider;
	}
	
}
