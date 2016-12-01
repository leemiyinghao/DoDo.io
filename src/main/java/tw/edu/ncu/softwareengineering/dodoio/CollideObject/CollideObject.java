package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public abstract class CollideObject {
	protected Position position;
	public BufferedImage appearance;
	final int ID;
	protected boolean isInvincible;
	protected int healthPoint;
	private boolean isDead;
	
	/**set data of the object
	 * 
	 * @param inputID
	 * @param image
	 * @param setPosition
	 */
	protected CollideObject(int inputID, BufferedImage image, Position setPosition) {
		ID = inputID;
		appearance = image;
		position = setPosition;
		healthPoint = 1000;
		isInvincible = false;
		isDead = false;
	}
	
	/**Method to complete
	 * 
	 * @param whichObjectCollideThis
	 */
	public abstract void onCollide(CollideObject whichObjectCollideThis);
	
	public Position getObjectPosition() {
		return position;
	}
	
	public boolean isInvincible() {
		return isInvincible;
	}
	
	/**when player are attacked, check if it will dead and change the healthPoint
	 * 
	 * @param damage
	 */
	protected void beAttacked(int damage) {
		if(this.isInvincible()) return;
		
		if(damage >= this.healthPoint) {
			this.dead();
		}
		else
			this.healthPoint-=damage;
	}
	
	protected void dead() {
		isDead = true;
	}
	
	public int getHP(){
		return healthPoint;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public void move(Position nextPosition){
		position = nextPosition;
	}
	/**To get "if the object is dead?"
	 * 
	 * @return
	 */
	public boolean isDead() {
		return isDead;
	}
	
}
