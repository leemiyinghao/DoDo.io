package tw.edu.ncu.softwareengineering.dodoio.Game;

import java.io.IOException;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class DeathMatch extends Game{
	public void start(String playerName, CollideObjectManager.collideObjecctClass chracterclass, int gameMode){
		try {
			this.myObjManager = new CollideObjectManager(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//get active player number by for-loop
		if (me == null)
			gamestate = GameState.PLAYERDEAD;
		else {
			int activePlayerNum = 0;
			ArrayList<CollideObject> list = this.myObjManager.collideObjectList;
			for(CollideObject i: list) {
				if(i instanceof Character) {
					activePlayerNum++;
				}
			}
			if (activePlayerNum == 1)
				gamestate = GameState.PLAYERWIN;
		}
		return gamestate;
	}
}
