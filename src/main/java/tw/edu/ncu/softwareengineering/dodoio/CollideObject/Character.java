package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import java.awt.image.BufferedImage;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public abstract class Character extends CollideObject {
	public final String name;
	public final String team;
	public final int[] expTable = {0, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
	private final int radius = 25;
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
		collider = new CircleCollider(position, radius);
		// 不用這個Thread算，在manager做
		Thread recoveryThread = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!isDead()){
					try {
						Thread.sleep((long) (recoveryCD*1000));
						if(healthPoint < maxHP){
							healthPoint++;
							//Update
						}
						
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
		// update to client
	}
	
	/**for death match game
	 * call when player get score
	 * 
	 * @param addScore
	 */
	void addDMScore(int addScore) {
		deathmatchScore+=addScore;
	}
	
	/**HP +10
	 * 
	 * Exception: Run out of abilityPoint.
	 * @throws Exception Run out of abilityPoint.
	 */
	public void upgradeHP() throws Exception {
		if(abilityPoint <= 0)
			throw new Exception("Exception: Run out of abilityPoint.");
		healthPoint+=10;
		abilityPoint--;
	}
	

	/**damagePoint+10
	 * @throws Exception Run out of abilityPoint.
	 * 
	 */
	public void upgradeDP() throws Exception {
		if(abilityPoint <= 0)
			throw new Exception("Exception: Run out of abilityPoint.");
		damagePoint+=10;
		abilityPoint--;
	}


	/**skillCD *0.99
	 * @throws Exception Run out of abilityPoint.
	 * 
	 */
	public void upgradeCD() throws Exception {
		if(abilityPoint <= 0)
			throw new Exception("Exception: Run out of abilityPoint.");
		skillCD = skillCD*0.99;
		abilityPoint--;
	}
	
	/**
	 * tell server you do attack
	 * @param setID
	 * @return
	 */
	abstract AttackObject attack(int setID);
	
	/**
	 * tell server you do attack
	 * @param setID
	 * @return
	 */
	abstract AttackObject skill(int setID);
	
	/**Only player do "attack" by using attack or skill, others do "collide"
	 * 
	 * @param attacker
	 */
	public void beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
		if(this.isDead()) {
			attacker.addDMScore(this.level);
		}
	}
	
	/**for death match
	 * 
	 * @return
	 */
	public int getDMScore() {
		return deathmatchScore;
	}
	
	public int getRadius() {
		return radius;
	}
	
	/**
	 * get team name
	 * @return
	 */
	public String getTeam() {
		return team;
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public int getAbilityPoint() {
		return abilityPoint;
	}
	
	public int getLevel() {
		return level;
	}
	
	public static enum ChracterClass {
		Archer,
		Magician,
		SwordMan
	}
	
	public static enum TeamName {
		deathMatch,
		teamBlue,
		teamRed
	}
}
