package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;
import java.util.Date;

import tw.edu.ncu.softwareengineering.dodoio.Collide.Collider;

public abstract class CollideObject {
	protected Position position;
	public BufferedImage  appearance;
	public final int ID;
	public final int collideDamage = 30;
	protected boolean isInvincible;
	protected int healthPoint;
	protected Collider collider;
	protected Date date;
	private boolean isDead;
	
	/**set data of the object
	 * 
	 * @param inputID
	 * @param setPosition
	 */
	protected CollideObject(int inputID, Position setPosition, CollideObjectManager cOManager, int className) {
		date = new Date();
		ID = inputID;
		appearance = cOManager.collideObjectImages[className];
		position = setPosition;
		healthPoint = 1000;
		isInvincible = false;
		isDead = false;
	}
	
	/**Method to complete
	 * 
	 * @param whichObjectCollideThis
	 */
	public void onCollide(CollideObject whichObjectCollideThis){
		this.beHarmed(collideDamage);
	}
	
	public boolean isInvincible() {
		return isInvincible;
	}
	
	abstract public void update();
	
	/**when player are attacked, check if it is invincible and change the healthPoint
	 * 
	 * @param damage
	 */
	protected void beHarmed(int damage) {
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
	
	public Collider getCollider() {
		return collider;
	}
	
	public void move(Position nextPosition){
		position = nextPosition;
		collider.update(nextPosition);
	}
	/**To get "if the object is dead?"
	 * 
	 * @return
	 */
	public boolean isDead() {
		return isDead;
	}
	
}
