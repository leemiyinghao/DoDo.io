package tw.edu.ncu.softwareengineering.dodoio;

public abstract class Game {
	public int playerID;
	public Character[] players;
	public abstract void start(ObjectManager myobjmanager);
	public abstract int update();
}
