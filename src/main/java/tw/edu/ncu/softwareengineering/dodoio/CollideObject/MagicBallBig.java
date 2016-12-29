package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class MagicBallBig extends MagicBall{

	public MagicBallBig(int setID, CollideObjectManager cOManager, int className, Character setPlayer) {
		super(setID, cOManager, className, setPlayer);
		radius = (int) (radius*1.2);
		damage = (int) (damage*2.5);
		priority =4;
	}
}
