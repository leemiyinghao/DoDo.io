package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Arrow extends AttackObject{
	double traversal = 0;
	int range;
	int height = 50;
	int width = 4;
	RectangleCollider rectangleCollider;
	int FPS = 20;// set for no error. this is not real
	
	Arrow(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setPosition, cOManager, className, setPlayer);
		damage = (int) player.damagePoint;
		range = (int) (player.getSpeed()*5);
		rectangleCollider = new RectangleCollider(setPosition, width, height);
	}

	@Override
	public void update() {
		long newTime = date.getTime();
		fly(newTime - oldTime);
		oldTime = newTime;
	}
	/**
	 * move the arrow by the speed and updateTime
	 * fly through until its traversal out of range
	 * @param updateTime the time past between the time of last update and the time of the update now
	 */
	void fly(long updateTime) {
		while(updateTime >= 0) {
			if(traversal <= range && !isDead()) {
				double arrowSpeed = player.speed*4;
				traversal+=arrowSpeed;
				move(Position.projection(arrowSpeed, position));
			}
			else{
				dead();
				break;
			}
			updateTime-=1000/FPS;
		}
	}
	
	public RectangleCollider getRectangleCollider() {
		return rectangleCollider;
	}
	
}
