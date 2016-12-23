package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;


public class SwordMan extends Character {

	public SwordMan(int setID, String setName, TeamName setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);

		damagePoint+=80;
		healthPoint+=100;
		recoveryCD = recoveryCD*0.9;
	}
	
	@Override
	public void attack() {
		if(attackActive) {
			attackActive = false;
			attackCountDown = attackCD;
			assert(client!=null);
			client.newattack(collideObjecctClass.Slash, this.ID);
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
			client.newattack(collideObjecctClass.SlashBig, this.ID);
		}
		else
			return;
	}

}
