package tw.edu.ncu.softwareengineering.dodoio.CollideObject;

import tw.edu.ncu.softwareengineering.dodoio.Collide.RectangleCollider;

public class Slash extends AttackObject {
    int height = 35;
    int width = 25;
    int FPS = 20;// set for no error. this is not real
    double slashTime = 0.5;

    /**
     * move the slash region out of swordman's body
     *
     * @param inputID
     * @param image
     * @param setPosition
     * @param setPlayer
     */
    public Slash(int setID, Position setPosition, CollideObjectManager cOManager, int className, Character setPlayer) {
        super(setID, cOManager, className, setPlayer);
        this.position = player.getPosition();
        player = setPlayer;
        collider = new RectangleCollider(setPosition, width, height);
        isInvincible = true;
        Position.projection(player.getRadius(), setPosition);
        move(position);
        damage = (int) player.damagePoint;
        priority =2;
    }

    @Override
    public void move(Position nextPosition) {
        position = nextPosition;
        collider.update(nextPosition);
    }

    ;

    /**
     * update it slash time, let it dead when run out of slashTime
     */
    @Override
    public void update() {
        long newTime = date.getTime();
        long updateTime = oldTime - newTime;
        while (updateTime >= 0) {
            if (!isDead() && slashTime > 0) {
                slashTime -= 1000 / FPS;
            } else {
                dead();
                break;
            }
            updateTime -= 1000 / FPS;
        }
    }
}
