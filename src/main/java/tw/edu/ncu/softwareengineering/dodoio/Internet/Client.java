package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.json.JSONObject;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;

public class Client
{
	Socket socket;
	DataInputStream rdata;
	DataOutputStream wdata;
	Game game;
	
	public Client(Game game , String name , int profession , int mode)
	{
		/*
		 * Client constructor
		 * make the socket connection
		 * and handle the socket exception
		*/
		this.game = game;
		
		try
		{
			socket = new Socket("127.0.0.1" , 55555);
			rdata = new DataInputStream(socket.getInputStream());
			wdata = new DataOutputStream(socket.getOutputStream());
			
			JSONObject charactdata = new JSONObject();
			charactdata.put("name", name);
			charactdata.put("profession", profession);
			charactdata.put("mode", mode);
			
			wdata.writeUTF(charactdata.toString());
			
			String collidestr = rdata.readUTF();
			JSONObject collidelist = new JSONObject(collidestr);
						
			Thread thread = new Thread(new UDPreceive(game));
			thread.start();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void update()
	{
		
	}
	
}
