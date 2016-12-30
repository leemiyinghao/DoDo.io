package tw.edu.ncu.softwareengineering.dodoio;

import java.util.Scanner;
import java.util.Date;

import javax.swing.JFrame;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.Game.DeathMatch;
import tw.edu.ncu.softwareengineering.dodoio.Graphic.Control;
import tw.edu.ncu.softwareengineering.dodoio.Graphic.GameStat;
import tw.edu.ncu.softwareengineering.dodoio.Graphic.Map;
import tw.edu.ncu.softwareengineering.dodoio.Graphic.Renderer;
import tw.edu.ncu.softwareengineering.dodoio.Internet.Server;

public final class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Server myServer = null;
		CollideObjectManager myCOM = null;
		Map myMap = null;
		Renderer myRenderer = null;
		Control myControl = null;
		//JFrame frame = null;
		
		Scanner scanner = new Scanner(System.in);
		String choice = "123";
		Boolean startFlag = false;
		System.out.println("Welcome to Dodo.io!");
		System.out.println("Please enter a number to choose server mode or client mode!");
		while (!startFlag) {
			System.out.println("0:server, 1:client");
			choice = scanner.next();
			switch (choice) {
				case "0":
				case "1":
					startFlag = true;
					break;
				default:
					System.out.println("Wrong choice, retry!");
			}
		}
        scanner.close();
        switch (choice) {
			case "0":
				System.out.println("You decide to create a server!");
				myServer = new Server();
				myServer.startserver();
				break;
			case "1":
				System.out.println("You decide to create a client!");
				myCOM = new CollideObjectManager();
				myMap = new Map();

				myRenderer = new Renderer(myCOM, myMap);
				myRenderer.setTitle("DoDo.io");
				myControl = new Control(myCOM, myRenderer);
				//myRenderer.setStat(GameStat.MAINMENU);
				myRenderer.setStat(GameStat.INGAME);
				myRenderer.game = new DeathMatch();
				myRenderer.game.start("fuck", collideObjecctClass.Archer, 0);
				myRenderer.collideObjectManager = myRenderer.game.getCollideObjectManager();
				myControl.collideObjectManager = myRenderer.game.getCollideObjectManager();
				
			try {
				myRenderer.mainCharacter = myRenderer.collideObjectManager.getMyPlayer();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				myRenderer.addKeyListener(myControl);
				myRenderer.setSize(1024, 768);
				myRenderer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				/*try {
					System.out.println("X:" + myRenderer.collideObjectManager.queryObjectByID(0).getPosition().getX() + " Y:"+ 
							myRenderer.collideObjectManager.queryObjectByID(0).getPosition().getY());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				myRenderer.pack();
				myRenderer.setVisible(true);
				long oldTime = System.currentTimeMillis();
				while(true) {
					long newTime = System.currentTimeMillis();
					int timeDiff = (int)newTime-(int)oldTime;
					myRenderer.render(timeDiff);
					myControl.update(timeDiff);
					try {
						Thread.sleep(5);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
		default:
				System.out.println("You should never see this line!");
        }
	}
}