package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

public class RectangleCollider implements ICollider {
    private int leftTopX, leftTopY, rightBottomX, rightBottomY;
    private Position position;

    public RectangleCollider(Position position, int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        this.position = position;
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.rightBottomX = rightBottomX;
        this.rightBottomY = rightBottomY;
    }

    public int getLeftTopX() {
        return leftTopX;
    }

    public int getLeftTopY() {
        return leftTopY;
    }

    public int getRightBottomX() {
        return rightBottomX;
    }

    public int getRightBottomY() {
        return rightBottomY;
    }

    public Position getPosition() {
        return position;
    }
}
