package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public abstract class AttackObject extends NonPlayer{
	Character player;
	int damage;
	long oldTime;
	
	protected AttackObject(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setPosition, cOManager, className);
		player = setPlayer;
		oldTime = date.getTime();
	}
	public abstract void update();
	
	public int getDamage() {
		return damage;
	}
	
	public Character getPlayer() {
		return player;
	}
}
