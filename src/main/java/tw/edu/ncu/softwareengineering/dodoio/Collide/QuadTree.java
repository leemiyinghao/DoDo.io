package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;

import java.util.ArrayList;

public class QuadTree {
    private final int maxObjects = 10;
    private final int maxLevels = 5;
    private int level;
    private ArrayList<CollideObject> collideObjects;
    private Rectangle bounds;
    private QuadTree[] nodes;

    public QuadTree(int level, Rectangle bounds) {
        this.level = level;
        this.bounds = bounds;
        collideObjects = new ArrayList<>();
        nodes = new QuadTree[4];
    }
}
