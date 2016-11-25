package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public abstract class Game {
	public int playerID;
	public Character[] playerList;
	abstract void start(CollideObjectManager myObjManager, String IP, String playerName);
	abstract int update();
}
