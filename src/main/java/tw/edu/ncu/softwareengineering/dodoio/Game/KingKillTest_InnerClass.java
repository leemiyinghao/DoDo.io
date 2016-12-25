package tw.edu.ncu.softwareengineering.dodoio.Game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class KingKillTest_InnerClass {
	private KingKill game;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new KingKill();
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 1);
	}

	@After
	public void tearDown() throws Exception {
		game = null;
	}

	@Test
	public void testGetActivePlayerNum() {
		int num = game.GetActivePlayerNum(game.myObjManager.collideObjectList);
		System.out.println(game.myObjManager.collideObjectList);
		assertEquals("It should be only 1 player!", 1, num);
	}

	@Test
	public void testInnerStateMachineRun_0_to_1() {
		game.activePlayerNum = 1;
		game.InnerStateMachineRun();
		assertEquals("The inner state should change to 1", 
				game.inner_gamestate, GameState.ONE);
	}
	
	@Test
	public void testInnerStateMachineRun_0_to_2() {
		game.activePlayerNum = 2;
		game.InnerStateMachineRun();
		assertEquals("The inner state should change to MORETHENONE", 
				game.inner_gamestate, GameState.MORETHENONE);
	}
	
	@Test
	public void testInnerStateMachineRun_1_to_2() {
		game.activePlayerNum = 1;
		game.InnerStateMachineRun();
		game.activePlayerNum = 2;
		game.InnerStateMachineRun();
		assertEquals("The inner state should change to MORETHENONE", 
				game.inner_gamestate, GameState.MORETHENONE);
	}
	//main player
	@Test
	public void testInnerStateMachineRun_2_to_1() {
		game.activePlayerNum = 2;
		game.InnerStateMachineRun();
		game.activePlayerNum = 1;
		game.InnerStateMachineRun();
		assertEquals("The inner state should change to MORETHENONE", 
				game.inner_gamestate, GameState.MORETHENONE);
	}

}
