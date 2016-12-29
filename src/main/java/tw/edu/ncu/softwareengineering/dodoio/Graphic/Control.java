package tw.edu.ncu.softwareengineering.dodoio.Graphic;

import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Character;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;

/**
 * Created by leemiyinghao on 2016/12/22.
 */
public class Control extends MouseAdapter implements KeyListener {
    private CollideObjectManager collideObjectManager;
    private Renderer renderer;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private ArrayList<Button> menuBtnList;
    private ArrayList<Button> inGameBtnList;


    public Control(CollideObjectManager collideObjectManager, Renderer renderer){
        this.collideObjectManager = collideObjectManager;
        this.renderer = renderer;
        this.menuBtnList = new ArrayList<Button>();
        this.inGameBtnList = new ArrayList<Button>();
    }

    public void update(int timeDiff){
        try {
            Position pos = collideObjectManager.getMyPlayer().getPosition();
            double x = pos.getX();
            double y = pos.getY();
            double speed = collideObjectManager.getMyPlayer().getSpeed();
            if (wPressed){
                y -= timeDiff*speed/1000;
            }
            if (aPressed){
                x -= timeDiff*speed/1000;
            }
            if (sPressed){
                y += timeDiff*speed/1000;
            }
            if (dPressed){
                x += timeDiff*speed/1000;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addInGameBtn(Button btn){
        this.inGameBtnList.add(btn);
    }
    public void addMenuBtn(Button btn){
        this.menuBtnList.add(btn);
    }
    public double angleBetweenTwoPoint(Position pos1, Position pos2){
        float xDiff = pos2.getX() - pos1.getX();
        float yDiff = pos2.getY() - pos1.getY();
        return atan2(xDiff, yDiff)/PI;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                wPressed = true;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                sPressed = true;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                aPressed = true;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                dPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                wPressed = false;
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                sPressed = false;
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                aPressed = false;
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                dPressed = false;
                break;
        }

    }

    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Character player;
        try {
            player = collideObjectManager.getMyPlayer();
        } catch (Exception e1) {
            e1.printStackTrace();
            return;
        }
        if(e.getButton() == MouseEvent.BUTTON1) {
            switch (renderer.getStat()) {
                case INGAME:
                    for (Button btn : inGameBtnList) {
                        if (btn.position.getX() > x && btn.position.getX() + btn.size.getX() < x &&
                                btn.position.getY() > y && btn.position.getY() + btn.size.getY() < x) {
                            btn.onClick();
                            return;
                        }
                    }
                    Position position = player.getPosition();
                    Position mousePosition = new Position(e.getX(), e.getY(), 0f);
                    position.setDirection(angleBetweenTwoPoint(player.getPosition(), mousePosition));
                    player.move(position);
                    player.attack();
                    break;
                case MAINMENU:
                    for (Button btn : menuBtnList) {
                        if (btn.position.getX() > x && btn.position.getX() + btn.size.getX() < x &&
                                btn.position.getY() > y && btn.position.getY() + btn.size.getY() < x) {
                            btn.onClick();
                            return;
                        }
                    }
                    break;
            }
        }else if(e.getButton() == MouseEvent.BUTTON3 && renderer.getStat()==GameStat.INGAME){
            Position position = player.getPosition();
            Position mousePosition = new Position(e.getX(), e.getY(), 0f);
            position.setDirection(angleBetweenTwoPoint(player.getPosition(), mousePosition));
            player.move(position);
            player.skill();
        }
    }
}
