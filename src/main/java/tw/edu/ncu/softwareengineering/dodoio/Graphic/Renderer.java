package tw.edu.ncu.softwareengineering.dodoio.Graphic;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by leemiyinghao on 2016/12/1.
 */
public class Renderer extends JFrame{

    private CollideObjectManager collideObjectManager;
    private Map map;
    private GameStat gameStat;
    private Character mainCharacter;
    private float[] windowContainSize = {50f, 20f};

    public Renderer(CollideObjectManager collideObjectManager, Map map){
        this.collideObjectManager = collideObjectManager;
        this.map = map;
    }
    public void render(int timeOffsetInMs){
        for(int i=0; i<collideObjectManager.collideObjectList.size();i++){
            printOnScreen((Image) collideObjectManager.collideObjectList[i].getImage(), collideObjectManager.collideObjectList[i].getPosition());
        }
    }
    public void controlUpdate(int timeOffsetInMs){

    }
    public void showMenu(){

    }
    public void setStat(GameStat gameStat){
        this.gameStat = gameStat;
    }
    public GameStat getStat(){
        return this.gameStat;
    }
    private Position getWindowCenter(){
        return mainCharacter.getPosition();
    }
    private void printOnScreen(Position position, Image image){
        float distence = (position.getX() - getWindowCenter().getX())^2 +
                         (position.getY() - getWindowCenter().getY())^2;
        if( (Math.abs(position.getX() - getWindowCenter().getX()) < windowContainSize[0]) &&
                (Math.abs(position.getX() - getWindowCenter().getX()) < windowContainSize[0]) ){
            Graphics g = this.getGraphics();
            g.drawImage(image,
                    (int)((position.getX() - windowContainSize[0])*Map.blockSize),
                    (int)((position.getY() - windowContainSize[0])*Map.blockSize),
                    this);
        }
    }
}