package tw.edu.ncu.softwareengineering.dodoio.CollideObject;


public class Archer extends Character {
	
	
	public Archer(int setID, String setName, String setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		attackCD = attackCD*0.8;
		speed = speed*1.2;
		skillCD = 11;
	}

}
