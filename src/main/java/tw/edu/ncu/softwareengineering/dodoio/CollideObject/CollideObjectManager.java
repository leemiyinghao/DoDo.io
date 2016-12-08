package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.ChracterClass;


public class CollideObjectManager{
	Character player;
	int recentIndex;
	boolean playerAttackActive = true, playerSkillActive = true;
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
		if(chracterClass.equals(ChracterClass.SwordMan)){
			player = new SwordMan(ID, name, team, image, position);
		}
		else if(chracterClass.equals(ChracterClass.Archer)){
			player = new Archer(ID, name, team, image, position);
		}
		else if(chracterClass.equals(ChracterClass.Magician)){
			player = new Magician(ID, name, team, image, position);
		}
		collideObjectList.add(player);
		recentIndex = collideObjectList.indexOf(player);
	}
	/**return the index from collideObjectList
	 * 
	 * exception: No collide object with ID(inputID) in collideObjectList, return -1
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
	
	/**get the index of main player in collideObjectList
	 * 
	 * Exception: There is no main player, return -1
	 * 
	 * @return
	 */
	public int getMyPlayer() {
		try{
			if(player == null)
				throw new Exception();
			
			if(!(collideObjectList.get(recentIndex).ID == player.ID))
				recentIndex = queryObjectByID(player.ID);
			player = (Character) collideObjectList.get(recentIndex);
		}
		catch(Exception exception) {
			System.out.println("There is no main player!");
			return -1;
		}
		
		return recentIndex;
	}
	
	public void myPlayerAttack() {
		try{
			if(getMyPlayer() == -1)
				throw new Exception();
		}
		catch(Exception exception) {
			System.out.println("There is no main player!");
			return;
		}
		
		Thread attackThread = new Thread(new Runnable(){

			@Override
			public void run() {
				if(playerAttackActive) {
					playerAttackActive = false;
					// get ID for attackObject from server
					AttackObject attackObject = ((Character) collideObjectList.get(getMyPlayer())).attack(client.getNewID());
					collideObjectList.add(attackObject);
					
					Thread attackObjectThread = new Thread((Runnable) collideObjectList.get(collideObjectList.indexOf(attackObject)));
					attackObjectThread.start();
					try {
						Thread.sleep((long) (((Character) collideObjectList.get(getMyPlayer())).attackCD*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					playerAttackActive = true;
				}
			}
		});
		attackThread.start();
		
	}
	
	public void myPlayerSkill() {
		try{
			if(getMyPlayer() == -1)
				throw new Exception();
		}
		catch(Exception exception) {
			System.out.println("There is no main player!");
			return;
		}
		
		Thread skillThread = new Thread(new Runnable(){

			@Override
			public void run() {
				if(playerSkillActive) {
					
					try {
							playerSkillActive = false;
						// get ID for attackObject from server
						AttackObject attackObject = ((Character) collideObjectList.get(getMyPlayer())).skill(client.getNewID());
						collideObjectList.add(attackObject);
						
						Thread attackObjectThread = new Thread((Runnable) collideObjectList.get(collideObjectList.indexOf(attackObject)));
						attackObjectThread.start();
						
						Thread.sleep((long) (((Character) collideObjectList.get(getMyPlayer())).skillCD*1000));

						playerSkillActive = true;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		skillThread.start();
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
	private void obstacleBeAttacked(Character playerAttack, Obstacle obstacleAttacked) {
		
	}
	/**Use this when player attack mob
	 * 
	 * @param playerAttack
	 * @param mobAttacked
	 */
	private void mobBeAttacked(Character playerAttack, Mob mobAttacked) {
		
	}
	
	
}
