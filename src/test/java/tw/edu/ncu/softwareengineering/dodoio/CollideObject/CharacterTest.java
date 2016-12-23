package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.Internet.Client;

public class CharacterTest extends CollideObjectTest{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void recoveryTest() {
		playerTest.update();
		
		assertEquals("now the player hp is full.", playerTest.maxHP, playerTest.getHP());

		playerTest.recoveryPointBuffer = 0;
		playerTest.beHarmed(50);
		int hpAfterHarm = playerTest.getHP();
		int hpToRecover = (int) (10/playerTest.recoveryCD);
		playerTest.recover(10*1000);
		assertEquals("Let player be harm now wait for 10s", hpAfterHarm+hpToRecover, playerTest.getHP());
		
		playerTest.recoveryPointBuffer = 0;
		long updateTime1 = (long) (1.3*1000);
		long updateTime2 = (long) (0.7*1000);
		playerTest.beHarmed(80);
		hpAfterHarm = playerTest.getHP();
		hpToRecover = (int) ((updateTime1+updateTime2)/(playerTest.recoveryCD*1000));
		playerTest.recover(updateTime1);
		playerTest.recover(updateTime2);
		assertEquals("use buffer to store the time left(less than recovery CD) last update", hpAfterHarm+hpToRecover, playerTest.getHP());

		playerTest.recoveryPointBuffer = 0;
		hpAfterHarm = playerTest.getHP();
		hpToRecover = (int) (1000000/playerTest.recoveryCD);
		playerTest.recover(1000000*1000);
		assertEquals("player hp will full", playerTest.maxHP, playerTest.getHP());
		
	}
	
	@Test
	public void countAttackCDTest() {
		//after attack()
		playerTest.attackActive = false;
		
		playerTest.countAttackCD((long) (miliSecond*playerTest.attackCD));
		assertTrue("player can attack again", playerTest.attackActive);
		
		playerTest.attack();
		playerTest.countAttackCD((long) (miliSecond*playerTest.attackCD+1200000));
		assertTrue("player can attack again", playerTest.attackActive);

		playerTest.attack();
		playerTest.countAttackCD(20);
		assertFalse("player can't attack now", playerTest.attackActive);
	}

	@Test
	public void countSkillCDTest() {
		//after attack()
		playerTest.skillActive = false;
		
		playerTest.countSkillCD((long) (miliSecond*playerTest.skillCD));
		assertTrue("player can use skill again", playerTest.skillActive);
		
		playerTest.skill();
		playerTest.countSkillCD((long) (miliSecond*playerTest.skillCD+1200000));
		assertTrue("player can use skill again", playerTest.skillActive);

		playerTest.skill();
		playerTest.countSkillCD(20);
		assertFalse("player can't use skill now", playerTest.skillActive);
	}
	
	@Test
	public void addExpCDTest() {
		for(int i=0; i<playerTest.levelMax-1; i++) {
			playerTest.addExp(playerTest.expTable[i]);
			assertEquals("now player is level "+ (i+1)+".", (i+1), playerTest.level);
		}
		for(int i=1; i<playerTest.levelMax-1; i++) {
			playerTest.exp = 0;
			playerTest.level = 1;
			playerTest.addExp(playerTest.expAccumulationTable[i]);
			assertEquals("player become level "+ (i+1)+" immediatly.", (i+1), playerTest.level);
		}
	}
	
	@Test(expected = Exception.class)
	public void upgradeHPTest() throws Exception {
		playerTest.upgradeHP();
		playerTest.addExp(5);
		int apBefore = playerTest.getAbilityPoint();
		playerTest.upgradeHP();
		assertEquals("Player use AP", apBefore-1, playerTest.getAbilityPoint());
	}
	
}
