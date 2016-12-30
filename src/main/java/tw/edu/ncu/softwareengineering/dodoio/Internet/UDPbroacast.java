package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class UDPbroacast
{
	private ArrayList<InetAddress> clientlist;
	private ArrayList<Integer> idlist;
	
	
	public UDPbroacast(ArrayList<InetAddress> clientlist , ArrayList<Integer> idlist)
	{
		// TODO Auto-generated constructor stub
		this.clientlist = clientlist;
		this.idlist = idlist;
	}
	
	public void broacast(String broacastdata)
	{
		/*
		 * process the broacast
		 * handle the socket exception
		 */
		try
		{
			
			for(int i = 0 ; i < clientlist.size() ; ++i)
			{
				DatagramSocket socket = new DatagramSocket();
				DatagramPacket packet = new DatagramPacket(broacastdata.getBytes(), broacastdata.getBytes().length);
				packet.setAddress(clientlist.get(i));
				packet.setPort(10000 + idlist.get(i));
				
				socket.send(packet);
				socket.close();
			}
			
		} 
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
