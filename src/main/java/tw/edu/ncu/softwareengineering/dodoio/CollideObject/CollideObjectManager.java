package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class CollideObjectManager{
	Character player;
	int recentIndex;
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
	public CollideObjectManager() {
		//code: update to server
	}

	public void addMainplayer(String chracterClass,int ID, String name, String team, BufferedImage image, Position position){
		if(chracterClass.equals("SwordMan")){
			player = new SwordMan(ID, name, team, image, position);
		}
		else if(chracterClass.equals("Archer")){
			player = new Archer(ID, name, team, image, position);
		}
		else if(chracterClass.equals("Magician")){
			player = new Magician(ID, name, team, image, position);
		}
		collideObjectList.add(player);
		recentIndex = collideObjectList.indexOf(player);
	}
	/**return the instance from collideObjectList
	 * 
	 * exception: No collide object with ID(inputID) in collideObjectList
	 * It will just print out this message, and you may get a null reference
	 * 
	 * @param inputID
	 * @return
	 */
	public int queryObjectByID(int inputID){
		int toFindObjectIndex = 0;
		int counter = 0;
		try {
			do {
				if(inputID == collideObjectList.get(counter).ID) {
					toFindObjectIndex = counter;
					break;
				}
				counter++;
			}while(collideObjectList.size() > counter);
			
			if(collideObjectList.size()==counter)
				throw new Exception();
		}
		catch(Exception exception) {
			System.out.println("Exception: No collide object with ID("+inputID+") in collideObjectList");
			return -1;
		}
		
		return toFindObjectIndex;
	}
	
	public Character getMyPlayer() {
		return player;
	}
	
	/**Use this when player attack an enemy player
	 * 
	 * @param playerAttack
	 * @param playerAttacked
	 */
	private void playerBeAttacked(Character playerAttack, Character playerAttacked) {
		
	}
	/**Use this when player attack an obstacle
	 * 
	 * @param playerAttack
	 * @param obstacleAttacked
	 */
	private void obstacleBeAttacked(Character playerAttack, Obstacle obstacleAttacked){
		
	}
	/**Use this when player attack mob
	 * 
	 * @param playerAttack
	 * @param mobAttacked
	 */
	private void mobBeAttacked(Character playerAttack, Mob mobAttacked) {
		
	}
	
	
}
