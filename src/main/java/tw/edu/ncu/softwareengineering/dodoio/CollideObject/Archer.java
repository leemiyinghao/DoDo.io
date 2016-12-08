package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Archer extends Character {
	int range;
	
	
	public Archer(int setID, String setName, String setTeam,
			BufferedImage image, Position setPosition) {
		super(setID, setName, setTeam, image, setPosition);
		attackCD = attackCD*0.8;
		speed = speed*1.2;
		skillCD = 11;
		range = (int) (speed*5*5);// maybe the range is for the arrow fly for 5 seconds
	}

	@Override
	AttackObject attack(int setID) {
		AttackObject arrow = new Arrow(setID, arrowImage, position, this);
		return arrow;
	}

	@Override
	AttackObject skill(int setID) {
		AttackObject arrow = new ArrowStrong(setID, arrowStrongImage, position, this);
		return arrow;
	}

}
