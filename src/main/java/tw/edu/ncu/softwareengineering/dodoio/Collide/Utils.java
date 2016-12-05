package tw.edu.ncu.softwareengineering.dodoio.Collide;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;

public class Utils {
    private static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    /**return whether the two CollideObject a and b collides.
     * @param a
     * @param b
     * @return true if collides<br>
     * false else
     */
    private boolean isCollide(CollideObject a,CollideObject b)
    {
        return true;
    }

    /**Calculate collide for all CollideObject in collideObjectManager's collideObjectList and modify object's content.
     * @param collideObjectManager
     */
    public void CalculateCollide(CollideObjectManager collideObjectManager)
    {
        for(CollideObject collideObject:collideObjectManager.collideObjectList)
        {

        }
    }
}
