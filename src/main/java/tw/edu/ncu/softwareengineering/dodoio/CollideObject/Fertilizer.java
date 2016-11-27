package tw.edu.ncu.softwareengineering.dodoio.collideObject;

public class Fertilizer extends Mob {
	int radius;
	
	public Fertilizer(String inputSize) {
		if(inputSize.equals("large")) {
			
			radius = 75;
		}
		else if(inputSize.equals("medium")) {
			radius = 50;
		}
		else if(inputSize.equals("small")) {
			radius = 25;
		}
		else {
			radius = 25;
		}
	}

	@Override
	public void beAttacked(int damage) {
		// TODO Auto-generated method stub
		
	}
}
