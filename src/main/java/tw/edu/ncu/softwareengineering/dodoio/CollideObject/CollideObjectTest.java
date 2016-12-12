package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;

public class CollideObjectTest {
	CollideObjectManager manager;
	Archer playerTest;
	SwordMan attackerTest;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = new CollideObjectManager();
		playerTest = new Archer(1, "archerX", Character.TeamName.deathMatch, new Position(0, 0, 0), 
				manager, CollideObjectManager.collideObjecctClass.Archer.ordinal());
		attackerTest = new SwordMan(2, "attackerX", Character.TeamName.deathMatch
				, new Position(1, 1, 0), manager, 
				CollideObjectManager.collideObjecctClass.SwordMan.ordinal());
	}

	@After
	public void tearDown() throws Exception {
		playerTest = null;
		attackerTest = null;
		manager = null;
	}

	@Test
	public void testOnCollide() {
		playerTest.onCollide(null);
		assertEquals("after collide it should be harm", 200 - playerTest.collideDamage, playerTest.getHP());
	}

	@Test
	public void testInvincible() {
		playerTest.isInvincible = true;
		assertEquals("now it is invictcible", true, playerTest.isInvincible());
		playerTest.onCollide(null);
		assertEquals("after collide it can't be harm", 200, playerTest.getHP());
		playerTest.beAttacked(attackerTest);
		assertEquals("after attacked it can't be harm", 200, playerTest.getHP());
	}
	
	public void testAttackToDead() {
		attackerTest.damagePoint = 10000;
		playerTest.beAttacked(attackerTest);
		assertEquals("archerX will die", true, playerTest.isDead());
	}
	
	public void testGetHP() {
		int[] xTest = {670, 2, 6, 77, 100000, 6849521, 243, 6, 50};
		for(int i=0; i<xTest.length; i++) {
			playerTest.healthPoint = xTest[i];
			assertEquals("assign hp and test getHP()", xTest[i], playerTest.getHP());
		}
	}
	
}
