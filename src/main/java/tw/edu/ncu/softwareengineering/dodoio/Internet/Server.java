package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	ServerSocket serversocket;
	int idcount[];
	ArrayList<InetAddress> clientaddresslist;
	

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
			
			// initial idcount , 0 for deathmatch , 1 for killking
			idcount = new int[2];
			idcount[0] = 0;
			idcount[1] = 0;
			
			clientaddresslist = new ArrayList<>();
			
			while(true)
			{
				Socket clientsocket = serversocket.accept();
				if(clientsocket.isConnected())
				{
					DataOutputStream wdata = new DataOutputStream(clientsocket.getOutputStream());
					
					
					clientaddresslist.add(clientsocket.getInetAddress());
					
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
	
	public void broacast_update()
	{
		for(int i = 0 ; i < clientaddresslist.size() ; ++i)
		{
			try
			{
				DatagramSocket broacastsocket = new DatagramSocket();
				
			} 
			catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
			
		}
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
			 * and broacast to every other client
			 */
			while(true)
			{
				
			}
		}
		
	}
	
}
