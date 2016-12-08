package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

import java.util.ArrayList;

public class Utils {
    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    /**
     * check if the two circleCollider is collide
     *
     * @param a
     * @param b
     * @return
     */
    private boolean checkCircleCollide(CircleCollider a, CircleCollider b) {
        //the distance that the two collider is collide
        double distance = Math.pow(a.getRadius() + b.getRadius(), 2);
        //the distance of the two collider's center
        double distance2 = Math.pow(b.getPosition().getX() - a.getPosition().getX(), 2) + Math.pow(b.getPosition().getY() - a.getPosition().getY(), 2);
        if (distance2 <= distance)
            return true;
        else
            return false;
    }

    private boolean SAT(CircleCollider a, RectangleCollider b) {

    }

    private boolean SAT(RectangleCollider a, CircleCollider b) {

    }

    private boolean SAT(RectangleCollider a, RectangleCollider b) {

    }

    /**
     * return whether the two CollideObject a and b collides.
     *
     * @param a
     * @param b
     */
    private boolean isCollide(CollideObject a, CollideObject b) {
        if (a.getCollider() instanceof CircleCollider && b.getCollider() instanceof CircleCollider)
            return checkCircleCollide(a.getCollider(), b.getCollider());
        else
            return SAT(a.getCollider(), b.getCollider());
    }

    /**
     * Calculate collide for all CollideObject in collideObjectManager's collideObjectList and modify object's content.
     *
     * @param collideObjectManager
     */
    public void CalculateCollide(CollideObjectManager collideObjectManager, Bound bound) {
        QuadTree quadtree = new QuadTree(0, bound);
        //insert all collideObjects to quadtree
        for (CollideObject collideObject : collideObjectManager.collideObjectList) {
            quadtree.insert(collideObject);
        }
        //iterate all collideObjects and check if there is a collision
        for (CollideObject collideObject : collideObjectManager.collideObjectList) {
            ArrayList<CollideObject> list = new ArrayList<>();
            quadtree.retrieve(list, collideObject);
            for (CollideObject collideObject2 : list) {
                if (collideObject2 != collideObject && isCollide(collideObject, collideObject2)) {
                    collideObject.onCollide(collideObject2);
                    collideObject2.onCollide(collideObject);
                }
            }
        }
    }
}
