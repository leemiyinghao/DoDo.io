package tw.edu.ncu.softwareengineering.dodoio.Game;

import java.io.IOException;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class KingKill extends Game{
	GameState inner_gamestate = GameState.ZERO;
	int activePlayerNum = 0;
	public void start(String playerName, CollideObjectManager.collideObjecctClass chracterclass, int gameMode){
		try {
			this.myObjManager = new CollideObjectManager(this);
		} catch (IOException e) {
			System.out.println("can't new an instance of CollideObjectManager!");
			e.printStackTrace();
		}
		//need to get friend and enemy King ID
		this.client = new Client(this, playerName, chracterclass, gameMode);
	}

	/*
	 * return GameState.CONTINUE		for nothing happened)!
	 * return GameState.PLAYERTEAMWIN	if the enemy boss is dead!
	 * return GameState.ENEMYTEANWIN	if the friend boss is dead!
	 * return GameState.PLAYERDEAD		if the player is dead! -> then return to title!
	 */
	public GameState update() {
		GameState gamestate = GameState.CONTINUE;
		activePlayerNum = GetActivePlayerNum(this.myObjManager.collideObjectList);
		InnerStateMachineRun();
		
		if(inner_gamestate == GameState.MORETHENONE) {
			if ((CheckEnemyKing() && CheckFriendKing())) {
				Character me = null;
				Character enemyKing = null;
				Character friendKing = null;
				try {
					me = this.myObjManager.getMyPlayer();
				} catch (Exception e) {
					//if null then the player is dead!
				}
				try {
					enemyKing = (Character)this.myObjManager.queryObjectByID(enemyPlayerKingID);
				} catch (Exception e) {
					//if not get then enemyKing is dead!
				}
				try {
					friendKing = (Character)this.myObjManager.queryObjectByID(friendPlayerKingID);
				} catch (Exception e) {
					//if not get then friendKing is dead!
				}
				if (enemyKing == null || enemyKing.getHP() <= 0) {
					gamestate = GameState.PLAYERTEAMWIN;
					inner_gamestate = GameState.PLAYERTEAMWIN;
				}
				else if (friendKing == null || friendKing.getHP() <= 0) {
					gamestate = GameState.ENEMYTEAMWIN;
					inner_gamestate = GameState.PLAYERTEAMWIN;
				}
				else if (me == null) {
					gamestate = GameState.PLAYERDEAD;
					inner_gamestate = GameState.MORETHENONE;
				}
				else 
					gamestate = GameState.CONTINUE;
			}
			else {
				//no friendKing or enemyKing
				gamestate = GameState.CONTINUE;
			}
		}
		return gamestate;
	}
	
	
	public boolean CheckEnemyKing() {
		if (enemyPlayerKingID == -1) {
			return false;
		}
		return true;
	}
	public boolean CheckFriendKing() {
		if (friendPlayerKingID == -1) {
			return false;
		}
		return true;
	}
	//get active player number by for-loop
	int GetActivePlayerNum(ArrayList<CollideObject> list){
		int activePlayerNum = 0;
		for(CollideObject i: list) {
			if(i instanceof Character) {
				activePlayerNum++;
			}
		}
		return activePlayerNum;
	}
	
	void InnerStateMachineRun() {
		if (inner_gamestate == GameState.ZERO || inner_gamestate == GameState.ONE) {
			if (inner_gamestate == GameState.ZERO && activePlayerNum > 0) {
				if (activePlayerNum == 1)
					inner_gamestate = GameState.ONE;
				else
					inner_gamestate = GameState.MORETHENONE;
				return;
			}
			if (inner_gamestate == GameState.ONE && activePlayerNum > 1) {
				inner_gamestate = GameState.MORETHENONE;
				return;
			}
		}
		return;
	}
}
