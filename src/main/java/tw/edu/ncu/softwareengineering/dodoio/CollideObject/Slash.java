package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Slash extends AttackObject{
	int height = 35;
	int width = 25;
	int FPS = 20;// set for no error. this is not real
	double slashTime = 0.5;
	
	/**
	 * move the slash region out of swordman's body
	 * 
	 * @param inputID
	 * @param image
	 * @param setPosition
	 * @param setPlayer
	 */
	protected Slash(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setPosition, cOManager, className, setPlayer);
		player = setPlayer;
		collider = new RectangleCollider(setPosition, width, height);
		isInvincible = true;
		
		move(Position.projection(player.getRadius(), setPosition));
	}
	
	/**
	 * update it slash time, let it dead when run out of slashTime 
	 */
	@Override
	public void update() {
		long newTime = date.getTime();
		long updateTime = oldTime - newTime;
		while(updateTime >= 0) {
			if(!isDead() && slashTime > 0) {
				slashTime -= 1000/FPS;
			}
			else {
				dead();
				break;
			}
			updateTime -= 1000/FPS;
		}
	}
	
}
