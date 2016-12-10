package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class Fertilizer extends Mob {
	public final int[][] breedTable = {{75, 300, 1}, {50, 600, 2}, {25, 1200, 4}};// breedTable[size][ability]
	

	public Fertilizer(int setID, String setName, String setTeam,
			Position setPosition, CollideObjectManager cOManager, int className, Size setSize) {
		super(setID, setName, setTeam, setPosition, cOManager, className);
		// TODO Auto-generated constructor stub
		if(setSize.equals(Size.large)){
			radius = breedTable[0][0];
			healthPoint = breedTable[0][1];
			exp = breedTable[0][2];
		}
		if(setSize.equals(Size.medium)){
			radius = breedTable[1][0];
			healthPoint = breedTable[1][1];
			exp = breedTable[1][2];
		}
		if(setSize.equals(Size.small)){
			radius = breedTable[2][0];
			healthPoint = breedTable[2][1];
			exp = breedTable[2][2];
		}
		
		collider = new CircleCollider(setPosition, radius);
	}

	
	public static enum Size{
		large,
		medium,
		small
	}
	
	public static enum Ability{
		Radius,
		HP,
		Exp
	}

}
