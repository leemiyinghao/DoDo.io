package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;

public class Client
{
	Socket socket;
	DataInputStream rdata;
	DataOutputStream wdata;
	Game game;
	
	public Client(Game game , String name , CollideObjectManager.collideObjecctClass profession , int mode)
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
			Gson gson = new Gson();
			
			JsonObject charactdata = new JsonObject();
			charactdata.addProperty("name", name);
			charactdata.addProperty("profession", profession.toString());
			charactdata.addProperty("mode", mode);
			
			wdata.writeUTF(charactdata.toString());
			
			String collidestr = rdata.readUTF();
			JsonObject collidelist = gson.fromJson(collidestr, JsonObject.class);
			
			// process collidelist for all client
			
						
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
