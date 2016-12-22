package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class ArrowStrong extends Arrow{

	ArrowStrong(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setPosition, cOManager, className, setPlayer);
		damage = (int) (player.damagePoint*2.5);
	}

}
