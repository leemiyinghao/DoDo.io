package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

import java.util.ArrayList;
import java.util.TreeSet;

public class Utils {
    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    /**
     * check if the two circleCollider collides
     *
     * @param a a circle collider
     * @param b a circle collider
     * @return whether the two circle collider collides
     */
    private boolean checkCircleCollide(CircleCollider a, CircleCollider b) {
        //the distance that the two collider is collide
        double distance = Math.pow(a.getRadius() + b.getRadius(), 2);
        //the distance of the two collider's center
        double distance2 = Math.pow(b.position.getX() - a.position.getX(), 2) + Math.pow(b.position.getY() - a.position.getY(), 2);
        if (distance2 < distance)
            return true;
        else
            return false;
    }

    /**
     * check if a circle collider and a rectangle collider collide
     *
     * @param a a circle collider
     * @param b a rectangle collider
     * @return whether a circle collider and a rectangle collider collide
     */
    private boolean SAT(CircleCollider a, RectangleCollider b) {
        Vector box2circle = new Vector(b.position.getX() - a.position.getX(), b.position.getY() - a.position.getY());
        Vector box2circleUnit = box2circle.normalized();
        double max = Double.NEGATIVE_INFINITY;
        for (Point point : b.getPoints()) {
            Vector v = new Vector(point.x - b.position.getX(), point.y - b.position.getY());
            double curDP = v.dotProduct(box2circleUnit);
            if (curDP > max)
                max = curDP;
        }
        if (box2circle.magnitude() - max - a.getRadius() > 0 && box2circle.magnitude() > 0)
            return false;
        else
            return true;
    }

    /**
     * check if a rectangle collider and a circle collider collide
     *
     * @param a a rectangle collider
     * @param b a circle collider
     * @return whether a rectangle collider and a circle collider collide
     */
    private boolean SAT(RectangleCollider a, CircleCollider b) {
        Vector box2circle = new Vector(a.position.getX() - b.position.getX(), a.position.getY() - b.position.getY());
        Vector box2circleUnit = box2circle.normalized();
        double max = Double.NEGATIVE_INFINITY;
        for (Point point : a.getPoints()) {
            Vector v = new Vector(point.x - a.position.getX(), point.y - a.position.getY());
            double curDP = v.dotProduct(box2circleUnit);
            if (curDP > max)
                max = curDP;
        }
        if (box2circle.magnitude() - max - b.getRadius() > 0 && box2circle.magnitude() > 0)
            return false;
        else
            return true;
    }

    /**
     * check if the two rectangle collider collides
     *
     * @param a a rectangle collider
     * @param b a rectangle collider
     * @return whether the two rectangle collider collides
     */
    private boolean SAT(RectangleCollider a, RectangleCollider b) {
        TreeSet<Vector> axes = new TreeSet<>();
        for (int i = 0; i < 2; i++) {
            axes.add(new Vector(a.getPoints()[i + 1].x - a.getPoints()[i].x, a.getPoints()[i + 1].y - a.getPoints()[i].y).normal());
            axes.add(new Vector(b.getPoints()[i + 1].x - b.getPoints()[i].x, b.getPoints()[i + 1].y - b.getPoints()[i].y).normal());
        }
        for (Vector axis : axes) {
            double minProjA = new Vector(a.getPoints()[0].x, a.getPoints()[0].y).dotProduct(axis);
            double maxProjA = new Vector(a.getPoints()[0].x, a.getPoints()[0].y).dotProduct(axis);
            double minProjB = new Vector(a.getPoints()[0].x, a.getPoints()[0].y).dotProduct(axis);
            double maxProjB = new Vector(a.getPoints()[0].x, a.getPoints()[0].y).dotProduct(axis);
            for (int i = 1; i < 3; i++) {
                double curDPA = new Vector(a.getPoints()[i].x, a.getPoints()[i].y).dotProduct(axis);
                double curDPB = new Vector(b.getPoints()[i].x, b.getPoints()[i].y).dotProduct(axis);
                if (curDPA < minProjA)
                    minProjA = curDPA;
                else if (curDPA > maxProjA)
                    maxProjA = curDPA;
                if (curDPB < minProjB)
                    minProjB = curDPB;
                else if (curDPB > maxProjB)
                    maxProjB = curDPB;
            }
            if (maxProjB < minProjA || maxProjA < minProjB)
                return false;
        }
        return true;
    }

    /**
     * return whether the two CollideObject a and b collides.
     *
     * @param a a collide object
     * @param b acollide object
     * @return whether the two CollideObject a and b collides.
     */
    private boolean isCollide(CollideObject a, CollideObject b) {
        if (a.getCollider() instanceof CircleCollider && b.getCollider() instanceof CircleCollider)
            return checkCircleCollide(a.getCollider(), b.getCollider());
        else
            return SAT(a.getCollider(), b.getCollider());
    }

    /**
     * Calculate collision for all CollideObject in collideObjectManager's collideObjectList
     *
     * @param collideObjectManager the collide object manager
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
