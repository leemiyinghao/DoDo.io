package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.DatagramSocket;
import java.net.Socket;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class Client
{
	Socket socket;
	static DataInputStream rdata;
	static DataOutputStream wdata;
	static int userID;
	
	public Client(int mode , CollideObjectManager collideObjectManager)
	{
		/*
		 * Client constructor
		 * make the socket connection
		 * and handle the socket exception
		*/
		try
		{
			socket = new Socket("127.0.0.1" , 55555);
			rdata = new DataInputStream(socket.getInputStream());
			wdata = new DataOutputStream(socket.getOutputStream());
			
						
			userID = rdata.readInt();
			
			Thread thread = new Thread(new datamanager());
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
	
	static class datamanager implements Runnable
	{
		
		@Override
		public void run()
		{
			/*
			 * function for immplement multi-thread
			 * while loop for read update from other client
			 */
			
			while(true)
			{
				try
				{
					DatagramSocket recvbroacastsocket = new DatagramSocket(10000 + userID);
					
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
