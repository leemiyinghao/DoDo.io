package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class SwordMan extends Character {

	public SwordMan(int setID, String setName, String setTeam,
			BufferedImage image, Position setPosition) {
		super(setID, setName, setTeam, image, setPosition);

		damagePoint+=80;
		healthPoint+=100;
		recoveryCD = recoveryCD*0.9;
	}

	@Override
	AttackObject attack(int setID) {
		AttackObject slash = new Slash(setID, slashImage, position);
		return slash;
	}

	@Override
	AttackObject skill(int setID) {
		AttackObject slash = new SlashBig(setID, slashImage, position);
		return slash;
	}

}
