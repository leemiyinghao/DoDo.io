package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;

public class UDPreceive implements Runnable
{
	Game game;
	byte[] buffer;
	
	public UDPreceive(Game game)
	{
		// TODO Auto-generated constructor stub
		this.game = game;
		buffer = new byte[1024];
	}
	
	@Override
	public void run()
	{
		/*
		 * muilti-thread method
		 * process broacast receive
		 * handle the socket exception
		 */
		// TODO Auto-generated method stub
		try
		{
			DatagramSocket socket = new DatagramSocket(10000 + game.myObjManager.getMyPlayer().ID);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			String broacaststr;
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapterFactory(new ColliderTypeAdapterFactory());
			Gson gson = gsonBuilder.create();
			JsonObject broacastobj;
			
			while(true)
			{
				socket.receive(packet);
				broacaststr = new String(packet.getData());
				int temp = broacaststr.indexOf("flag");
				temp = broacaststr.indexOf("}", temp);
				broacaststr = broacaststr.substring(0, temp+1);
				// process string to object
				broacastobj = gson.fromJson(broacaststr, JsonObject.class);
				int index = findlistindex(broacastobj.get("ID").getAsInt());
				CollideObject target = gson.fromJson(broacaststr , game.myObjManager.collideObjectList.get(index).getClass());
				
				// if flag is set, means object is dead, then remove object
				if(target.getFlag())
					game.myObjManager.collideObjectList.remove(game.myObjManager.queryObjectByID(target.ID));
				else
					game.myObjManager.collideObjectList.set(index, target);
			}
			
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	private int findlistindex(int id)
	{
		ArrayList<CollideObject> temp = game.myObjManager.collideObjectList;
		
		for(int i = 0 ; i < temp.size() ; ++i)
		{
			if(temp.get(i).ID == id)
				 return i;
		}
		
		return -1;
	}
}
