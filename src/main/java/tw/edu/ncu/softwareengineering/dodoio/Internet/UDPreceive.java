package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
			DatagramSocket socket = new DatagramSocket(10000);
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			String broacaststr;
			Gson gson = new Gson();
			JsonObject broacastobj;
			
			while(true)
			{
				socket.receive(packet);
				broacaststr = new String(packet.getData());
				
				// process string to object
				broacastobj = gson.fromJson(broacaststr, JsonObject.class);
				
				
			}
			
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
