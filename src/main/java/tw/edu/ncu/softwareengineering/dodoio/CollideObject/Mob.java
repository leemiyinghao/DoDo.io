package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public abstract class Mob extends NonPlayer {
	int radius;
	int exp;
	
	protected Mob(int setID, Position setPosition, CollideObjectManager cOManager, int className) {
		super(setID, setPosition, cOManager, className);
		// TODO Auto-generated constructor stub
	}
	
	/**Only player do "attack" by using attack or skill, others do "collide"
	 * collide module will use as : 
	 * collideObjectManage.collideObjectList[attackerIndx] = collideObjectList[attackedMobIndx].beAttacked(Character attacker);
	 * 
	 * @param attacker
	 */
	public void beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
		if(isDead()){
			attacker.addExp(exp);
		}
	}
	
	@Override
	public void move(Position nextPosition) 
	{
		position = nextPosition;
		collider.update(nextPosition);
	};
	
}
