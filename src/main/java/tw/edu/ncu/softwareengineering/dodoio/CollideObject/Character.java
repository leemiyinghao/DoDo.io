package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public abstract class Character extends CollideObject {
	public final String name;
	public final String team;
	public final int[] expTable = {0, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
	private final int radius = 25;
	public final CircleCollider circleCollider;
	private int deathmatchScore;
	private int level;
	private int exp;
	private int maxHP;
	private int abilityPoint;
	double recoveryCD;
	double damagePoint;
	double attackCD;
	double attackCountDown;
	double skillCD;
	double skillCDCountDown;
	double speed;
	boolean attackActive = true, skillActive = true;
	
	/**Set the data of player
	 * initialize the abilities of player
	 * 
	 * @param setID
	 * @param setName
	 * @param setTeam
	 * @param image
	 * @param setPosition
	 */
	public Character(int setID, String setName, String setTeam, BufferedImage image, Position setPosition) {
		super(setID, image, setPosition);
		name = setName;
		team = setTeam;

		deathmatchScore = 0;
		level = 1;
		maxHP = 200;
		healthPoint = maxHP;
		recoveryCD = 2;
		damagePoint = 100;
		attackCD = 1;
		skillCD = 10;
		speed = 1;
		circleCollider = new CircleCollider(position, radius);
		
		Thread recoveryThread = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!isDead()){
					try {
						if(healthPoint < maxHP){
							healthPoint++;
							//Update
						}
						
						Thread.sleep((long) (recoveryCD*1000));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		recoveryThread.start();
	}
	
	/**call when get exp
	 * @param addExp
	 */
	public void addExp(int addExp) {
		exp+=addExp;
		levelUp();
	}
	
	/**call this when get exp
	 */
	void levelUp() {
		while(exp < expTable[level]) {
			exp -= expTable[level];
			level++;
			maxHP++;
			healthPoint = maxHP;
			recoveryCD = recoveryCD*0.99;
			damagePoint++;
			abilityPoint++;
			
		}
	}
	
	/**for death match game
	 * call when player get score
	 * 
	 * @param addScore
	 */
	void addDMScore(int addScore) {
		deathmatchScore+=addScore;
	}
	
	/**點技能點 HP +10
	 * 
	 * Exception: Run out of abilityPoint.
	 */
	public void upgradeHP() {
		try{
			if(abilityPoint <= 0)
				throw new Exception();
			healthPoint+=10;
			abilityPoint--;
		}
		catch(Exception excp) {
			System.out.println("Exception: Run out of abilityPoint.");
			return;
		}
	}
	

	/**點技能點 傷害 +10
	 * 
	 */
	public void upgradeDP() {
		try{
			if(abilityPoint <= 0)
				throw new Exception();
			damagePoint+=10;
			abilityPoint--;
		}
		catch(Exception excp) {
			System.out.println("Exception: Run out of abilityPoint.");
			return;
		}
	}


	/**點技能點 技能CD減為0.99倍
	 * 
	 */
	public void upgradeCD() {
		try{
			if(abilityPoint <= 0)
				throw new Exception();
			skillCD = skillCD*0.99;
			abilityPoint--;
		}
		catch(Exception excp) {
			System.out.println("Exception: Run out of abilityPoint.");
			return;
		}
	}
	
	public abstract void attack();
	
	public abstract void skill();
	
	/**Only player do "attack" by using attack or skill, others do "collide"
	 * 
	 * @param attacker
	 */
	public void beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
		if(this.isDead()) {
			didKill(attacker);
		}
		//update data to server
	}
	
	/**done everything when the character kill player
	 * 
	 * @param attacker
	 */
	private void didKill(Character attacker) {
		attacker.addDMScore(this.level);
	}
	
	/**取得該角色的死鬥分數
	 * 
	 * @return
	 */
	public int getDMScore() {
		return deathmatchScore;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public String getTeam() {
		return team;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public int getAbilityPoint() {
		return abilityPoint;
	}
	
	/**Get the CircleCollider collision checking
	 * 
	 * @return
	 */
	public CircleCollider getCollider() {
		return circleCollider;
	}
}
