package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends Cshape{
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
        int x = (int)Math.round(pr.get("sec_x"));
        int y = (int)Math.round(pr.get("sec_y"));
        int x2 = (int)Math.round(pr.get("sec_x2"));
        int y2 = (int)Math.round(pr.get("sec_y2"));
        int xs[]=new int[3];
        int ys[]=new int [3];

        xs[0]=p1.x;
        ys[0]=p1.y;
        xs[1]=x;
        ys[1]=y;
        xs[2]=x2;
        ys[2]=y2;



        if(fco==Color.black)
            canvas.drawPolygon(xs, ys, 3);
        else {
            canvas.setColor(fco);
            canvas.fillPolygon(xs, ys, 3);
        }
    }

}

