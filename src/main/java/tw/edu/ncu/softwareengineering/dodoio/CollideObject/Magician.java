package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;

public class Magician extends Character{

	public Magician(int setID, String setName, TeamName setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		
		damagePoint+=100;
		skillCD = 8;
	}
	
	@Override
	public void attack() {
		if(attackActive) {
			attackActive = false;
			attackCountDown = attackCD;
			assert(client!=null);
			client.newattack(collideObjecctClass.MagicBall, this.ID);
		}
		else
			return;
	}

	@Override
	public void skill() {
		if(skillActive) {
			skillActive = false;
			skillCountDown = skillCD;
			assert(client!=null);
			client.newattack(collideObjecctClass.MagicBallBig, this.ID);
		}
		else
			return;
	}
}
