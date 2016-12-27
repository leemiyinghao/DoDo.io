package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.Game.DeathMatch;

public class CollideObjectManagerTest {
	CollideObjectManager manager;
	CollideObjectManager managerClient;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = new CollideObjectManager();
		managerClient = new CollideObjectManager(new DeathMatch());
	}

	@After
	public void tearDown() throws Exception {
		manager = null;
		managerClient = null;
	}

	@Test
	public void testAddCharacter() throws Exception {
		manager.addCharacter(collideObjecctClass.SwordMan, 1, new Position(0, 0, 0), "swordManX", TeamName.teamBlue);
		assertEquals("list should add 1 sword man", 1, manager.collideObjectList.size());
	}

	@Test
	public void testAddAttackObject() throws Exception {
		manager.addCharacter(collideObjecctClass.SwordMan, 1, new Position(0, 0, 0), "swordManX", TeamName.teamBlue);
		SwordMan toAddObjectsPlayer = (SwordMan) manager.collideObjectList.get(0);
		manager.addAttackObject(collideObjecctClass.SlashBig, 35, 
				manager.collideObjectList.get(0).position, (Character) toAddObjectsPlayer);
	}

//	@Test
//	public void testSetMainPlayer() throws Exception {
//		manager.addCharacter(collideObjecctClass.SwordMan, 1, new Position(0, 0, 0), "swordManX", TeamName.teamBlue);
//		SwordMan toAddObjectsPlayer = (SwordMan) manager.collideObjectList.get(0);
//		manager.addAttackObject(collideObjecctClass.SlashBig, 35, 
//				manager.collideObjectList.get(0).position, (Character) toAddObjectsPlayer);
//		manager.addCharacter(collideObjecctClass.Magician, 2, new Position(1, 2, 0), "magicianX", TeamName.teamBlue);
//		manager.setMainPlayer((Character) manager.collideObjectList.get(2));
//		assertNotNull("Main player added", manager.player);
//	}

	@Test(expected=Exception.class)
	public void testQueryObjectByID() throws Exception {
		manager.addCharacter(collideObjecctClass.SwordMan, 1, new Position(0, 0, 0), "swordManX", TeamName.teamBlue);
		SwordMan toAddObjectsPlayer = (SwordMan) manager.collideObjectList.get(0);
		manager.addAttackObject(collideObjecctClass.SlashBig, 35, 
				manager.collideObjectList.get(0).position, (Character) toAddObjectsPlayer);
		manager.addCharacter(collideObjecctClass.Magician, 2, new Position(1, 2, 0), "magicianX", TeamName.teamBlue);
		manager.setMainPlayer((Character) manager.collideObjectList.get(2));
		
		assertEquals("query id 1, sword man",manager.collideObjectList.get(0), manager.queryObjectByID(1));
		manager.queryObjectByID(22);
	}
	
	@Test
	public void testSetKing() throws Exception {
		manager.addCharacter(collideObjecctClass.Magician, 19,  new Position(14, 2, 0), "magicianXX", TeamName.teamBlue);
		Character king = (Character) manager.queryObjectByID(19);
		manager.setKing(king);
		assertEquals("sword man become king", king.levelMax, king.level);
	}

	@Test(expected=Exception.class)
	public void testGetMyPlayer() throws Exception {
		managerClient.addCharacter(collideObjecctClass.SwordMan, 1, new Position(0, 0, 0), "swordManX", TeamName.teamBlue);
		SwordMan toAddObjectsPlayer = (SwordMan) managerClient.collideObjectList.get(0);
		managerClient.addAttackObject(collideObjecctClass.SlashBig, 35, 
				managerClient.collideObjectList.get(0).position, (Character) toAddObjectsPlayer);
		managerClient.addCharacter(collideObjecctClass.Magician, 2, new Position(1, 2, 0), "magicianX", TeamName.teamBlue);
		managerClient.setMainPlayer((Character) managerClient.collideObjectList.get(2));
		
		assertEquals(" get my player", managerClient.player, managerClient.getMyPlayer());
		managerClient.player = null;
		managerClient.getMyPlayer();
	}

}
