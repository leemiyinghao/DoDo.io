package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.ChracterClass;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;


public class CollideObjectManager{
	Character player;
	int recentIndex;
	Game game;
	boolean playerAttackActive = true, playerSkillActive = true;// initial at constructor if needed
	public ArrayList<CollideObject> collideObjectList;
	public Image[] collideObjectImages;
	
	public CollideObjectManager(Game game) {
		//code: update to server
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
	/**return the index from collideObjectList
	 * 
	 * exception: No collide object with ID(inputID) in collideObjectList.
	 * 
	 * @param inputID
	 * @return
	 * @throws Exception No collide object with inputID in collideObjectList
	 */
	public int queryObjectByID(int inputID) throws Exception{
		int toFindObjectIndex = 0;
		int counter = 0;
		do {
			if(inputID == collideObjectList.get(counter).ID) {
				toFindObjectIndex = counter;
				break;
			}
			counter++;
		}while(collideObjectList.size() > counter);
		
		if(collideObjectList.size()==counter)
			throw new Exception("Exception: No collide object with ID("+inputID+") in collideObjectList");
		
		return toFindObjectIndex;
	}
	
	/**get the index of main player in collideObjectList
	 * 
	 * Exception: There is no main player.
	 * 
	 * @return
	 * @throws Exception There is no main player!
	 */
	public int getMyPlayer() throws Exception {
		if(player == null)
			throw new Exception("There is no main player!");
		
		if(!(collideObjectList.get(recentIndex).ID == player.ID))
			recentIndex = queryObjectByID(player.ID);
		player = (Character) collideObjectList.get(recentIndex);
		
		return recentIndex;
	}
	
	/**
	 * update main player recovery, attackCD, skillCD ... by time offset
	 * oldtime 
	 */
	
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
