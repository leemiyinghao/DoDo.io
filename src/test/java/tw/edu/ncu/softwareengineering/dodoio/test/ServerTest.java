package tw.edu.ncu.softwareengineering.dodoio.Internet;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

public class ServerTest
{
	@Rule
	Server server;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		server = new Server();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		assertEquals(server.test(1, 9), 10);
	}

}
