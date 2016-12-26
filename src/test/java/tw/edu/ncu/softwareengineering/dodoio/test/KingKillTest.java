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
	private KingKill game;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new KingKill();
		
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 1);
		
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void testStart() {
		assertNotNull("CollideObjectManager should be created!", game.myObjManager);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFriendPlayerKingID() {
		int id = 123;
		Game game2 = (Game)game;
		game2.setFriendPlayerKingID(id);
		assertEquals("It should be the same!", id, game2.getFriendPlayerKingID());
	}

	@Test
	public void testGetEnemyPlayerKingID() {
		int id = 456;
		Game game2 = (Game)game;
		game2.setEnemyPlayerKingID(id);
		assertEquals("It should be the same!", id, game2.getEnemyPlayerKingID());
	}

	@Test
	public void testSetFriendPlayerKingID() {
		int id = 789;
		Game game2 = (Game)game;
		game2.setFriendPlayerKingID(id);
		assertEquals("It should be the same!", id, game2.getFriendPlayerKingID());
	}

	@Test
	public void testSetEnemyPlayerKingID() {
		int id = 678;
		Game game2 = (Game)game;
		game2.setEnemyPlayerKingID(id);
		assertEquals("It should be the same!", id, game2.getEnemyPlayerKingID());
	}

	@Test
	public void testCheckEnemyKing_NotSet() {
		assertFalse("If not set, it should be -1!", game.CheckEnemyKing());
	}
	
	@Test
	public void testCheckEnemyKing_IsSet() {
		int id = 10;
		game.setEnemyPlayerKingID(id);
		assertTrue("It's set and should be true!", game.CheckEnemyKing());
	}

	@Test
	public void testCheckFriendKing_NotSet() {
		assertFalse("If not set, it should be false!", game.CheckFriendKing());
	}
	
	@Test
	public void testCheckFriendKing_IsSet() {
		
		assertTrue("It's set and should be true!", game.CheckFriendKing());
	}

}
