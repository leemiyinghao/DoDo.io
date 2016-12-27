package tw.edu.ncu.softwareengineering.dodoio.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;
import tw.edu.ncu.softwareengineering.dodoio.Game.KingKill;

public class KingKillTest {
	private KingKill kk;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		kk = new KingKill();
		kk.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 1);
	}

	@After
	public void tearDown() throws Exception {
		kk = null;
	}

	@Test
	public void testStart() {
		assertNotNull("CollideObjectManager should be created!", kk.myObjManager);
	}
	
	@Test
	public void testGetClient() {
		assertNotNull("It's not set and should be false!", kk.getClient());
	}
	
	/**************************************************************************/
	// CheckEmemyKing_isSet / notSet method in class KingKill
	@Test
	public void testCheckEnemyKing_NotSet() {
		assertFalse("It's not set and should be false!", kk.CheckEnemyKing());
	}
	
	@Test
	public void testCheckEnemyKing_IsSet() {
		int id = 10;
		Game game2 = (Game)this.kk;
		game2.setEnemyPlayerKingID(id);
		assertTrue("It's set and should be true!", kk.CheckEnemyKing());
	}
	/**************************************************************************/

	/**************************************************************************/
	// CheckFriendKing_isSet / notSet method in class KingKill
	@Test
	public void testCheckFriendKing_NotSet() {
		assertFalse("It's not set and should be false!", kk.CheckFriendKing());
	}
	
	@Test
	public void testCheckFriendKing_IsSet() {
		int id = 20;
		kk.setFriendPlayerKingID(id);
		assertTrue("It's set and should be true!", kk.CheckFriendKing());
	}
	/**************************************************************************/
	
	/**************************************************************************/
	// Get_King_ID method in class Game
	@Test
	public void testGetFriendPlayerKingID() {
		int id = 123;
		Game game2 = (Game)kk;
		game2.setFriendPlayerKingID(id);
		assertEquals("It should be the same!", id, kk.getFriendPlayerKingID());
	}

	@Test
	public void testGetEnemyPlayerKingID() {
		int id = 456;
		Game game2 = (Game)kk;
		game2.setEnemyPlayerKingID(id);
		assertEquals("It should be the same!", id, kk.getEnemyPlayerKingID());
	}
	/**************************************************************************/
	
	/**************************************************************************/
	// Set_King_ID method in class Game
	@Test
	public void testSetFriendPlayerKingID() {
		int id = 789;
		Game game2 = (Game)kk;
		game2.setFriendPlayerKingID(id);
		assertEquals("It should be the same!", id, kk.getFriendPlayerKingID());
	}

	@Test
	public void testSetEnemyPlayerKingID() {
		int id = 678;
		Game game2 = (Game)kk;
		game2.setEnemyPlayerKingID(id);
		assertEquals("It should be the same!", id, kk.getEnemyPlayerKingID());
	}
	/**************************************************************************/
	
	
	/**************************************************************************/
	// test update in KingKill
	@Test
	public void testUpdate() {
		//fail("Not yet implemented");
	}

}
