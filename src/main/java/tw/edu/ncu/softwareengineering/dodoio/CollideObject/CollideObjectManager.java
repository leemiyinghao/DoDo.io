package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.Image;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Fertilizer.Size;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;


public class CollideObjectManager{
	Character player;
	int recentIndex;
	Game game;
	boolean playerAttackActive = true, playerSkillActive = true;// initial at constructor if needed
	public ArrayList<CollideObject> collideObjectList;
	public Image[] collideObjectImages;
	
	/**
	 * this is for client(DOM)
	 * @param game the game of this client
	 */
	public CollideObjectManager(Game game) {
		this.game = game;
		//code: update to server
	}
	
	/**
	 * this is for CDC
	 */
	public CollideObjectManager() {
		
	}
	
	/**
	 * add character to list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @param setName
	 * @param setTeam
	 * @throws Exception No such kind of collideObject.
	 */
	public void addCharacter(collideObjecctClass className, int inputID, Position setPosition,
			String setName, String setTeam) throws Exception {
		CollideObject toAddObject;
		if(className == collideObjecctClass.Archer) {
			toAddObject = new Archer(inputID, setName, setTeam, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.Magician) {
			toAddObject = new Magician(inputID, setName, setTeam, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.SwordMan) {
			toAddObject = new SwordMan(inputID, setName, setTeam, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**
	 * add attack object into list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @param setPlayer
	 * @throws Exception No such kind of collideObject.
	 */
	public void addAttackObject(collideObjecctClass className, int inputID, 
			Position setPosition, Character setPlayer) throws Exception {
		CollideObject toAddObject;
		if(className == collideObjecctClass.Arrow) {
			toAddObject = new Arrow(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.ArrowStrong) {
			toAddObject = new ArrowStrong(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.MagicBall) {
			toAddObject = new MagicBall(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.MagicBallBig) {
			toAddObject = new MagicBallBig(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.Slash) {
			toAddObject = new Slash(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.SlashBig) {
			toAddObject = new SlashBig(inputID, setPosition, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**
	 * add obstacle into list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @param width
	 * @param height
	 * @param setDestroyable
	 * @throws Exception No such kind of collideObject.
	 */
	public void addObsatcle(collideObjecctClass className, int inputID, 
			Position setPosition, int width, int height, boolean setDestroyable) throws Exception {
		CollideObject toAddObject;
		
		if(className == collideObjecctClass.Obsatcle) {
			toAddObject = new Obstacle(inputID, setPosition, this, className.ordinal(), width, height, setDestroyable);
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**
	 * add fertilizer into list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @param setSize
	 * @throws Exception No such kind of collideObject.
	 */
	public void addFertilizer(collideObjecctClass className, int inputID, 
			Position setPosition, Size setSize) throws Exception {
		CollideObject toAddObject;
		if(className == collideObjecctClass.Fertilizer) {
			toAddObject = new Fertilizer(inputID, setPosition, this, className.ordinal(), setSize);
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**
	 * add chaser or wanderer into list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @throws Exception No such kind of collideObject.
	 */
	public void addNotFertilizerMob(collideObjecctClass className, int inputID, 
			Position setPosition) throws Exception {
		CollideObject toAddObject;
		if(className == collideObjecctClass.Chaser) {
			toAddObject = new Chaser(inputID, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.Wanderer) {
			toAddObject = new Wanderer(inputID, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**set your player
	 * 
	 * Exception : Main player already exist.
	 * 
	 * @param chracterClass Archer, SwordMan, or Magician. Input which your player is.
	 * @param ID
	 * @param name player's name
	 * @param team check the enum "TeamName" in Character 
	 * @param image
	 * @param position
	 * @throws Exception 
	 */
	public void setMainPlayer(Character setPlayer) throws Exception{
		if(player != null)
			throw new Exception("Main player already exist.");
		player = setPlayer;
	}
	
	/**return the collideObject queried by ID from collideObjectList
	 * 
	 * @param inputID
	 * @return
	 * @throws Exception No collide object with inputID in collideObjectList
	 */
	public CollideObject queryObjectByID(int inputID) throws Exception{
		CollideObject toFindObject = null;
		int counter = 0;
		do {
			if(inputID == collideObjectList.get(counter).ID) {
				toFindObject = collideObjectList.get(counter);
				break;
			}
			counter++;
		}while(collideObjectList.size() > counter);
		
		if(collideObjectList.size()==counter) {
			throw new Exception("Exception: No collide object with ID("+inputID+") in collideObjectList");
		}
		
		return toFindObject;
	}
	
	/**get the main player in collideObjectList
	 * 
	 * @return
	 * @throws Exception There is no main player!
	 */
	public Character getMyPlayer() throws Exception {
		if(player == null)
			throw new Exception("There is no main player!");
		
		return player;
	}
	
	/**
	 * update all the collide object in the list for convenience
	 */
	public void updateAllCollideObjects() {
		for(int i=0; i<collideObjectList.size(); i++) {
			collideObjectList.get(i).update();
		}
	}
	
	public static enum collideObjecctClass {
		Archer,
		Arrow,
		ArrowStrong,
		Chaser,
		Fertilizer,
		MagicBall,
		MagicBallBig,
		Magician,
		Obsatcle,
		Slash,
		SlashBig,
		SwordMan,
		Wanderer
	}
	
}
