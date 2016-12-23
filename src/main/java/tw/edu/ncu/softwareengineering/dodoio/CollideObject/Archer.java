package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;


public class Archer extends Character {
	
	
	public Archer(int setID, String setName, TeamName setTeam, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		attackCD = attackCD*0.8;
		speed = speed*1.2;
		skillCD = 11;
	}
	
//	@Override
//	public void attack() {
//		if(attackActive) {
//			attackActive = false;
//			attackCountDown = attackCD;
//			// tell server you do attack
//			client.newAttack(collideObjecctClass.Arrow, this.ID);
//		}
//		else
//			return;
//	}
//
//	@Override
//	public void skill() {
//		if(skillActive) {
//			skillActive = false;
//			skillCountDown = skillCD;
//			// tell server you do skill
//			client.newAttack(collideObjecctClass.ArrowStrong, this.ID);
//		}
//		else
//			return;
//	}
}
