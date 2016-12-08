package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class ArrowStrong extends Arrow{

	public ArrowStrong(int inputID, BufferedImage image, Position setPosition,
			Archer setPlayer) {
		super(inputID, image, setPosition, setPlayer);
		damage = (int) (player.damagePoint*2.5);
	}

}
