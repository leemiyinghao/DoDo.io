package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class KingKill extends Game{
	private int friendPlayerKingID;
	private int enemyPlayerKingID;
	void start(String playerName, CollideObjectManager.ChracterClass chracterclass, int gameMode){
		//need to get friend and enemy King ID
		this.myObjManager = new CollideObjectManager(this);
		this.client = new Client(this, playerName, chracterclass, gameMode);
	}
	int update() {
		/*return 0 for nothing happened (game continue)!
		 *return 1 if the enemy boss is dead! -> �ͤ�Ĺ, �C������
		 *return 2 if the friend boss is dead! -> �Ĥ�Ĺ, �C������
		 *return 3 if the player is dead! -> player GG, �^���W�r�a��
		 */
		int updateNeed = 0;
		/*Player me = this.playerList[this.playerID];
		Player enemyKing = this.playerList[enemyPlayerKingID];
		Player friendKing = this.playerList[friendPlayerKingID];
		if (enemyKing.getHP() <= 0.0)
			updateNeed = 1;
		else if (friendKing.getHP() <= 0.0)
			updateNeed = 2;
		else (me.getHP() <= 0.0)
			updateNeed = 3;*/
		return updateNeed;
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
}
