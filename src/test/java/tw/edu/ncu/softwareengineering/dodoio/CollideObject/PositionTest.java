package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PositionTest {
	Position positionTest;
	int setX, setY;
	double setAngle;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		setX = 0;
		setY = 0;
		setAngle = 0;
		positionTest = new Position(setX, setY, setAngle);
	}

	@After
	public void tearDown() throws Exception {
		setX = 0;
		setY = 0;
		setAngle = 0;
		positionTest = null;
	}

	@Test
	public void testSetPosition() {
		setX = 60;
		setY = 52;
		setAngle = 0.31468;
		positionTest.setPosition(setX, setY, setAngle);
		assertEquals("test SetPosition", setX, positionTest.getX());
		assertEquals("test SetPosition", setY, positionTest.getY());
		assertEquals(setAngle, positionTest.getDirection(), 0.0000001);
		
		setX = -100;
		setY = 52;
		setAngle = 0.31468;
		positionTest.setPosition(setX, setY, setAngle);
		assertEquals("test SetPosition", 0, positionTest.getX());
		assertEquals("test SetPosition", setY, positionTest.getY());
		assertEquals(setAngle, positionTest.getDirection(), 0.0000001);
		
		setX = -100;
		setY = -52;
		setAngle = 1.2258;
		positionTest.setPosition(setX, setY, setAngle);
		assertEquals("test SetPosition", 0, positionTest.getX());
		assertEquals("test SetPosition", 0, positionTest.getY());
		assertEquals(0, positionTest.getDirection(), 0.0000001);
		
		setX = -100;
		setY = -52;
		setAngle = -1.2258;
		positionTest.setPosition(setX, setY, setAngle);
		assertEquals("test SetPosition", 0, positionTest.getX());
		assertEquals("test SetPosition", 0, positionTest.getY());
		assertEquals(0, positionTest.getDirection(), 0.0000001);
	}

	@Test
	public void testProjection() {
		
		setX = 150;
		setY = 125;
		setAngle = 0;
		int travel = 100;
		positionTest.setPosition(setX, setY, setAngle);
		Position.projection(travel, positionTest);
		assertEquals("test SetPosition", setX + travel, positionTest.getX(), 1);
		assertEquals("test SetPosition", setY, positionTest.getY());
		
		setX = 150;
		setY = 125;
		setAngle = 0.25;
		travel = 100;
		positionTest.setPosition(setX, setY, setAngle);
		Position.projection(travel, positionTest);
		assertEquals("test SetPosition", setX, positionTest.getX(), 1);
		assertEquals("test SetPosition", setY + travel, positionTest.getY());
		
		setX = 150;
		setY = 125;
		setAngle = 0.5;
		travel = 100;
		positionTest.setPosition(setX, setY, setAngle);
		Position.projection(travel, positionTest);
		assertEquals("test SetPosition", setX - travel, positionTest.getX(), 1);
		assertEquals("test SetPosition", setY, positionTest.getY());
		
		setX = 150;
		setY = 125;
		setAngle = 0.75;
		travel = 100;
		positionTest.setPosition(setX, setY, setAngle);
		Position.projection(travel, positionTest);
		assertEquals("test SetPosition", setX, positionTest.getX(), 1);
		assertEquals("test SetPosition", setY - travel, positionTest.getY());
	}

}
