package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;

public class DeathMatch extends Game{
	void start(CollideObjectManager myObjManager, String playerName){
		int myid = 0; //must be changed!
		this.setPlayerID(myid);
	}
	int update() {
		int updateNeed = 0;
		//return 1 if the player GG!
		//return 2 if the player win!
		//return 0 for nothing happened (game continue)
		/*Character me = this.getPlayerList()[this.getPlayerID()];
		if (me.getHP() <= 0.0)
			updateNeed = 1;
		if (this.getPlayerList().existNum == 1 && me.getHP() > 0.0)
			updateNeed = 2;*/
		return updateNeed;
	}
	

}
