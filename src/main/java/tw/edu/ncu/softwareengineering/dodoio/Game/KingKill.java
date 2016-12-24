package tw.edu.ncu.softwareengineering.dodoio.Game;

import java.io.IOException;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class KingKill extends Game{
	private int friendPlayerKingID = -1;
	private int enemyPlayerKingID = -1;
	public void start(String playerName, CollideObjectManager.collideObjecctClass chracterclass, int gameMode){
		//need to get friend and enemy King ID
		try {
			this.myObjManager = new CollideObjectManager(this);
		} catch (IOException e) {
			System.out.println("can't new a instance of collideobjectmanager!");
			e.printStackTrace();
		}
		this.client = new Client(this, playerName, chracterclass, gameMode);
	}

	/*
	 * return CONTINUE for nothing happened (game continue)!
	 * return 1 if the enemy boss is dead! -> player team win!
	 * return 2 if the friend boss is dead! -> enemy team win!
	 * return 3 if the player is dead! -> player GG, return to title!
	 */
	public GameState update() {
		GameState gamestate = GameState.CONTINUE;
		Character me = null;
		
		try {
			me = this.myObjManager.getMyPlayer();
		} catch (Exception e) {
			System.out.println("can't get player, is player dead?");
			e.printStackTrace();
		}
		
		if ((CheckEnemyKing() && CheckFriendKing())) {
			Character enemyKing = null;
			Character friendKing = null;
			try {
				enemyKing = (Character)this.myObjManager.queryObjectByID(enemyPlayerKingID);
			} catch (Exception e) {
				enemyKing = null;
			}
			try {
				friendKing = (Character)this.myObjManager.queryObjectByID(friendPlayerKingID);
			} catch (Exception e) {
				friendKing = null;
			}
			if (enemyKing.getHP() <= 0.0)
				gamestate = GameState.PLAYERTEAMWIN;
			else if (friendKing.getHP() <= 0.0)
				gamestate = GameState.ENEMYTEAMWIN;
			else if(me == null)
				gamestate = GameState.PLAYERDEAD;
		}
		else {
			System.out.println("no friendKing or enemyKing");
		}
		return gamestate;
	}
	public int getFriendPlayerKingID() {
		return friendPlayerKingID;
	}
	
	public int getEnemyPlayerKingID() {
		return enemyPlayerKingID;
	}
	public void setFriendPlayerKingID(int id) {
		friendPlayerKingID = id;
	}
	public void setEnemyPlayerKingID(int id) {
		enemyPlayerKingID = id;
	}
	
	private Boolean CheckEnemyKing() {
		if (enemyPlayerKingID == -1) {
			return false;
		}
		return true;
	}
	private Boolean CheckFriendKing() {
		if (friendPlayerKingID == -1) {
			return false;
		}
		return true;
	}
}
