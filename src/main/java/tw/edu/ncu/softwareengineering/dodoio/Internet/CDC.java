package tw.edu.ncu.softwareengineering.dodoio.Internet;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class CDC
{
	public CollideObjectManager[] collideObjectManager;
	
	public CDC()
	{
		collideObjectManager = new CollideObjectManager[2];
		collideObjectManager[0] = new CollideObjectManager();
		collideObjectManager[1] = new CollideObjectManager();
	}
	
}
