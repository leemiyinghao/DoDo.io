package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollideObjectTest {
	CollideObjectManager manager;
	Archer playerTest;
	SwordMan attackerTest;
	Magician attackerTest2;
	int miliSecond = 1000;
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
		attackerTest2 = new Magician(3, "MagicianX", Character.TeamName.deathMatch, new Position(18, 50, 0.441),
				manager, CollideObjectManager.collideObjecctClass.Magician.ordinal());
	}

	@After
	public void tearDown() throws Exception {
		playerTest = null;
		attackerTest = null;
		attackerTest2 = null;
		manager = null;
	}

	@Test
	public void onCollideTest() {
		playerTest.beHarmed(playerTest.collideDamage);
		assertEquals("after collide it should be harm", 200 - playerTest.collideDamage, playerTest.getHP());
	}

	@Test
	public void invincibleTest() {
		playerTest.isInvincible = true;
		assertEquals("now it is invictcible", true, playerTest.isInvincible());
		playerTest.beHarmed(playerTest.collideDamage);
		assertEquals("after collide it can't be harm", 200, playerTest.getHP());
		playerTest.beAttacked(attackerTest);
		assertEquals("after attacked it can't be harm", 200, playerTest.getHP());
	}
	
	@Test
	public void attackToDeadTest() {
		attackerTest.damagePoint = 10000;
		playerTest.beAttacked(attackerTest);
		assertEquals("archerX will die", true, playerTest.isDead());
	}
	
	@Test
	public void getHPTest() {
		int[] xTest = {670, 2, 6, 77, 100000, 6849521, 243, 6, 50};
		for(int i=0; i<xTest.length; i++) {
			playerTest.healthPoint = xTest[i];
			assertEquals("assign hp and test getHP()", xTest[i], playerTest.getHP());
		}
	}
	
	@Test
	public void getPositionTest() {
		Position setPosition = new Position(3, 80, 0.556);
		playerTest.position = setPosition;
		assertEquals("player position move to 3,80,0.566", setPosition, playerTest.getPosition());
		setPosition = null;
	}
	
	@Test
	public void isDeadTest() {
		assertFalse("player still alive", playerTest.isDead());
		playerTest.dead();
		assertTrue("player die", playerTest.isDead());
	}

}
