package tw.edu.ncu.softwareengineering.dodoio.collideObject;

public class Fertilizer extends Mob {
	public final int[][] breedTable = {{75, 300, 1}, {50, 600, 2}, {25, 1200, 4}};// breedTable[size][ability]
	
	
	public Fertilizer(Size inputSize) {
		
	}
	
	public enum Size{
		large,
		medium,
		small
	}
	
	public enum Ability{
		Radius,
		HP,
		Exp
	}

	@Override
	public void beAttacked(int damage) {
		// TODO Auto-generated method stub
		
	}
}
