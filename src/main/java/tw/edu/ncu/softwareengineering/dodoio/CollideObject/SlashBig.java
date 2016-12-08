package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class SlashBig extends Slash{

	protected SlashBig(int inputID, BufferedImage image, Position setPosition,
			SwordMan setPlayer) {
		super(inputID, image, setPosition, setPlayer);
		damage = damage*3;
		width = 52;
		rectangleCollider = new RectangleCollider(setPosition, width, height);
	}

}
