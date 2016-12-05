package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

public class Position {
	private int x;
	private int y;
	private double angle;
	
	public Position(int initialX, int initialY, double initialAngle) {
		setPositionXY(initialX, initialY);
		setDirection(initialAngle);
	}
	
	public void setPosition(int setX, int setY, double setAngle) {
		setPositionXY(setX, setY);
		setDirection(setAngle);
	}
	
	public void setPositionXY(int setX, int setY) {
		if(setY >= 0)
			x = setX;
		else
			x = 0;
		
		if(setY >= 0)
			y = setY;
		else
			y = 0;
	}
	
	public void setDirection(double setAngle) {
		if(setAngle >= 0 && setAngle < 1)
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
