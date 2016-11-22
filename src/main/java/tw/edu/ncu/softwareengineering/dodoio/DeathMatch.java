package tw.edu.ncu.softwareengineering.dodoio;

public class DeathMatch extends Game{
	void start(CollideObjectManager myObjManager, String IP, String playerName){
		Server server = new Server(IP);
		this.playerID = server.getNewID();
		this.playerList = myObjManager.playerList;
		Player me = this.playerList[this.playerID];
		me.setName(playerName);
		
		
	}
	int update() {
		int updateNeed = 0;
		//return 1 if the player GG!
		//return 2 if the player win!
		//return 0 for nothing happened (game continue)
		Player me = this.playerList[this.playerID];
		if (me.getHP() <= 0.0)
			updateNeed = 1;
		if (this.playerList.existNum == 1 && me.getHP() > 0.0)
			updateNeed = 2;
		return updateNeed;
	}
	

}
