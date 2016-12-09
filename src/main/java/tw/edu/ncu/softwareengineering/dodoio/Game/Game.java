package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public abstract class Game {
	public CollideObjectManager myObjManager;
	abstract void start(String playerName, CollideObjectManager.Profession profession, int gameMode);
	abstract int update();
	
}
