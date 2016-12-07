package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Magician extends Character{

	int range;

	public Magician(int setID, String setName, String setTeam,
			BufferedImage image, Position setPosition) {
		super(setID, setName, setTeam, image, setPosition);
		damagePoint+=100;
		skillCD = 8;
		range = (int) (speed*5*2.5);// maybe the range is for the arrow fly for 5 seconds
	}

	@Override
	AttackObject attack(int setID) {
		AttackObject magicBall;
		magicBall = new MagicBall(setID, magicBallImage, position, this);
		return magicBall;
		
	}

	@Override
	AttackObject skill(int setID) {
		AttackObject magicBall = new MagicBall(setID, magicBallImage, position, this);
		return magicBall;
	}

}
