package tw.edu.ncu.softwareengineering.dodoio.Graphic;

import javafx.geometry.Pos;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObject;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.CollideObjectManager;
import tw.edu.ncu.softwareengineering.dodoio.CollideObject.Position;
import tw.edu.ncu.softwareengineering.dodoio.Game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by leemiyinghao on 2016/12/22.
 */
public class Control implements KeyListener {
    private CollideObjectManager collideObjectManager;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;

    public Control(CollideObject collideObject){
        this.CollideObjectManager = collideObjectManager;
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
}
