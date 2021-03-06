package tw.edu.ncu.softwareengineering.dodoio.Game;

import java.io.IOException;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class DeathMatch extends Game{
	public void start(String playerName, CollideObjectManager.collideObjecctClass chracterclass, int gameMode){
		this.myObjManager = new CollideObjectManager(this);
		this.client = new Client(this, playerName, chracterclass, gameMode);
	}

	/* 
	 * return PLAYERDEAD	if the player GG!
	 * return PLAYERWIN		if the player win!
	 * return CONTINUE		for nothing happened (game continue) 
	 */
	public GameState update() {
		GameState gamestate = GameState.CONTINUE;
		Character me = null;
		try {
			me = this.myObjManager.getMyPlayer();
		} catch (Exception e) {
			System.out.println("player appears to be dead?");
		}
		
		//check if player is null or not
		if (me == null || me.getHP() <= 0)
			gamestate = GameState.PLAYERDEAD;
		else {
			int activePlayerNum = GetActivePlayerNum(this.myObjManager.collideObjectList);
			
			if (activePlayerNum == 1)
				gamestate = GameState.PLAYERWIN;
		}
		return gamestate;
	}

	//get active player number by for-loop
	int GetActivePlayerNum(ArrayList<CollideObject> list) {
		int activePlayerNum = 0;
		for(CollideObject i: list) {
			if(i instanceof Character) {
				activePlayerNum++;
			}
		}
		return activePlayerNum;
	}
}
