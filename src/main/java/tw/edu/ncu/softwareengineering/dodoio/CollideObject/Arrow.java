package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Arrow extends CollideObject implements Runnable{
	private Archer player;
	private int damage;
	private double traversal = 0;
	
	
	
	protected Arrow(int inputID, BufferedImage image, Position setPosition, Archer setPlayer, int setDamage) {
		super(inputID, image, setPosition);
		player = setPlayer;
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
				double arrowSpeed = player.speed*4;
				traversal+=arrowSpeed;
				casting(traversal);
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dead();
	}
	
	/**把距離投射成在地圖上實際移動到的位置
	 * 
	 * @param setTraversal
	 */
	void casting(double setTraversal){
		int x = (int) ((Math.cos(position.getDirection()*(2*Math.PI)))*setTraversal);
		int y = (int) ((Math.sin(position.getDirection()*(2*Math.PI)))*setTraversal);
		position.setPosition(position.getX()+x, position.getY()+y, position.getDirection());
	}
	
}
