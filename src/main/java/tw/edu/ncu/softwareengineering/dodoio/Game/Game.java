package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public abstract class Game {
	public CollideObjectManager myObjManager;
	Client client;
	int friendPlayerKingID = -1;
	int enemyPlayerKingID = -1;
	
	public Client getClient() {
		return this.client;
	}
	public int getFriendPlayerKingID() {
		return this.friendPlayerKingID;
	}
	
	public int getEnemyPlayerKingID() {
		return this.enemyPlayerKingID;
	}
	public void setFriendPlayerKingID(int id) {
		this.friendPlayerKingID = id;
	}
	public void setEnemyPlayerKingID(int id) {
		this.enemyPlayerKingID = id;
	}
	
	public CollideObjectManager getCollideObjectManager(){
		return this.myObjManager;
	}
	public abstract void start(String playerName, CollideObjectManager.collideObjecctClass chracterclass, int gameMode);
	public abstract GameState update();
	
}
