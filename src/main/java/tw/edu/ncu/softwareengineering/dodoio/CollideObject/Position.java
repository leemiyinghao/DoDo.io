package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class Position {
	private int x;
	private int y;
	private double angle;
	
	public Position(int initialX, int initialY, double initialAngle) {
		if(x >= 0)
			x = initialX;
		else
			x = 0;
		
		if(y >= 0)
			y = initialY;
		else
			y = 0;

		if(angle >= 0 && angle < 1)
			angle = initialAngle;
		else
			angle = 0;
	}
	
	public void setPosition(int setX, int setY, double setAngle) {
		if(x >= 0)
			x = setX;
		else
			x = 0;
		
		if(y >= 0)
			y = setY;
		else
			y = 0;

		if(angle >= 0 && angle < 1)
			angle = setAngle;
		else
			angle = 0;
	}
	
	public double getDirection() {
		return angle;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
