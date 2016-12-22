package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;
import tw.edu.ncu.softwareengineering.dodoio.Collide.Vector;

public abstract class Character extends CollideObject {
    public final String name;
    public final int team;
    public final int levelMax = 30;
    public final int[] expTable = {0, 2, 2, 2, 2, 4, 4, 4, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    int[] expAccumulationTable;
    private final int radius = 25;
    private int deathmatchScore;
    int level;
    int exp;
    int maxHP;
    private int abilityPoint;
    private long oldTime;
    double recoveryCD;
    double recoveryPointBuffer;
    double damagePoint;
    double attackCD;
    double attackCountDown;
    boolean attackActive;
    double skillCD;
    double skillCountDown;
    boolean skillActive;
    double speed;

    /**
     * Set the data of player
     * initialize the abilities of player
     *
     * @param setID
     * @param setName
     * @param setTeam
     * @param image
     * @param setPosition
     */
    public Character(int setID, String setName, TeamName setTeam, Position setPosition, CollideObjectManager cOManager, int className) {
        super(setID, setPosition, cOManager, className);
        oldTime = date.getTime();

        name = setName;
        team = setTeam.ordinal();

        deathmatchScore = 0;
        level = 1;
        maxHP = 200;
        healthPoint = maxHP;
        recoveryPointBuffer = 0;
        recoveryCD = 2;
        damagePoint = 100;
        speed = 5;
        collider = new CircleCollider(position, radius);

        attackCD = 1;
        attackCountDown = 0;
        attackActive = true;

        skillCD = 10;
        skillCountDown = 0;
        skillActive = true;

        expAccumulationTable = new int[levelMax];
        expAccumulationTable[0] = expTable[0];
        for (int i = 1; i < levelMax; i++) {
            expAccumulationTable[i] = expAccumulationTable[i - 1] + expTable[i];
        }
    }

    /**
     * The update function for character
     * update :
     * recovery
     * count skill CD
     * count attack CD
     */
    @Override
    public void move(Position nextPosition) {
        position = nextPosition;
        collider.update(nextPosition);
        client.update();
    }

    ;

    @Override
    public void update() {
        long newTime = date.getTime();
        long updateTime = newTime - oldTime;
        recover(updateTime);
        countAttackCD(updateTime);
        countSkillCD(updateTime);
        oldTime = newTime;
        // client.update()
    }

    void recover(long updateTime) {
        while (updateTime > 0) {
            if (healthPoint >= maxHP) {
                break;
            }
            if (updateTime / 1000 >= recoveryCD) {
                healthPoint++;
                updateTime = (long) (updateTime - recoveryCD * 1000);
            } else
                break;
        }
        double addTime = updateTime / 1000.0;
        recoveryPointBuffer += (addTime);

        if (recoveryPointBuffer >= recoveryCD) {
            recoveryPointBuffer -= recoveryCD;
            healthPoint++;
        }

        if (healthPoint >= maxHP) {
            healthPoint = maxHP;
            recoveryPointBuffer = 0;
        }
    }

    void countAttackCD(long updateTime) {
        double updateTimeSecond = (updateTime / 1000.0);
        if (!attackActive) {
            if (attackCountDown - updateTimeSecond <= 0) {
                attackCountDown = 0;
                attackActive = true;
            } else {
                attackCountDown -= updateTimeSecond;
            }
        }
    }

    void countSkillCD(long updateTime) {
        double updateTimeSecond = (updateTime / 1000.0);
        if (!skillActive) {

            if (skillCountDown - updateTimeSecond <= 0) {
                skillCountDown = 0;
                skillActive = true;
            } else {
                skillCountDown -= updateTimeSecond;
            }
        }
    }

    /**
     * call when get exp(CDC)
     *
     * @param addExp
     */
    void addExp(int addExp) {
        exp += addExp;
        levelUp();
    }

    /**
     * call this when get exp
     */
    void levelUp() {
        while (exp > expTable[level] && level < levelMax) {
            exp -= expTable[level];
            level++;
            maxHP++;
            healthPoint = maxHP;
            recoveryCD = recoveryCD * 0.99;
            damagePoint++;
            abilityPoint++;

        }
    }

    /**
     * for death match game(server call only)
     * call when player get score
     *
     * @param addScore
     */
    void addDMScore(int addScore) {
        deathmatchScore += addScore;
    }

    /**
     * HP +10
     * <p>
     * Exception: Run out of abilityPoint.
     *
     * @throws Exception Run out of abilityPoint.
     */
    public void upgradeHP() throws Exception {
        if (abilityPoint <= 0)
            throw new Exception("Exception: Run out of abilityPoint.");
        healthPoint += 10;
        abilityPoint--;
        client.update();
    }


    /**
     * damagePoint+10
     *
     * @throws Exception Run out of abilityPoint.
     */
    public void upgradeDP() throws Exception {
        if (abilityPoint <= 0)
            throw new Exception("Exception: Run out of abilityPoint.");
        damagePoint += 10;
        abilityPoint--;
        client.update();
    }


    /**
     * skillCD *0.99
     *
     * @throws Exception Run out of abilityPoint.
     */
    public void upgradeCD() throws Exception {
        if (abilityPoint <= 0)
            throw new Exception("Exception: Run out of abilityPoint.");
        skillCD = skillCD * 0.99;
        abilityPoint--;
        client.update();
    }

    /**
     * tell server you do attack
     *
     * @param setID
     * @return
     */
    public void attack() {
        if (attackActive) {
            attackActive = false;
            attackCountDown = attackCD;
            // tell server you do attack
        }
    }

    /**
     * tell server you do attack
     *
     * @param setID
     * @return
     */
    public void skill() {
        if (skillActive) {
            skillActive = false;
            skillCountDown = skillCD;
            // tell server you do skill
        }
    }

    /**
     * Only player do "attack" by using attack or skill, others do "collide"
     * (server call only)
     *
     * @param attacker
     */
    public void beAttacked(CollideObject attacker) {
        if (attacker instanceof AttackObject) {
            this.beHarmed(((AttackObject) attacker).damage);
            if (this.isDead()) {
                ((AttackObject) attacker).player.addDMScore(this.level);
            }
        } else if (attacker instanceof Character) {
            this.beHarmed(attacker.collideDamage);
            if (this.isDead()) {
                ((Character) attacker).addDMScore(this.level);
            }
        } else
            this.beHarmed(attacker.collideDamage);
    }

    /**
     * for death match
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
     *
     * @return
     */
    public int getTeam() {
        return team;
    }

    public double getSpeed() {
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

    @Override
    public void onCollide(CollideObject whichObjectCollideThis) {
        if(whichObjectCollideThis.isDead())
            return;
        if (whichObjectCollideThis instanceof AttackObject) {
            if (!(((AttackObject) whichObjectCollideThis).player.team == this.team))
                this.beAttacked(whichObjectCollideThis);
        } else if (whichObjectCollideThis instanceof Character) {
            if (!(((Character) whichObjectCollideThis).team == this.team))
                this.beAttacked(whichObjectCollideThis);
            //the unit vector of whichObject to this object
            Vector v = new Vector(this.position.getX() - whichObjectCollideThis.position.getX(), this.position.getY() - whichObjectCollideThis.position.getY()).normalized();
            //the relative speed;
            double speed = ((Character) whichObjectCollideThis).speed - this.speed;
            if (speed >= 0)
                this.move(new Position((int) (5 * v.getX()), (int) (5 * v.getY()), this.position.getDirection()));
        } else {
            this.beAttacked(whichObjectCollideThis);
            Vector v = new Vector(this.position.getX() - whichObjectCollideThis.position.getX(), this.position.getY() - whichObjectCollideThis.position.getY()).normalized();
            this.move(new Position((int) (5 * v.getX()), (int) (5 * v.getY()), this.position.getDirection()));
        }
    }
}
