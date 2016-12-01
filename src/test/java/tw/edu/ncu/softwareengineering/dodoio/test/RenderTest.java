package tw.edu.ncu.softwareengineering.dodoio.test;

import tw.edu.ncu.softwareengineering.dodoio.Graphic.Renderer;
import static org.junit.Assert.*;
import org.junit.*;

public class RenderTest{
    @Rule
    private Renderer renderer;
    @before
    public void setup(){
        this.renderer = new Renderer(null, null);
    }
    @Test
    public void testGameStat(){
        renderer.setStat(GameStat.INGAME);
        assertEquals(renderer.getStat(), GameStat.INGAME);
    }
}