package tw.edu.ncu.softwareengineering.dodoio.Internet;

import java.util.ArrayList;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Wanderer;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Fertilizer.Size;

public class MOBclient implements Runnable
{
	Server server;
	int mode;
	int[] fertilizercount;
	ArrayList<Wanderer>wanderers;
	
	public MOBclient(Server ss , int mm)
	{
		// set member
		server = ss;
		mode = mm;
		
		// initial count and list
		fertilizercount = new int[3];
		fertilizercount[0] = fertilizercount[1] = fertilizercount[2] = 0;
		wanderers = new ArrayList<Wanderer>();
		
		// initial obstacle
		initboundary();
		initobstacle();
		
		//initial monster
		addsmallmonster(20);
		addmediummonster(16);
		addlargemonster(14);
		addwandermoster(2);
		
	}
	
	private void initboundary()
	{
		try
		{
			// up
			server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(3840, 1, 0), 7680, 2, false);
			++server.idcount[mode];
			
			//down
			server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(3840, 4318, 0), 7680, 2, false);
			++server.idcount[mode];
			
			//left
			server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(1, 2160, 0), 2, 4320, false);
			++server.idcount[mode];
			
			//right
			server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(7678, 2160, 0), 2, 4320, false);
			++server.idcount[mode];
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initobstacle()
	{
		addLobstacle(2000, 2000, 6);
		addLobstacle(2050, 2000, 6);
		addLobstacle(2100, 2000, 6);
		addLobstacle(2150, 2000, 6);
		addLobstacle(2200, 2000, 6);
		
		addLobstacle(4000, 1357, 2);
		addLobstacle(800, 3000, 1);
		addLobstacle(2000, 2000, 3);
		addLobstacle(2468, 2468, 5);
		addLobstacle(3874, 987, 4);
		addLobstacle(200, 857, 6);
		addLobstacle(5555, 200, 4);
		
	}
	
	private void addLobstacle(int x , int y , int direction)
	{
		try
		{
			server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x, y, 0), 50, 10, true);
			++server.idcount[mode];
			
			if(direction == 0)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x - 20, y + 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			else if(direction == 1)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x - 20, y - 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			else if(direction == 2)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x , y + 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			else if(direction == 3)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x, y - 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			else if(direction == 4)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x + 20, y + 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			else if(direction == 5)
			{
				server.cdc.collideObjectManager[mode].addObsatcle(collideObjecctClass.Obstacle, server.idcount[mode], new Position(x + 20, y - 30, 0), 10, 50, true);
				++server.idcount[mode];
			}
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void addsmallmonster(int number)
	{
		for(int i = 0 ; i < number ; ++i)
		{
			try
			{
				server.cdc.collideObjectManager[mode].addFertilizer(collideObjecctClass.Fertilizer, server.idcount[mode], server.randposition() , Size.small);
				++fertilizercount[Size.small.ordinal()];
				++server.idcount[mode];
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addmediummonster(int number)
	{
		for(int i = 0 ; i < number ; ++i)
		{
			try
			{
				server.cdc.collideObjectManager[mode].addFertilizer(collideObjecctClass.Fertilizer, server.idcount[mode], server.randposition() , Size.medium);
				++fertilizercount[Size.medium.ordinal()];
				++server.idcount[mode];
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addlargemonster(int number)
	{
		for(int i = 0 ; i < number ; ++i)
		{
			try
			{
				server.cdc.collideObjectManager[mode].addFertilizer(collideObjecctClass.Fertilizer, server.idcount[mode], server.randposition() , Size.large);
				++fertilizercount[Size.large.ordinal()];
				++server.idcount[mode];
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void addwandermoster(int number)
	{
		for(int i = 0 ; i < number ; ++i)
		{
			try
			{
				server.cdc.collideObjectManager[mode].addWanderer(collideObjecctClass.Wanderer, server.idcount[mode], server.randposition());
				wanderers.add((Wanderer)server.cdc.collideObjectManager[mode].queryObjectByID(server.idcount[mode]));
				++server.idcount[mode];
			}
			catch (Exception e)
			{
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	public void removefertilizer(int ord)
	{
		--fertilizercount[ord];
	}

	public void removewandermonster(int id)
	{
		for(Wanderer toremove : wanderers)
		{
			if(toremove.ID == id)
			{
				wanderers.remove(toremove);
				break;
			}
		}
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
		int difference;
		boolean isroundeven = false;
		while(true)
		{
			// born new monster
			difference = server.playercount[mode] * 10 - fertilizercount[Size.small.ordinal()];
			if(difference > 0)
			{
				if(difference < 5)
					addsmallmonster(difference);
				else
					addsmallmonster(5);
			}
			
			difference = server.playercount[mode] * 8 - fertilizercount[Size.medium.ordinal()];
			if(difference > 0)
			{
				if(difference < 3)
					addmediummonster(difference);
				else
					addmediummonster(3);
			}
			
			difference = server.playercount[mode] * 7 - fertilizercount[Size.large.ordinal()];
			if(difference > 0)
			{
				if(difference < 2)
					addlargemonster(difference);
				else
					addlargemonster(2);
			}
			
			if(isroundeven)
			{
				difference = server.playercount[mode] - wanderers.size();
				if(difference > 0)
					addwandermoster(1);
			}
			
			// move wander
			for(Wanderer tomove : wanderers)
				tomove.update();
			
			server.cdc.calculatecollide(mode);
			
			try
			{
				isroundeven = !isroundeven;
				Thread.sleep(1000);
			} 
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
