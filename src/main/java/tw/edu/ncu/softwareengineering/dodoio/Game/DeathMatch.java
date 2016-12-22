package tw.edu.ncu.softwareengineering.dodoio.Game;

import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class DeathMatch extends Game{
	void start(String playerName, CollideObjectManager.ChracterClass chracterclass, int gameMode){
		this.myObjManager = new CollideObjectManager(this);
		this.client = new Client(this, playerName, chracterclass, gameMode);
	}
	int update() {
		int updateNeed = 0;
		int myid = this.myObjManager.getMyPlayer();
		ArrayList<CollideObject> list = this.myObjManager.collideObjectList;
		Character me = (Character)list.get(myid);
		int activePlayerNum = 0;
		//check if player is null or not
		//get active player number for-loop
		
		//return 1 if the player GG!
		//return 2 if the player win!
		//return 0 for nothing happened (game continue)
		if (me == null)
			updateNeed = 1;
		else {
			for(CollideObject i: list) {
				if(i instanceof Character) {
					activePlayerNum++;
				}
			}
			if (activePlayerNum == 1)
				updateNeed = 2;
		}
		return updateNeed;
	}
}
