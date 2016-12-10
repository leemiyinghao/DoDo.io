package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class SlashBig extends Slash{

	protected SlashBig(int setID, String setName, String setTeam,
			Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setName, setTeam, setPosition, cOManager, className, setPlayer);
		damage = damage*3;
		width = 52;
		collider = new RectangleCollider(setPosition, width, height);
	}

}
