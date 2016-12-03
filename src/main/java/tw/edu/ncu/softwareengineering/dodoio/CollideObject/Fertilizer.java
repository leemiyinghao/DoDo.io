package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Fertilizer extends Mob {
	public final int[][] breedTable = {{75, 300, 1}, {50, 600, 2}, {25, 1200, 4}};// breedTable[size][ability]
	

	public Fertilizer(int inputID, BufferedImage image, Position setPosition, String setSize) {
		super(inputID, image, setPosition);
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
