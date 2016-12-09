package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class Wanderer extends Mob{
	private int speed = 1;
	private int direction = 0;
	
	public Wanderer(int inputID, BufferedImage image, Position setPosition) {
		super(inputID, image, setPosition);
		healthPoint = 1200;
		exp = 4;
		radius = 120;
		collider = new CircleCollider(setPosition, radius);
		startWander();
	}
	
	void startWander(){
		Thread wanderThread = new Thread(new Runnable(){

			@Override
			public void run() {
				
				while(!isDead()){
					try {
						direction = (int) (Math.random()*10%4);
						wander();
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		});
		wanderThread.start();
	}
	
	void wander(){
		if(direction == 0){
			position.setPosition(position.getX() + speed, position.getY(), position.getDirection());
		}
		if(direction == 1){
			position.setPosition(position.getX(), position.getY() + speed, position.getDirection());
		}
		if(direction == 2){
			position.setPosition(position.getX() - speed, position.getY(), position.getDirection());
		}
		if(direction == 3){
			position.setPosition(position.getX(), position.getY() - speed, position.getDirection());
		}
	}
	
	public int getSpeed(){
		return speed;
	}

}
