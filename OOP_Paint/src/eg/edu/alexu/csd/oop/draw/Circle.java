package eg.edu.alexu.csd.oop.draw;

import java.awt.*;
import java.util.*;
public class Circle extends Cshape  {

    private Point center;
    private Color co;
    private Color fco;
    private Map <String, Double> pr = new HashMap<String, Double>();

    public void draw(Graphics canvas) {
        center=getPosition();
        pr=getProperties();
        co=getColor();
        fco=getFillColor();
        canvas.setColor(co);
        Double radius=pr.get("circle");
        if(fco==Color.black)
            canvas.drawOval(center.x-radius.intValue(),center.y-radius.intValue(),2*radius.intValue(),2*radius.intValue());
        else {
            canvas.setColor(fco);
            canvas.fillOval(center.x-radius.intValue(),center.y-radius.intValue(),2*radius.intValue(),2*radius.intValue());
        }
    }
}


