package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public abstract class Mob extends NonPlayer {
	int radius;
	int exp;
	
	protected Mob(int inputID, BufferedImage image, Position setPosition) {
		super(inputID, image, setPosition);
		// TODO Auto-generated constructor stub
	}
	

	/**Only player do "attack" by using attack or skill, others do "collide"
	 * collide module will use as : 
	 * collideObjectManage.collideObjectList[attackerIndx] = collideObjectList[attackedMobIndx].beAttacked(Character attacker);
	 * 
	 * @param attacker
	 */
	public Character beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
		if(isDead()){
			attacker.addExp(exp);
		}
		return attacker;
	}
	
}
