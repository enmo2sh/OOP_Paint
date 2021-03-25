package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;
import javax.swing.*;

public class rectangle extends Cshape {

    private Point p1;
    private Color co;
    private Color fco;
    private Map <String, Double> pr = new HashMap<String, Double>();

    public void draw(Graphics canvas) {
        p1=getPosition();
        pr=getProperties();
        co=getColor();
        fco=getFillColor();
        canvas.setColor(co);
        int x= (int) Math.round(pr.get("sec_x"));
        int y= (int) Math.round(pr.get("sec_y"));
        pr=getProperties();
        int minX = Math.min(x, p1.x);
        int minY = Math.min(y, p1.y);
        int width = Math.abs(x - p1.x);
        int height = Math.abs(y - p1.y);
        if(fco==Color.black)
            canvas.drawRect(minX, minY, width, height);
        else {
            canvas.setColor(fco);
            canvas.drawRect(minX, minY, width, height);
        }
    }
}
