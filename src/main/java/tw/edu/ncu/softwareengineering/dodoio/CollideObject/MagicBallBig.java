package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class MagicBallBig extends MagicBall{

	protected MagicBallBig(int setID, String setName, String setTeam,
			Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, setName, setTeam, setPosition, cOManager, className, setPlayer);
		radius = (int) (radius*1.2);
		damage = (int) (damage*2.5);
	}

}
