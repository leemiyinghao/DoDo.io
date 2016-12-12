package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Archer;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Magician;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.SwordMan;


public class Server
{
	ServerSocket serversocket;
	ArrayList<ArrayList<InetAddress>> clientaddresslist;
	ArrayList<ArrayList<Integer>> clientidlist;
	boolean teamcount;
	CDC cdc;
	
	public int test(int a , int b)
	{
		return a + b;
	}

	public void startserver()
	{
		/*
		 * Server constructor
		 * start the server socket
		 * while loop to listen the connection from client
		 * handle the socket exception
		 */
		
		try
		{
			serversocket = new ServerSocket(55555);
			teamcount = true;
			
			// initial client address list , id list
			clientaddresslist.add(new ArrayList<InetAddress>());
			clientaddresslist.add(new ArrayList<InetAddress>());
			clientidlist.add(new ArrayList<Integer>());
			clientidlist.add(new ArrayList<Integer>());
			
			
			// initial CDC
			cdc = new CDC();
			
			
			while(true)
			{
				Socket clientsocket = serversocket.accept();
				if(clientsocket.isConnected())
				{
					DataOutputStream wdata = new DataOutputStream(clientsocket.getOutputStream());
					DataInputStream rdata = new DataInputStream(clientsocket.getInputStream());
					
					Gson gson = new Gson();
					JsonObject newplayrejson = gson.fromJson(rdata.readUTF(), JsonObject.class);

					int mode = newplayrejson.get("mode").getAsInt();
					
					clientaddresslist.get(mode).add(clientsocket.getInetAddress());
					
					String profession = newplayrejson.get("profession").getAsString();
					int newid = cdc.collideObjectManager[mode].collideObjectList.size();
					String teamname;
					if(mode == 0)
						teamname = "deathMatch";
					else
					{
						if(teamcount)
							teamname = "teamBlue"; 
						else
							teamname = "teamRed";
						teamcount = !teamcount;
					}
					
					if(profession.equals(collideObjecctClass.SwordMan.toString()))
					{
						cdc.collideObjectManager[mode].collideObjectList.add(new SwordMan(newid, newplayrejson.get("name").getAsString(), teamname, null, null, 0));
					}
					else if(profession.equals(collideObjecctClass.Archer.toString()))
					{
						cdc.collideObjectManager[mode].collideObjectList.add(new Archer(newid, newplayrejson.get("name").getAsString(), teamname, null, null, 0));
					}
					else 
					{
						cdc.collideObjectManager[mode].collideObjectList.add(new Magician(newid, newplayrejson.get("name").getAsString(), teamname, null, null, 0));
					}
					
					
					
					
					
					Thread thread = new Thread(new clientmanager(clientsocket));
					thread.start();
				}
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void broacast_update(int mode)
	{
		UDPbroacast broacaster = new UDPbroacast(clientaddresslist.get(mode), clientidlist.get(mode));
		
		// process the broacast data
		
	}
	
		
	static class clientmanager implements Runnable
	{
		Socket clientsocket;
		DataInputStream rdata;
		
		public clientmanager(Socket client)
		{
			/*
			 * clientmanager constructor
			 * handle the inputstream exception
			 */

			try
			{
				clientsocket = client;
				rdata = new DataInputStream(clientsocket.getInputStream());
			} 
			catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		@Override
		public void run()
		{
			/*
			 * Implement run() for multi-thread
			 * while loop to read the data from client
			 * and update to CDC
			 * handle the inputstream exception
			 */
			
			Gson gson = new Gson();
			JsonObject playerupdatejson;
			
			while(true)
			{
				try
				{
					String playerupdatestr = rdata.readUTF();
					
					
					playerupdatejson = gson.fromJson(playerupdatestr, JsonObject.class);
					
					
				} 
				catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
}
