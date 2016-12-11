package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class MagicBall extends AttackObject{
	int radius = 12;
	int range;
	private double traversal = 0;
	int FPS = 20;//隨便設定的

	protected MagicBall(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setPosition, cOManager, className, setPlayer);
		collider = new CircleCollider(position, radius);
		range = (int) (player.getSpeed()*3);// maybe the range is for the arrow fly for 5 seconds
		player = setPlayer;
		damage = (int) player.damagePoint;
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
	
}
