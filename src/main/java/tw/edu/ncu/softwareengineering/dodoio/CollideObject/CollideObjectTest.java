package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CollideObjectTest {
	Archer playerTest;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		playerTest = new Archer(1, "test", Character.TeamName.deathMatch, 
				new Position(0, 0, 0), new CollideObjectManager(), 
				CollideObjectManager.collideObjecctClass.Archer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
