package tw.edu.ncu.softwareengineering.dodoio;

import java.util.Scanner;

import javax.swing.JFrame;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
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
				myRenderer.setSize(1024, 768);
				myRenderer.setStat(GameStat.MAINMENU);
				myRenderer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myRenderer.render(33); // 1/30 sec. = 33.3 msec.
				myRenderer.setVisible(true);
				break;
			default:
        }
	}
}