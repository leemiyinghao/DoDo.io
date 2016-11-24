package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class CollideObjectManager {
	final int playerID;
	Character player;
	public ArrayList<CollideObject> collideObjectList;
	
	/**When this CollideObjectManager be create, initialize the user's character at the same time
	 * 
	 * @param ID
	 * @param profession
	 * @param name
	 * @param team
	 * @param image
	 * @param position
	 */
	public CollideObjectManager(int ID, String profession, String name, String team, BufferedImage image,Position position ) {
		playerID = ID;
		player = new Character(ID, name, team, image, position);
		collideObjectList.add(player);
		//code: update to server
	}
	
	/**it will return the instance of the object you want from object
	 * 
	 * exception: No collide object with ID(inputID) in collideObjectList
	 * It will just print out this message, and you may get a null reference
	 * 
	 * @param inputID
	 * @return
	 */
	public CollideObject queryObjectByID(int inputID){
		CollideObject toFindObject = collideObjectList.get(0);
		int counter = 0;
		try {
			do {
				if(inputID == collideObjectList.get(counter).ID) {
					toFindObject = collideObjectList.get(counter);
					break;
				}
				counter++;
			}while(collideObjectList.size() > counter);
			
			if(collideObjectList.size()==counter)
				throw new Exception();
		}
		catch(Exception exception) {
			System.out.println("No collide object with ID("+inputID+") in collideObjectList");
		}
		
		return toFindObject;
	}
	
	/**Use this when player attack an enemy player
	 * 
	 * @param playerAttack
	 * @param playerAttacked
	 */
	public void playerBeAttacked(Character playerAttack, Character playerAttacked) {
		
	}
	/**Use this when player attack an obstacle
	 * 
	 * @param playerAttack
	 * @param obstacleAttacked
	 */
	public void obstacleBeAttacked(Character playerAttack, Obstacle obstacleAttacked){
		
	}
	/**Use this when player attack mob
	 * 
	 * @param playerAttack
	 * @param mobAttacked
	 */
	public void mobBeAttacked(Character playerAttack, Mob mobAttacked) {
		
	}
	
	
}
