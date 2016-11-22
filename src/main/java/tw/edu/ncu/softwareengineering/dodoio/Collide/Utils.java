package Collide;

public class Utils {
    private isCollide(ColideObject a, ColideObject b) {
        if (a.getCollider() instanceof CircleCollider) {
            if (b.getCollider() instanceof CircleCollider)
                return Math.pow((double)(a.getPosition().getX()-b.getPosition().getX()),2)+Math.pow((double)(a.getPosition().getY()-b.getPosition().getY()),2)<=Math.pow((double)(a.getCollider),2)
        }
    }


}
