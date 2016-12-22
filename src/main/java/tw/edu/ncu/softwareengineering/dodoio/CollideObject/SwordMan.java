package tw.edu.ncu.softwareengineering.dodoio.CollideObject;


public class SwordMan extends Character {

	public SwordMan(int setID, String setName, String setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);

		damagePoint+=80;
		healthPoint+=100;
		recoveryCD = recoveryCD*0.9;
	}

}
