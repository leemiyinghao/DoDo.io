package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class Magician extends Character{

	public Magician(int setID, String setName, String setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		
		damagePoint+=100;
		skillCD = 8;
	}

}
