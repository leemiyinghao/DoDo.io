package tw.edu.ncu.softwareengineering.dodoio.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Game.DeathMatch;

public class DeathMatchTest {
	private DeathMatch game;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new DeathMatch();
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void testStart() {
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 0);
		assertNotNull("ObjectMangager should be created!", game.myObjManager);
	}

	@Test
	public void testUpdate_forContinue() {
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 0);
		
		
	}
	
	@Test
	public void testUpdate_forPlayerDead() {
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 0);
		
		
	}
	
	@Test
	public void testUpdate_forPlayerWin() {
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 0);
		
		
	}
	
	@Test
	public void testGetClient() {
		assertNotNull("Client should be created!", game.getClient());
	}

}
