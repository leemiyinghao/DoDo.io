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
	private DeathMatch dm;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dm = new DeathMatch();
		dm.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 0);
	}

	@After
	public void tearDown() throws Exception {
		dm = null;
	}

	@Test
	public void testStart() {
		assertNotNull("ObjectMangager should be created!", dm.myObjManager);
	}
	
	@Test
	public void testGetClient() {
		assertNotNull("Client should be created and available!", dm.getClient());
	}
	/**************************************************************************/
	

	/**************************************************************************/
	// test update in DeathMatch
	@Test
	public void testUpdate_forContinue() {
		System.out.println(dm.myObjManager.collideObjectList);
	}
	
	@Test
	public void testUpdate_forPlayerDead() {
		
		
	}
	
	@Test
	public void testUpdate_forPlayerWin() {
		
		
	}
	
	

}
