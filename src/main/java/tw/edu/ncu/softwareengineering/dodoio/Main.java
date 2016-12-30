package tw.edu.ncu.softwareengineering.dodoio;

import java.util.Scanner;

import javax.swing.JFrame;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
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
		JFrame frame = null;
		
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
				frame = new JFrame("DoDo.io");
				
				myRenderer = new Renderer(myCOM, myMap);
				myControl = new Control(myCOM, myRenderer);
				myRenderer.setStat(GameStat.MAINMENU);
				myRenderer.render(33); // 1/30 sec. = 33.3 msec.
				
				frame.addKeyListener(myControl);
				frame.add(myRenderer);
				frame.pack();
				frame.setSize(1024, 768);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				while(true) {
					myControl.update(33);
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