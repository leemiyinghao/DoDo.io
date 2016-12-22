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
    private float[] windowContainSize = {30f, 9f};
    private Image overlayBasic;

    public Renderer(CollideObjectManager collideObjectManager, Map map){
        this.collideObjectManager = collideObjectManager;
    }
    public void render(int timeOffsetInMs){
        switch(getStat()){
            case MAINMENU:
                drawGameTypeMenu();
                break;
            case INGAME:
                drawInGameEntriesLayer();
                drawInGameOverlay();
                break;
        }
    }
    private Position getRealPositionByPercentage(float x, float y){
        return new Position((int)(x*this.getWidth()),(int)(y*this.getHeight()), 0);
    }
    private void drawGameTypeMenu(){

    }
    private void drawInGameOverlay(){
        Graphics g = this.getGraphics();
        g.drawImage(this.overlayBasic, 0, 0, this.getWidth(), this.getHeight(), Color.BLACK, this);
        //draw HP
        Position startPos = getRealPositionByPercentage(0.03f, 0.93f);
        Position duraPos = getRealPositionByPercentage(0.04f, 0.02f);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(startPos.getX(), startPos.getY(), duraPos.getX(), duraPos.getY());
        g.setColor(Color.RED);
        try {
            g.fillRect(startPos.getX(), startPos.getY(), duraPos.getX()*collideObjectManager.getMyPlayer().getHP(), duraPos.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO: draw RankList, maybe
    }
    private void drawInGameEntriesLayer(){
        for (CollideObject object: collideObjectManager.collideObjectList) {
            printOnScreen(object.getPosition(), (Image)object.getImage(collideObjectManager));
        }
    }
    private void drawResult(){

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
