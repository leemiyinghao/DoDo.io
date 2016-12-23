package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;

public class MagicBallTest {
	MagicBall magicBall;
	Position setPosition;
	CollideObjectManager manager;
	Magician setPlayer;
	int miliSecond = 1000;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		setPosition = new Position(0, 0, 0);
		manager = new CollideObjectManager();
		setPlayer = new Magician(1, "archerX", TeamName.deathMatch, setPosition, manager, collideObjecctClass.Magician.ordinal());
		magicBall = new MagicBall(4, setPosition, manager, collideObjecctClass.MagicBall.ordinal(), setPlayer);
		manager.collideObjectList.add(setPlayer);
		manager.collideObjectList.add(magicBall);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFly() {
		int flyTime = 0;
		int maxFlyTime = (int) (magicBall.range/magicBall.ballSpeed);
		for(int i=0; i<maxFlyTime; i++) {
			magicBall.traversal = 0;
			flyTime = i;
			magicBall.fly(flyTime*miliSecond);
			assertEquals("fly for "+flyTime+" seconds", magicBall.ballSpeed*flyTime, magicBall.traversal, 1);
		}
		
		flyTime = maxFlyTime+10;
		magicBall.fly(flyTime*miliSecond);
		assertEquals("fly for "+flyTime+" seconds", magicBall.range, magicBall.traversal, 1);
	}

}
