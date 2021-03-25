package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Cshape implements Shape , Cloneable {
    private Point pos;
    private Color co;
    private Color fco;
    private Map <String, Double> pr = new HashMap<String, Double>();

    public void setPosition(Point position) {
        pos=position;
    }

    public Point getPosition() {
        return pos;
    }

    public void setProperties(Map<String, Double> properties) {
        pr=properties;
    }

    public Map<String, Double> getProperties() {
        if(pr.size()==0)
            pr.put("empty",0.0);
        return pr;
    }

    public void setColor(Color color) {
        co=color;
    }

    public Color getColor() {
        return co;
    }

    public void setFillColor(Color color) {
        fco=color;
    }

    public Color getFillColor() {
        return fco;
    }

    public void draw(Graphics canvas) {
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}

