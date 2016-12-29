package tw.edu.ncu.softwareengineering.dodoio;

import java.io.IOException;
import java.util.Scanner;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
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
				myRenderer.setVisible(true);
				break;
			default:
        }
	}
}