package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class MagicBallBig extends MagicBall{

	protected MagicBallBig(int inputID, BufferedImage image,
			Position setPosition, Magician setPlayer) {
		super(inputID, image, setPosition, setPlayer);
		radius = (int) (radius*1.2);
		damage = (int) (damage*2.5);
	}

}
