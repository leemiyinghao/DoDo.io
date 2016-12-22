package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;
import tw.edu.ncu.softwareengineering.dodoio.Internet.ColliderTypeAdapterFactory;

public class Client
{
	Socket socket;
	DataInputStream rdata;
	DataOutputStream wdata;
	Game game;
	GsonBuilder gsonBuilder;
	Gson gson;
	int mode;

	public Client(Game game , String name , CollideObjectManager.collideObjecctClass profession , int mode)
	{
		/*
		 * Client constructor
		 * make the socket connection
		 * and handle the socket exception
		*/
		this.game = game;
		this.mode = mode;
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(new ColliderTypeAdapterFactory());
		gson = gsonBuilder.create();
		
		try
		{
			socket = new Socket("127.0.0.1" , 55555);
			
			rdata = new DataInputStream(socket.getInputStream());
			wdata = new DataOutputStream(socket.getOutputStream());

			
			JsonObject charactdata = new JsonObject();
			charactdata.addProperty("name", name);
			charactdata.addProperty("profession", profession.toString());
			charactdata.addProperty("mode", mode);
			
			wdata.writeUTF(charactdata.toString());
			wdata.flush();
			
			
			// process collidelist for all client
			int newid = rdata.readInt();
			String collidestr = rdata.readUTF();
			while(!collidestr.equals("done"))
			{
				Class cls = Class.forName(collidestr);
				collidestr = rdata.readUTF();
				CollideObject temp = gson.fromJson(collidestr, cls);
				game.myObjManager.collideObjectList.add(temp);
				collidestr = rdata.readUTF();
			}
			
			//add main character
			this.game.myObjManager.setMainPlayer(this.game.myObjManager.queryObjectByID(newid));
			
				
			Thread thread = new Thread(new UDPreceive(this.game));
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
		try
		{
			String string = gson.toJson(game.myObjManager.getMyPlayer());
			wdata.writeInt(0);
			wdata.writeUTF(string);
			wdata.flush();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newattack(collideObjecctClass classname , int id)
	{
		try
		{
			wdata.writeInt(1);
			wdata.writeUTF(classname.toString());
			wdata.writeInt(id);
			wdata.flush();
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
