package tw.edu.ncu.softwareengineering.dodoio.Game;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public abstract class Game {
	public CollideObjectManager myObjManager;
	Client client;
	public Client getClient() {
		return this.client;
	}
	abstract void start(String playerName, CollideObjectManager.ChracterClass chracterclass, int gameMode);
	abstract int update();
	
}
