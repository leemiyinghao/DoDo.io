package tw.edu.ncu.softwareengineering.dodoio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client
{
	Socket socket;
	static DataInputStream rdata;
	static DataOutputStream wdata;
	static int userID;
	
	public Client()
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
	
	public void updatetoserver()
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
				
			}
		}
	}
}
