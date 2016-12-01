package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public abstract class Game {
	private int playerID;
	private Character[] playerList;
	abstract void start(CollideObjectManager myObjManager, String playerName);
	abstract int update();
	public int getPlayerID() {
		return playerID;
	}
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	public Character[] getPlayerList() {
		return playerList;
	}
	public Character[] setPlayerList(Character[] playerList) {
		this.playerList = playerList;
		return playerList;
	}
}
