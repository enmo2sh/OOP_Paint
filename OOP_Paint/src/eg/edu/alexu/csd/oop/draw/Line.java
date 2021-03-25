package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.util.*;

public class Line extends Cshape {
    private Point p1;
    private Point p2;
    private Color co;
    private Map <String, Double> pr = new HashMap<String, Double>();
    public void draw(Graphics canvas) {
        p1=getPosition();
        pr=getProperties();
        co=getColor();
        int x = (int)Math.round(pr.get("sec_x"));
        int y = (int)Math.round(pr.get("sec_y"));
        co=getColor();
        canvas.setColor(co);
        canvas.drawLine(p1.x,p1.y,x,y);
    }
}
