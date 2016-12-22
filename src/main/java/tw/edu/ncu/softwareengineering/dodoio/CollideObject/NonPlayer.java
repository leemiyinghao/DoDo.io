package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public abstract class NonPlayer extends CollideObject {

	protected NonPlayer(int setID, Position setPosition,
			CollideObjectManager cOManager, int className) {
		super(setID, setPosition, cOManager, className);
	}

}
