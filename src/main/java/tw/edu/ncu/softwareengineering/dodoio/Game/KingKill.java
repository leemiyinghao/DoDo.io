package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class KingKill extends Game{
	private int friendPlayerKingID;
	private int enemyPlayerKingID;
	void start(CollideObjectManager myObjManager, String playerName){
		//int myid = 0; //must be changed!
		//this.setPlayerID(myid);
	}
	int update() {
		/*return 0 for nothing happened (game continue)!
		 *return 1 if the enemy boss is dead! -> 友方贏, 遊戲結束
		 *return 2 if the friend boss is dead! -> 敵方贏, 遊戲結束
		 *return 3 if the player is dead! -> player GG, 回到選名字地方
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
	public void setFriendPlayerKingID(int friendPlayerKingID) {
		this.friendPlayerKingID = friendPlayerKingID;
	}
	public int getEnemyPlayerKingID() {
		return enemyPlayerKingID;
	}
	public void setEnemyPlayerKingID(int enemyPlayerKingID) {
		this.enemyPlayerKingID = enemyPlayerKingID;
	}

}
