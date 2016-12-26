package tw.edu.ncu.softwareengineering.dodoio.Internet;


import tw.edu.ncu.softwareengineering.dodoio.Collide.Bound;
import tw.edu.ncu.softwareengineering.dodoio.Collide.Utils;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class CDC
{
	CollideObjectManager[] collideObjectManager;
	Utils utils = Utils.getInstance();

	
	public CDC()
	{
		// initial collide object manager
		collideObjectManager = new CollideObjectManager[2];
		collideObjectManager[0] = new CollideObjectManager();
		collideObjectManager[1] = new CollideObjectManager();

	}
	
	public void calculatecollide(int mode)
	{		
		// calculate collide
		utils.CalculateCollide(collideObjectManager[mode], new Bound(0, 0, 7680, 4320));

	}
	
}
