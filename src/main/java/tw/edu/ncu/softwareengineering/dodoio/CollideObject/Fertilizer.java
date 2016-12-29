package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager.collideObjecctClass;

public class Fertilizer extends Mob {
	public final int[][] breedTable = {{75, 300, 1}, {50, 600, 2}, {25, 1200, 4}};// breedTable[size][ability]
	public final int size;

	public Fertilizer(int setID, Position setPosition, CollideObjectManager cOManager,
			int className, Size setSize) {
		super(setID, setPosition, cOManager, className);
		// TODO Auto-generated constructor stub
		size = setSize.ordinal();
		if(setSize.equals(Size.large)){
			radius = breedTable[0][0];
			healthPoint = breedTable[0][1];
			exp = breedTable[0][2];
			className = collideObjecctClass.FertilizerBig.ordinal();
		}
		if(setSize.equals(Size.medium)){
			radius = breedTable[1][0];
			healthPoint = breedTable[1][1];
			exp = breedTable[1][2];
			className = collideObjecctClass.FertilizerMedium.ordinal();
		}
		if(setSize.equals(Size.small)){
			radius = breedTable[2][0];
			healthPoint = breedTable[2][1];
			exp = breedTable[2][2];
			className = collideObjecctClass.FertilizerSmall.ordinal();
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

	@Override
	public void update() {
		
	}

}
