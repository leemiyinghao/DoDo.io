package tw.edu.ncu.softwareengineering.dodoio.collideObject;

import java.awt.image.BufferedImage;

public abstract class Character extends CollideObject {
	public final String name;
	public final String team;
	public final int[] expTable = {0, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
	private final int radius;
	public final CircleCollider circleCollider;
	private int deathmatchScore;
	private int level;
	int exp;
	int maxHP;
	int abilityPoint;
	double recoveryCD;
	double damagePoint;
	double attackCD;
	double skillCD;
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
		radius = 25;
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
	}
	
	/**call when get exp
	 * @param addExp
	 */
	void addExp(int addExp) {
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
	
	/**�I�ޯ��I HP +10
	 * 
	 */
	public void upgradeHP() {
		healthPoint+=10;
	}
	

	/**�I�ޯ��I �ˮ` +10
	 * 
	 */
	public void upgradeDP() {
		damagePoint+=10;
	}


	/**�I�ޯ��I �ޯ�CD�0.99��
	 * 
	 */
	public void upgradeCD() {
		skillCD = skillCD*0.99;
	}
	
	/**���o�Ө��⪺��������
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
	
	/**Get the CircleCollider collision checking
	 * 
	 * @return
	 */
	public CircleCollider getCollider() {
		return circleCollider;
	}
	
	public void attack() {
		//code for attack
	}
	
	public void skill() {
		//code for skill
	}
	
	/**Only player do "attack", ohters do "collide"
	 * 
	 * @param attacker
	 */
	public void beAttacked(Character attacker){
		this.beHarmed((int)attacker.damagePoint);
		
	}
	
}