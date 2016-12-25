package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;

public class ArrowTest {
	Arrow arrow;
	Position setPosition;
	CollideObjectManager manager;
	Archer setPlayer;
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
		setPlayer = new Archer(1, "archerX", TeamName.deathMatch, setPosition, manager, collideObjecctClass.Archer.ordinal());
		arrow = new Arrow(4, setPosition, manager, collideObjecctClass.Arrow.ordinal(), setPlayer);
		manager.collideObjectList.add(setPlayer);
		manager.collideObjectList.add(arrow);
	}

	@After
	public void tearDown() throws Exception {
		setPosition = null;
		manager = null;
		setPlayer = null;
		arrow = null;
	}

	@Test
	public void testFly() {
		int flyTime = 0;
		int maxFlyTime = (int) (arrow.range/arrow.arrowSpeed);
		for(int i=0; i<maxFlyTime; i++) {
			arrow.traversal = 0;
			flyTime = i;
			arrow.fly(flyTime*miliSecond);
			assertEquals("fly for "+flyTime+" seconds", arrow.arrowSpeed*flyTime, arrow.traversal, 1);
		}
		
		flyTime = maxFlyTime+10;
		arrow.fly(flyTime*miliSecond);
		assertEquals("fly for "+flyTime+" seconds", arrow.range, arrow.traversal, 1);
	}

}
