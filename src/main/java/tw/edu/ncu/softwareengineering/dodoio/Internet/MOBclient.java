package tw.edu.ncu.softwareengineering.dodoio.Internet;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Fertilizer.Size;

public class MOBclient implements Runnable
{
	Server server;
	int mode;
	int smallcount;
	int mediumcount;
	int largecount;
	
	public MOBclient(Server ss , int mm)
	{
		// set member
		server = ss;
		mode = mm;
		
		// initial list
		smallcount = 0;
		mediumcount = 0;
		largecount = 0;
		
		//initial monster
		addsmallmonster(20);
		addmediummonster(16);
		addlargemonster(14);
		
	}
	
	private void addsmallmonster(int number)
	{
		for(int i = 0 ; i < number ; ++i)
		{
			try
			{
				server.cdc.collideObjectManager[mode].addFertilizer(collideObjecctClass.Fertilizer, server.idcount[mode], server.randposition() , Size.small);
				
				++smallcount;
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
				++mediumcount;
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
				++largecount;
				++server.idcount[mode];
			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
		int difference;
		while(true)
		{
			// born new monster
			difference = server.playercount[mode] * 10 - smallcount;
			if(difference > 0)
			{
				if(difference < 5)
					addsmallmonster(difference);
				else
					addsmallmonster(5);
			}
			
			difference = server.playercount[mode] * 8 - mediumcount;
			if(difference > 0)
			{
				if(difference < 3)
					addmediummonster(difference);
				else
					addmediummonster(3);
			}
			
			difference = server.playercount[mode] * 7 - largecount;
			if(difference > 0)
			{
				if(difference < 2)
					addlargemonster(difference);
				else
					addlargemonster(2);
			}
			
			try
			{
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
