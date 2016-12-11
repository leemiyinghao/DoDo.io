package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class Chaser extends Mob{
	int speed;
	
	protected Chaser(int setID, Position setPosition, CollideObjectManager cOManager, int className) {
		super(setID, setPosition, cOManager, className);
		radius = 15;
		speed = 6;
		collider = new CircleCollider(setPosition, radius);
	}
	
	public int getSpeed() {
		return speed;
	}

	@Override
	public void update() {
		
	}
}
