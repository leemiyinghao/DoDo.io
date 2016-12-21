package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Arrow extends AttackObject {
    double traversal;
    int range;
    double arrowSpeed;
    int height = 50;
    int width = 4;
    int FPS = 20;// set for no error. this is not real

    public Arrow(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
        super(setID, cOManager, className, setPlayer);
        this.position = player.getPosition();
        damage = (int) player.damagePoint;
        range = (int) (player.getRadius() * 2 * 24);
        traversal = 0;
        arrowSpeed = player.getSpeed() * 4;
        collider = new RectangleCollider(setPosition, width, height);
        priority =0;
        Position.projection(player.getRadius(), setPosition);
        move(setPosition);
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
                traversal += (arrowSpeed / FPS);
                Position.projection(arrowSpeed, position);
                move(position);
            } else {
                dead();
                break;
            }
            updateTime -= 1000 / FPS;
        }
    }
}
