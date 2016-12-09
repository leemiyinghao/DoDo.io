package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class SlashBig extends Slash{

	protected SlashBig(int inputID, BufferedImage image, Position setPosition,
			SwordMan setPlayer) {
		super(inputID, image, setPosition, setPlayer);
		damage = damage*3;
		width = 52;
		collider = new RectangleCollider(setPosition, width, height);
	}

}
