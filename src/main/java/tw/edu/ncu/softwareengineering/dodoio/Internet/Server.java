package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character.TeamName;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;
import tw.edu.ncu.softwareengineering.dodoio.Internet.CDC;
import tw.edu.ncu.softwareengineering.dodoio.Internet.ColliderTypeAdapterFactory;

public class Server
{
	ServerSocket serversocket;
	ArrayList<ArrayList<InetAddress>> clientaddresslist;
	ArrayList<ArrayList<Integer>> clientidlist;
	ArrayList<MOBclient> mobclients;
	CDC cdc;
	GsonBuilder gsonBuilder;
	Gson gson;
	int[] idcount;
	int [] playercount;
	int[] king;
	boolean teamcount;

	
	public Server()
	{
		// TODO Auto-generated constructor stub
		
		// initial client address list , id list
		clientaddresslist = new ArrayList<ArrayList<InetAddress>>();
		clientaddresslist.add(new ArrayList<InetAddress>());
		clientaddresslist.add(new ArrayList<InetAddress>());
		
		clientidlist = new ArrayList<ArrayList<Integer>>();
		clientidlist.add(new ArrayList<Integer>());
		clientidlist.add(new ArrayList<Integer>());
		
		// initial CDC
		cdc = new CDC();
		
		//initial mobclient and start client
		mobclients = new ArrayList<MOBclient>();
		mobclients.add(new MOBclient(this, 0));
		mobclients.add(new MOBclient(this, 1));
		new Thread(mobclients.get(0)).start();
		new Thread(mobclients.get(1)).start();
		
		
		// initial gson
		gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapterFactory(new ColliderTypeAdapterFactory());
		gson = gsonBuilder.create();
		
		// initial socket
		try
		{
			serversocket = new ServerSocket(55555);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// initial counter
		teamcount = true;
		playercount = new int[2];
		playercount[0] = 0;
		playercount[1] = 0;
		idcount = new int[2];
		idcount[0] = 0;
		idcount[1] = 0;
		
		//initial king
		king = new int[2];
		king[0] = -1;
		king[1] = -1;
		
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
			
			while(true)
			{
				
				Socket clientsocket = serversocket.accept();
				if(clientsocket.isConnected())
				{
					DataOutputStream wdata = new DataOutputStream(clientsocket.getOutputStream());
					DataInputStream rdata = new DataInputStream(clientsocket.getInputStream());
					

					// read message from client and add new player
					JsonObject newplayrejson = gson.fromJson(rdata.readUTF(), JsonObject.class);
					int mode = newplayrejson.get("mode").getAsInt();
					clientaddresslist.get(mode).add(clientsocket.getInetAddress());
					String profession = newplayrejson.get("profession").getAsString();
					String teamname;
					if(mode == 0)
						teamname = "deathMatch";
					else
					{
						if(teamcount)
						{
							teamname = "teamBlue";
							if(king[0] == -1)
								king[0] = idcount[mode];
						}
						else
						{
							teamname = "teamRed";
							if(king[1] == -1)
								king[1] = idcount[mode];
						}
						teamcount = !teamcount;
					}

					cdc.collideObjectManager[mode].addCharacter(collideObjecctClass.valueOf(profession), idcount[mode], randposition(), newplayrejson.get("name").getAsString(), TeamName.valueOf(teamname));
					clientidlist.get(mode).add(idcount[mode]);
					++idcount[mode];
					++playercount[mode];
					
					// sending collideobject list to client
					wdata.writeInt(idcount[mode]);
					wdata.flush();
										
					for(CollideObject object : cdc.collideObjectManager[mode].collideObjectList)
					{
						wdata.writeUTF(object.getClass().getName());
						wdata.writeUTF(gson.toJson(object));
						wdata.flush();
					}
					
					wdata.writeUTF("done");
					wdata.flush();
					
					// send king id if mode is kingkill
					if(mode == 1)
					{
						wdata.writeInt(king[0]);
						wdata.writeInt(king[1]);
						wdata.flush();
					}
					
					Thread thread = new Thread(new clientmanager(clientsocket , this , mode));
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
	
	public Position randposition()
	{
		// future to change alogorithm
		Random random = new Random();
		Position position = new Position(random.nextInt(7680), random.nextInt(4320), random.nextDouble());
		return position;
	}
	
	public void broacast_update(int mode , int index)
	{
		UDPbroacast broacaster = new UDPbroacast(clientaddresslist.get(mode), clientidlist.get(mode));
		
		// process the broacast data
		CollideObject target = cdc.collideObjectManager[mode].collideObjectList.get(index);
		JsonObject data = new JsonObject();
		data.addProperty("index", index);
		data.add("object", gson.toJsonTree(target));
		broacaster.broacast(data.toString());
		
	}
	
	static class clientmanager implements Runnable
	{
		Server server;
		Socket clientsocket;
		DataInputStream rdata;
		int mode;
		
		public clientmanager(Socket client , Server ss , int md)
		{
			/*
			 * clientmanager constructor
			 * handle the inputstream exception
			 */
			server = ss;
			mode = md;
			
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
			 * Implement run() for multithread
			 * while loop to read the data from client
			 * and update to CDC
			 * handle the inputstream exception
			 */

			while(true)
			{
				try
				{
					// get type for update. 1 for object update, 2 for new attack object
					int type = rdata.readInt();
					
					if(type == 0)
					{
						String playerupdatestr = rdata.readUTF();
						JsonObject jsonObject = server.gson.fromJson(playerupdatestr, JsonObject.class);
						int index = findlistindex(jsonObject.get("ID").getAsInt());
						CollideObject obj =  server.gson.fromJson(jsonObject, server.cdc.collideObjectManager[mode].collideObjectList.get(index).getClass());
						
						server.cdc.collideObjectManager[mode].collideObjectList.set(index,obj);
						server.cdc.calculatecollide(mode);
						server.broacast_update(mode , index);
					}
					else
					{
						String classname = rdata.readUTF();
						int id = rdata.readInt();
						int index = findlistindex(id);
						
						server.cdc.collideObjectManager[mode].addAttackObject(collideObjecctClass.valueOf(classname), server.idcount[mode], server.randposition(),(Character) server.cdc.collideObjectManager[mode].collideObjectList.get(index));
						++server.idcount[mode];
					}

				}
				catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		private int findlistindex(int id)
		{
			ArrayList<CollideObject> temp = server.cdc.collideObjectManager[mode].collideObjectList;
			
			for(int i = 0 ; i < temp.size() ; ++i)
			{
				if(temp.get(i).ID == id)
					 return i;
			}
			
			return -1;
		}
		
	}
	
}
