package tw.edu.ncu.softwareengineering.dodoio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server
{
	ServerSocket serversocket;
	static Vector outputvc;
	int idcount;

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
			outputvc = new Vector();
			idcount = 0;
			
			while(true)
			{
				Socket clientsocket = serversocket.accept();
				if(clientsocket.isConnected())
				{
					DataOutputStream wdata = new DataOutputStream(clientsocket.getOutputStream());
					
					wdata.writeInt(idcount + 1);
					wdata.flush();
					
					outputvc.add(wdata);
					++idcount;
					
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
