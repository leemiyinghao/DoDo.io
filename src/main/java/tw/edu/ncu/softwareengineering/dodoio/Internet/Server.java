package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.JSONObject;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class Server
{
	ServerSocket serversocket;
	ArrayList<ArrayList<InetAddress>> clientaddresslist;
	ArrayList<ArrayList<Integer>> clientidlist;
	CollideObjectManager[] collideObjectManager;

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
			
			// initial client address list , id list
			clientaddresslist.add(new ArrayList<InetAddress>());
			clientaddresslist.add(new ArrayList<InetAddress>());
			clientidlist.add(new ArrayList<Integer>());
			clientidlist.add(new ArrayList<Integer>());
			
			
			// initial collideObjectManager
			
			
			
			while(true)
			{
				Socket clientsocket = serversocket.accept();
				if(clientsocket.isConnected())
				{
					DataOutputStream wdata = new DataOutputStream(clientsocket.getOutputStream());
					DataInputStream rdata = new DataInputStream(clientsocket.getInputStream());
					
					String newplayerstr = rdata.readUTF();
					JSONObject newplayrejson = new JSONObject(newplayerstr);
					
					clientaddresslist.get(newplayrejson.getInt("mode")).add(clientsocket.getInetAddress());
					
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
			
			while(true)
			{
				try
				{
					String playerupdatestr = rdata.readUTF();
					JSONObject playerupdatejson = new JSONObject(playerupdatestr);
					
					
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
