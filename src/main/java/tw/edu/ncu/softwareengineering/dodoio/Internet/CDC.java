package tw.edu.ncu.softwareengineering.dodoio.Internet;


import tw.edu.ncu.softwareengineering.dodoio.Collide.Bound;
import tw.edu.ncu.softwareengineering.dodoio.Collide.Utils;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Fertilizer;

public class CDC
{
	CollideObjectManager[] collideObjectManager;
	Utils utils = Utils.getInstance();
	Server server;
	
	public CDC(Server ss)
	{
		// initial collide object manager
		collideObjectManager = new CollideObjectManager[2];
		collideObjectManager[0] = new CollideObjectManager();
		collideObjectManager[1] = new CollideObjectManager();
		
		// set server
		server = ss;

	}
	
	public void calculatecollide(int mode)
	{		
		// calculate collide
		utils.CalculateCollide(collideObjectManager[mode], new Bound(0, 0, 7680, 4320));
		
		// check which object has change
		for(CollideObject tohandle : collideObjectManager[mode].collideObjectList)
		{
			if(tohandle.getFlag())
			{
				// if object is alive, then reset flag
				if(tohandle.getHP() > 0)
					tohandle.resetFlag();
				else
				{
					if(tohandle.className == collideObjecctClass.FertilizerBig.ordinal() ||
							tohandle.className == collideObjecctClass.FertilizerMedium.ordinal() || 
							tohandle.className == collideObjecctClass.FertilizerSmall.ordinal())
						server.mobclients.get(mode).removefertilizer(((Fertilizer)tohandle).size);
					else if(tohandle.className == collideObjecctClass.Wanderer.ordinal())
						server.mobclients.get(mode).removewandermonster(tohandle.ID);
					
					collideObjectManager[mode].collideObjectList.remove(tohandle);
				}

				server.broacast_update(mode, tohandle);

			}
		}
		
	}
	
}
