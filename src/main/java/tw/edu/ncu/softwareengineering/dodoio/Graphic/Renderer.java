package tw.edu.ncu.softwareengineering.dodoio.Graphic;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;
import tw.edu.ncu.softwareengineering.dodoio.Game.*;

import javax.swing.*;
import java.awt.*;

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
    private Image menuBasic;
    private Control control;
    public Game game;

    public Renderer(CollideObjectManager collideObjectManager, Map map){
        this.collideObjectManager = collideObjectManager;
        this.control = new Control(this.collideObjectManager, this);
        //add menu button stuff
        Button startDeathMatchBtn = new StartDeathMatchBtn(this);
        startDeathMatchBtn.position = getRealPositionByPercentage(0.2f, 0.35f);
        startDeathMatchBtn.size = getRealPositionByPercentage(0.6f, 0.1f);
        this.control.addMenuBtn(startDeathMatchBtn);
        Button startKingKillBtn = new StartKingKillBtn(this);
        startKingKillBtn.position = getRealPositionByPercentage(0.3f, 0.35f);
        startKingKillBtn.size = getRealPositionByPercentage(0.6f, 0.1f);
        this.control.addMenuBtn(startKingKillBtn);
        //add ingame button stuff
        Button upgradeDP = new UpgradeDPBtn(this.collideObjectManager);
        upgradeDP.position = getRealPositionByPercentage(0f, 0.9f);
        upgradeDP.size = getRealPositionByPercentage(0.04f, 0.02f);
        this.control.addInGameBtn(upgradeDP);
        Button upgradeHP = new UpgradeHPBtn(this.collideObjectManager);
        upgradeHP.position = getRealPositionByPercentage(0f, 0.96f);
        upgradeHP.size = getRealPositionByPercentage(0.04f, 0.02f);
        this.control.addInGameBtn(upgradeHP);
    }
    public void render(int timeOffsetInMs){
        switch(getStat()){
            case MAINMENU:
                drawGameTypeMenu();
                break;
            case INGAME:
                int result = this.game.update();
                switch(result){
                    case 1:
                        this.setStat(GameStat.RESULT_LOSE);
                        break;
                    case 2:
                        this.setStat(GameStat.RESULT_WIN);
                        break;
                }
                drawInGameEntriesLayer();
                drawInGameOverlay();
                break;
        }
    }
    private Position getRealPositionByPercentage(float x, float y){
        return new Position((int)(x*this.getWidth()),(int)(y*this.getHeight()), 0);
    }
    private void drawGameTypeMenu(){
        Graphics g = this.getGraphics();
        g.drawImage(this.menuBasic, 0, 0, this.getWidth(), this.getHeight(), Color.BLACK, this);
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
    private void drawResult() {
        Graphics g = this.getGraphics();
        Position pos = getRealPositionByPercentage(0.2f, 0.35f);
        Position size = getRealPositionByPercentage(0.6f, 0.3f);
        try {
            if (collideObjectManager.getMyPlayer().getHP() == 0) { //player die
                g.drawChars("You die.".toCharArray(), 0, 0, size.getX(), size.getY());
            }else if(this.game instanceof KingKill) { //kingkill mode
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
class StartDeathMatchBtn extends Button{
    private Renderer renderer;
    public StartDeathMatchBtn(Renderer renderer){
        this.renderer = renderer;
    }
    @Override
    public void onClick(){
        renderer.game = new DeathMatch();
        renderer.setStat(GameStat.INGAME);
    }
}
class StartKingKillBtn extends Button{
    private Renderer renderer;
    public StartKingKillBtn (Renderer renderer){
        this.renderer = renderer;
    }
    @Override
    public void onClick(){
        renderer.game = new KingKill();
        renderer.setStat(GameStat.INGAME);
    }
}
class UpgradeDPBtn extends Button{
    private CollideObjectManager collideObjectManager;
    public UpgradeDPBtn (CollideObjectManager collideObjectManager){
        this.collideObjectManager = collideObjectManager;
    }
    @Override
    public void onClick(){
        try {
            collideObjectManager.getMyPlayer().upgradeDP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class UpgradeHPBtn extends Button{
    private CollideObjectManager collideObjectManager;
    public UpgradeHPBtn (CollideObjectManager collideObjectManager){
        this.collideObjectManager = collideObjectManager;
    }
    @Override
    public void onClick(){
        try {
            collideObjectManager.getMyPlayer().upgradeHP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}