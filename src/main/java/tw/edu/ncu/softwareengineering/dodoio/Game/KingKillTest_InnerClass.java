package tw.edu.ncu.softwareengineering.dodoio.Game;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.SwordMan;

public class KingKillTest_InnerClass {
	private KingKill game;
	private SwordMan mainPlayer;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		game = new KingKill();
		mainPlayer = new SwordMan(0, "kirito", TeamName.deathMatch, new Position(0, 0, 0.0), game.myObjManager, 0);
		game.start("kirito", CollideObjectManager.collideObjecctClass.SwordMan, 1);
		game.myObjManager.setMainPlayer(mainPlayer);
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
	/*****************************************************************************/
	//For InnerStateMachine Test
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
