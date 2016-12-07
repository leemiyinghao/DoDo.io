package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public abstract class AttackObject extends CollideObject implements Runnable{

	int damage;
	
	protected AttackObject(int inputID, BufferedImage image,
			Position setPosition) {
		super(inputID, image, setPosition);
		// TODO Auto-generated constructor stub
	}

	/**input the traversal, the object will know where do it move to
	 * 
	 * @param setTraversal
	 */
	void casting(double setTraversal){
		int x = (int) ((Math.cos(position.getDirection()*(2*Math.PI)))*setTraversal);
		int y = (int) ((Math.sin(position.getDirection()*(2*Math.PI)))*setTraversal);
		position.setPosition(position.getX()+x, position.getY()+y, position.getDirection());
	}
	
}
