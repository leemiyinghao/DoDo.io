package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.CircleCollider;

public class MagicBall extends AttackObject {
    int radius;
    int range;
    double traversal;
    double ballSpeed;
    int FPS = 20;//隨便設定的

    public MagicBall(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
        super(setID, cOManager, className, setPlayer);
        this.position = player.getPosition();
        collider = new CircleCollider(position, radius);
        ballSpeed = player.getSpeed() * 3;
        radius = 12;
        traversal = 0;
        range = (int) (player.getRadius() * 2 * 12);// maybe the range is for the arrow fly for 5 seconds
        damage = (int) player.damagePoint;
        priority =1;
    }

    @Override
    public void move(Position nextPosition) {
        position = nextPosition;
        collider.update(nextPosition);
    }

    ;

    @Override
    public void update() {
        long newTime = date.getTime();
        fly(newTime - oldTime);
        oldTime = newTime;
    }

    /**
     * move the arrow by the speed and updateTime
     * fly through until its traversal out of range
     *
     * @param updateTime the time past between the time of last update and the time of the update now
     */
    void fly(long updateTime) {
        while (updateTime > 0) {
            if (traversal <= range && !isDead()) {
                traversal += ballSpeed / FPS;
                Position.projection(ballSpeed, position);
                move(position);
            } else {
                dead();
                break;
            }
            updateTime -= 1000 / FPS;
        }
    }
}
