package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;
import java.util.Date;

import tw.edu.ncu.softwareengineering.dodoio.Collide.Collider;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public abstract class CollideObject {
	protected Position position;
	public final int className;
	public final int ID;
	public final int collideDamage;
	protected boolean isInvincible;
	protected int healthPoint;
	protected Collider collider;
	protected Date date;
	protected Client client;
	private boolean isDead;
	
	/**set data of the object
	 * 
	 * @param inputID
	 * @param setPosition
	 */
	protected CollideObject(int inputID, Position setPosition, CollideObjectManager cOManager, int setClassName) {
		ID = inputID;
		position = setPosition;
		className = setClassName;
		collideDamage = 30;
		healthPoint = 1000;
		isInvincible = false;
		isDead = false;
		date = new Date();
	}
	
	/**Method to complete
	 * 
	 * @param whichObjectCollideThis
	 */
	public void onCollide(){
		this.beHarmed(collideDamage);
	}
	
	void setClient(Game game) {
		client = game.getClient();
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
	
	public boolean isInvincible() {
		return isInvincible;
	}
	
	public int getHP(){
		return healthPoint;
	}
	
	public Position getPosition(){
		return position;
	}
	
	public BufferedImage getImage(CollideObjectManager manager) {
		BufferedImage appearanece = manager.collideObjectImages[className];
		return appearanece;
	}
	
	public Collider getCollider() {
		return collider;
	}
	
	public abstract void move(Position nextPosition);

	/**To get "if the object is dead?"
	 * 
	 * @return
	 */
	public boolean isDead() {
		return isDead;
	}
	
}
