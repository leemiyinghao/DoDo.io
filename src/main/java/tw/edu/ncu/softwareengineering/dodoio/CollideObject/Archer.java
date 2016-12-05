package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class Archer extends Character {
	int range;
	
	
	public Archer(int setID, String setName, String setTeam,
			BufferedImage image, Position setPosition) {
		super(setID, setName, setTeam, image, setPosition);
		attackCD = attackCD*0.8;
		speed = speed*1.2;
		skillCD = 11;
		range = (int) (speed*5*5);//這裡的5應當是格子的大小，speed指的是 1 frame的移動pixel
	}

	@Override
	public void attack() {
		Thread attackThread = new Thread(new Runnable(){
			
			@Override
			public void run() {
				if(attackActive){
					//shoot arrow
					attackActive = false;
					try {
						Thread.sleep((long) (attackCD*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					attackActive = true;
				}
			}
		});
		attackThread.start();
	}

	@Override
	public void skill() {

		Thread skillThread = new Thread(new Runnable(){
			
			@Override
			public void run() {
				if(skillActive){
					//shoot arrow
					skillActive = false;
					try {
						Thread.sleep((long) (skillCD*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					skillActive = true;
				}
			}
		});
		skillThread.start();
	}

}
