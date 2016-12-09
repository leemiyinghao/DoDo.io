package tw.edu.ncu.softwareengineering.dodoio.test;

import tw.edu.ncu.softwareengineering.dodoio.Graphic.Render;
import static org.junit.Assert.*;
import org.junit.*;

public class RenderTest{
    @Rule
    private Render renderer;
    @before
    public void setup(){
        this.renderer = new Render(null, null);
    }
    @Test
    public void testNewRender(){
        assertEquals(renderer.test(1,1), 1+1);
    }
}