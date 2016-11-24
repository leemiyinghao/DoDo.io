package tw.edu.ncu.softwareengineering.dodoio.Game;

public class KingKill extends Game{
	public int friendPlayerKingID;
	public int enemyPlayerKingID;
	void start(CollideObjectManager myObjManager, String IP, String playerName) {
		
	}
	int update() {
		int updateNeed = 0;
		Player me = this.playerList[this.playerID];
		Player enemyKing = this.playerList[enemyPlayerKingID];
		Player friendKing = this.playerList[friendPlayerKingID];
		if (enemyKing.getHP() <= 0.0)
			updateNeed = 1;
		else if (friendKing.getHP() <= 0.0)
			updateNeed = 2;
		else (me.getHP() <= 0.0)
			updateNeed = 3;
		return updateNeed;
	}

}
