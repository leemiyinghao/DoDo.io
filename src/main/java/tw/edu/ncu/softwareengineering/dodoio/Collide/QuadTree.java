package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    private final int maxObjects = 10;
    private final int maxLevels = 5;
    private int level;
    private ArrayList<CollideObject> collideObjects;
    private Bound bound;
    private QuadTree[] nodes;

    /**
     * create a quadtree with level and bound
     *
     * @param level the level of the quadtree
     * @param bound the bound of the quadtree
     */
    public QuadTree(int level, Bound bound) {
        this.level = level;
        this.bound = bound;
        collideObjects = new ArrayList<>();
        nodes = new QuadTree[4];
    }

    /**
     * Clears the quadtree
     */
    public void clear() {
        collideObjects.clear();
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }

    /**
     * Splits the node into 4 subnodes
     */
    private void split() {
        int subWidth = (int) (bound.getWidth() / 2);
        int subHeight = (int) (bound.getHeight() / 2);
        int x = bound.getX();
        int y = bound.getY();
        nodes[0] = new QuadTree(level + 1, new Bound(x + subWidth, y, subWidth, subHeight));
        nodes[1] = new QuadTree(level + 1, new Bound(x, y, subWidth, subHeight));
        nodes[2] = new QuadTree(level + 1, new Bound(x, y + subHeight, subWidth, subHeight));
        nodes[3] = new QuadTree(level + 1, new Bound(x + subWidth, y + subHeight, subWidth, subHeight));
    }

    /**
     * get the index of a circleCollider
     *
     * @param collider           a circle collider
     * @param horizontalMidpoint the horizontalMidpoint of the bound
     * @param verticalMidpoint   the verticalMidpoint of the bound
     * @return the quadrant  which the collider is in, If the collider isn't in any quadrant return -1
     */
    private int getCircleIndex(CircleCollider collider, double horizontalMidpoint, double verticalMidpoint) {
        int index = -1;
        int x = collider.position.getX();
        int y = collider.position.getY();
        int radius = collider.getRadius();
        // Object can completely fit within the top quadrants
        boolean topQuadrant = y < verticalMidpoint && y + radius < verticalMidpoint;
        // Object can completely fit within the bottom quadrants
        boolean bottomQuadrant = y > verticalMidpoint;
        // Object can completely fit within the left quadrants
        if (x < horizontalMidpoint && x + radius < horizontalMidpoint)
            if (topQuadrant)
                index = 1;
            else if (bottomQuadrant)
                index = 2;
                // Object can completely fit within the right quadrants
            else if (x > horizontalMidpoint)
                if (topQuadrant)
                    index = 0;
                else if (bottomQuadrant)
                    index = 3;
        return index;
    }

    /**
     * get the index of a rectangleCollider
     *
     * @param collider           a rectangle collider
     * @param horizontalMidpoint the horizontalMidpoint of the bound
     * @param verticalMidpoint   the verticalMidpoint of the bound
     * @return the quadrant  which the collider is in, If the collider isn't in any quadrant return -1
     */
    private int getRectangleIndex(RectangleCollider collider, double horizontalMidpoint, double verticalMidpoint) {
        int index = -1;
        Point[] points = collider.getPoints();
        boolean topQuadrant = false;
        boolean bottomQuadrant = false;
        boolean leftQuadrant = false;
        boolean rightQuadrant = false;
        // Object can completely fit within the top quadrants
        for (int i = 0; i < points.length; i++) {
            topQuadrant = points[i].y < verticalMidpoint;
            if (!topQuadrant)
                break;
        }
        // Object can completely fit within the bottom quadrants
        for (int i = 0; i < points.length; i++) {
            bottomQuadrant = points[i].y > verticalMidpoint;
            if (!bottomQuadrant)
                break;
        }
        // Object can completely fit within the left quadrants
        for (int i = 0; i < points.length; i++) {
            leftQuadrant = points[i].x < horizontalMidpoint;
            if (!leftQuadrant)
                break;
        }
        //// Object can completely fit within the right quadrants
        for (int i = 0; i < points.length; i++) {
            rightQuadrant = points[i].x > horizontalMidpoint;
            if (!rightQuadrant)
                break;
        }
        if (leftQuadrant) {
            if (topQuadrant)
                index = 1;
            else if (bottomQuadrant)
                index = 2;
        } else if (rightQuadrant) {
            if (topQuadrant)
                index = 0;
            else if (bottomQuadrant)
                index = 3;
        }
        return index;
    }

    /**
     * Determine which node the object belongs to. -1 means
     * object cannot completely fit within a child node and is part
     * of the parent node
     *
     * @param collideObject the collide object to be calc
     */
    private int getIndex(CollideObject collideObject) {
        double horizontalMidpoint = bound.getX() + (bound.getWidth() / 2);
        double verticalMidpoint = bound.getY() + (bound.getHeight() / 2);
        if (collideObject.getCollider() instanceof CircleCollider)
            return getCircleIndex((CircleCollider) collideObject.getCollider(), horizontalMidpoint, verticalMidpoint);
        else
            return getRectangleIndex((RectangleCollider) collideObject.getCollider(), horizontalMidpoint, verticalMidpoint);
    }

    /**
     * Insert the object into the quadtree. If the node
     * exceeds the capacity, it will split and add all
     * objects to their corresponding nodes.
     *
     * @param collideObject the collide object to be insert
     */
    public void insert(CollideObject collideObject) {
        if (nodes[0] != null) {
            int index = getIndex(collideObject);

            if (index != -1) {
                nodes[index].insert(collideObject);

                return;
            }
        }

        collideObjects.add(collideObject);

        if (collideObjects.size() > maxObjects && level < maxLevels) {
            if (nodes[0] == null) {
                split();
            }

            int i = 0;
            while (i < collideObjects.size()) {
                int index = getIndex(collideObjects.get(i));
                if (index != -1) {
                    nodes[index].insert(collideObjects.remove(i));
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * Return all objects that could collide with the given object
     *
     * @param returnObjects a list  use to store result
     * @param collideObject the collide object you want to get
     */
    public List retrieve(List returnObjects, CollideObject collideObject) {
        int index = getIndex(collideObject);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, collideObject);
        }
        returnObjects.addAll(collideObjects);
        return returnObjects;
    }
}
