package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Fertilizer.Size;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;


public class CollideObjectManager{
	Character player;
	int recentIndex;
	Game game;
	public ArrayList<CollideObject> collideObjectList;
	public static BufferedImage[] collideObjectImages;
	
	/**
	 * this is for client(DOM)
	 * @param game the game of this client
	 */
	public CollideObjectManager(Game game){
		this.game = game;
		initializeManager();
		loadImages();
		//code: update to server
	}
	
	/**
	 * this is for CDC
	 */
	public CollideObjectManager(){
		initializeManager();
	}
	
	private void initializeManager() {
		collideObjectList = new ArrayList<CollideObject>(0);
	}
	
	void loadImages(){
		collideObjectImages = new BufferedImage[collideObjecctClass.values().length];
		Archer archer = new Archer(1, null, TeamName.deathMatch, new Position(0, 0, 0), this, collideObjecctClass.Archer.ordinal());
		Arrow arrow = new Arrow(1,  new Position(0, 0, 0), this, 1, archer);
		ArrowStrong arrowStrong = new ArrowStrong(1,  new Position(0, 0, 0), this, 1, archer);
		Magician magician = new Magician(1, null, TeamName.deathMatch, new Position(0, 0, 0), this, 1);
		MagicBall magicBall = new MagicBall(1, this, 1, magician);
		MagicBallBig magicBallBig = new MagicBallBig(1, this, 1, magician);
		SwordMan swordMan = new SwordMan(1, null, TeamName.deathMatch, new Position(0, 0, 0), this, 4);
		Slash slash = new Slash(1, this, 2, swordMan);
		SlashBig slasBig = new SlashBig(1, this, 2, swordMan);
		BufferedImage img;
		try {
			img = ImageIO.read(new FileInputStream("Resource/Archer.png"));
			collideObjectImages[collideObjecctClass.Archer.ordinal()] = scaleImage(img, archer.getRadius()*2, archer.getRadius()*2);
			img = ImageIO.read(new FileInputStream("Resource/Arrow.png"));
			collideObjectImages[collideObjecctClass.Arrow.ordinal()] = scaleImage(img, arrow.width, arrow.height);
			img = ImageIO.read(new FileInputStream("Resource/ArrowStrong.png"));
			collideObjectImages[collideObjecctClass.ArrowStrong.ordinal()] = scaleImage(img, arrowStrong.width, arrowStrong.height);
			img = ImageIO.read(new FileInputStream("Resource/Fertilizer.png"));
			Fertilizer FBig = new Fertilizer(1, new Position(0, 0, 0), this, collideObjecctClass.FertilizerBig.ordinal(), Size.large);
			collideObjectImages[collideObjecctClass.FertilizerBig.ordinal()] = scaleImage(img, FBig.radius*2, FBig.radius*2);
			Fertilizer FMed = new Fertilizer(1, new Position(0, 0, 0), this, collideObjecctClass.FertilizerMedium.ordinal(), Size.medium);
			collideObjectImages[collideObjecctClass.FertilizerMedium.ordinal()] = scaleImage(img, FMed.radius*2, FMed.radius*2);
			Fertilizer FSma = new Fertilizer(1, new Position(0, 0, 0), this, collideObjecctClass.FertilizerBig.ordinal(), Size.small);
			collideObjectImages[collideObjecctClass.FertilizerSmall.ordinal()] = scaleImage(img, FSma.radius*2, FSma.radius*2);
			img = ImageIO.read(new FileInputStream("Resource/MagicBall.png"));
			collideObjectImages[collideObjecctClass.MagicBall.ordinal()] = scaleImage(img, magicBall.getRadius()*2, magicBall.getRadius()*2);
			img = ImageIO.read(new FileInputStream("Resource/MagicBallBig.png"));
			collideObjectImages[collideObjecctClass.MagicBallBig.ordinal()] = scaleImage(img, magicBallBig.getRadius()*2, magicBallBig.getRadius()*2);
			img = ImageIO.read(new FileInputStream("Resource/Magician.png"));
			collideObjectImages[collideObjecctClass.Magician.ordinal()] = scaleImage(img, magician.getRadius()*2, magician.getRadius()*2);
			
			collideObjectImages[collideObjecctClass.Obstacle.ordinal()] = ImageIO.read(new FileInputStream("Resource/Obstacle.png"));
			
			img = ImageIO.read(new FileInputStream("Resource/Slash.png"));
			collideObjectImages[collideObjecctClass.Slash.ordinal()] = scaleImage(img, slash.width, slash.height);
			img = ImageIO.read(new FileInputStream("Resource/SlashBig.png"));
			collideObjectImages[collideObjecctClass.SlashBig.ordinal()] = scaleImage(img, slasBig.width, slasBig.height);
			img = ImageIO.read(new FileInputStream("Resource/SwordMan.png"));
			collideObjectImages[collideObjecctClass.SwordMan.ordinal()] = scaleImage(img, swordMan.getRadius()*2, swordMan.getRadius()*2);
			img = ImageIO.read(new FileInputStream("Resource/Wanderer.png"));
			Wanderer wanderer = new Wanderer(1, new Position(0, 0, 0), this, 5);
			collideObjectImages[collideObjecctClass.Wanderer.ordinal()] = scaleImage(img, wanderer.radius*2, wanderer.radius*2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    protected BufferedImage scaleImage(java.awt.image.BufferedImage appearanece, int w, int h) {
    	// creates output image
        BufferedImage outputImage = new BufferedImage(w,
                h, appearanece.getType());
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(appearanece, 0, 0, 25, 25, null);
        g2d.dispose();
    	return outputImage;
    }
	
	/**
	 * add character to list
	 * @param className
	 * @param inputID
	 * @param setPosition
	 * @param setName
	 * @param setTeam
	 * @throws Exception No such kind of collideObject. / client try to add collide object.
	 */
	public void addCharacter(collideObjecctClass className, int inputID, Position setPosition,
			String setName, TeamName setTeam) throws Exception {
		if(game != null)
			throw new Exception("client try to add collide object");
		
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
	 * @throws Exception No such kind of collideObject. / client try to add collide object.
	 */
	public void addAttackObject(collideObjecctClass className, int inputID, 
			Position setPosition, Character setPlayer) throws Exception {
		if(game != null)
			throw new Exception("client try to add collide object");
		
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
			toAddObject = new MagicBall(inputID, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.MagicBallBig) {
			toAddObject = new MagicBallBig(inputID, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.Slash) {
			toAddObject = new Slash(inputID, this, className.ordinal(), setPlayer);
			collideObjectList.add(toAddObject);
		}
		else if(className == collideObjecctClass.SlashBig) {
			toAddObject = new SlashBig(inputID, this, className.ordinal(), setPlayer);
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
	 * @throws Exception No such kind of collideObject. / client try to add collide object.
	 */
	public void addObsatcle(collideObjecctClass className, int inputID, 
			Position setPosition, int width, int height, boolean setDestroyable) throws Exception {
		if(game != null)
			throw new Exception("client try to add collide object");
		
		CollideObject toAddObject;
		
		if(className == collideObjecctClass.Obstacle) {
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
	 * @throws Exception No such kind of collideObject. / client try to add collide object.
	 */
	public void addFertilizer(collideObjecctClass className, int inputID, 
			Position setPosition, Size setSize) throws Exception {
		if(game != null)
			throw new Exception("client try to add collide object");
		
		CollideObject toAddObject;
		if(className == collideObjecctClass.FertilizerBig || 
				className == collideObjecctClass.FertilizerMedium || 
				className == collideObjecctClass.FertilizerSmall) {
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
	 * @throws Exception No such kind of collideObject. / client try to add collide object.
	 */
	public void addWanderer(collideObjecctClass className, int inputID, 
			Position setPosition) throws Exception {
		if(game != null)
			throw new Exception("client try to add collide object");
		
		CollideObject toAddObject;
		if(className == collideObjecctClass.Wanderer) {
			toAddObject = new Wanderer(inputID, setPosition, this, className.ordinal());
			collideObjectList.add(toAddObject);
		}
		else
			throw new Exception("No such kind of collideObject.");
		
		toAddObject = null;
	}
	
	/**
	 * set some one as a king in kingKill mode
	 * @param king
	 */
	public void setKing(Character king) {
		king.addExp(king.expAccumulationTable[king.expAccumulationTable.length-1]);
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
	public void setMainPlayer(CollideObject setPlayer) throws Exception{
		if(player != null)
			throw new Exception("Main player already exist.");
		if(game == null)
			throw new Exception("game have not set.");
		player = (Character) setPlayer;
		player.setClient(game);
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
		FertilizerBig,
		FertilizerMedium,
		FertilizerSmall,
		MagicBall,
		MagicBallBig,
		Magician,
		Obstacle,
		Slash,
		SlashBig,
		SwordMan,
		Wanderer
	}
	
}
