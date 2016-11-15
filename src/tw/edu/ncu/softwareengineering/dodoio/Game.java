package tw.edu.ncu.softwareengineering.dodoio;

public abstract class Game {
	public int playerID;
	public Character[] players;
	abstract void start(ObjectManager myobjmanager);
	abstract int update();
}
