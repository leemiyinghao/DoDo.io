package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class Wanderer extends Mob{
	private int speed = 5;
	private int direction = 0;
	private long oldTime;
	final int FPS = 20;
	
	public Wanderer(int setID, String setName, String setTeam,
			Position setPosition, CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		healthPoint = 1200;
		exp = 4;
		radius = 120;
		collider = new CircleCollider(setPosition, radius);
		oldTime = date.getTime();
	}
	
	/**
	 * call update to change the direction and move
	 */
	public void update() {
		long newTime = date.getTime();
		long updateTime = newTime - oldTime;
		direction = (int) (Math.random());
		wander(updateTime);
		oldTime = newTime;
	}
	/**
	 * move the wanderer by the speed and updateTime
	 * @param updateTime the time past between the time of last update and the time of the update now
	 */
	void wander(long updateTime){
		position.setDirection(direction);
		while(updateTime >= 0) {
			move(Position.projection(speed/FPS, position));
			updateTime-=1000/FPS;
		}
	}
	
	public int getSpeed(){
		return speed;
	}

}
