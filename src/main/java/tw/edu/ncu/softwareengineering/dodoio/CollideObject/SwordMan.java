package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

public class SwordMan extends Character {

	public SwordMan(int setID, String setName, String setTeam,
			BufferedImage image, Position setPosition) {
		super(setID, setName, setTeam, image, setPosition);

		damagePoint+=80;
		healthPoint+=100;
		recoveryCD = recoveryCD*0.9;
	}

	@Override
	public void attack() {
		Thread attackThread = new Thread(new Runnable(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
