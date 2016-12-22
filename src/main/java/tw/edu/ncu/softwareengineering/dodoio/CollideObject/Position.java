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
		if(setX >= 0)
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
	

	/**
	 * input the distance, the object will know where do it move to(in straight line)
	 * 
	 * @param setDistance How far you want to move
	 * @param position from which point, in what direction
	 */

	static void projection(double setDistance, Position position){
		double x = (Math.cos(position.getDirection()*(2*Math.PI)))*setDistance;
		double y = (Math.sin(position.getDirection()*(2*Math.PI)))*setDistance;
		position.setPositionXY((int)(position.getX()+x), (int)(position.getY()+y));
	}
	
}
