package eg.edu.alexu.csd.oop.draw;

import javax.management.RuntimeErrorException;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Cdrawing implements DrawingEngine{

    private LinkedList <Shape> Myshapes = new LinkedList <Shape> ();
    private Stack<Map <String, Shape>> op = new Stack<Map <String, Shape>>();
    private Stack<Map <String, Shape>> te = new Stack<Map <String, Shape>>();
    private Object total;
    private List<Class<? extends Shape>> classes = new ArrayList<>();

    /* redraw all shapes on the canvas */
    public void refresh(java.awt.Graphics canvas) {
        for(int i=0;i<Myshapes.size();i++) {

            Myshapes.get(i).draw(canvas);

        }
    }

    public void addShape(Shape shape) {
        Myshapes.add(shape);
        Map<String,Shape> p=new HashMap<String,Shape>();
        p.put("a", shape);
        if(op.size()>=20)
            op.remove(0);
        op.add(p);
        te.clear();

    }

    public void removeShape(Shape shape) {
        Myshapes.remove(shape);
        Map<String,Shape> p=new HashMap<String,Shape>();
        p.put("r", shape);
        if(op.size()>=20)
            op.remove(0);
        op.add(p);
        te.clear();
    }

    public void updateShape(Shape oldShape, Shape newShape) {
        for(int i=0; i<Myshapes.size(); i++) {
            if(Myshapes.get(i)== oldShape) {
                Myshapes.set(i, newShape);
                break;
            }
        }
        Map<String,Shape> p=new HashMap<String,Shape>();
        p.put("u", oldShape);
        p.put("un", newShape);
        if(op.size()>=20)
            op.remove(0);
        op.add(p);
        te.clear();
    }

    /* return the created shapes objects */
    public Shape[] getShapes() {
        int s = Myshapes.size();
        Shape [] arr = new Shape [s];
        for(int i=0; i<s; i++)
            arr[i] = Myshapes.get(i);
        return arr;
    }

    /* return the classes (types) of supported shapes already exist and the
     * ones that can be dynamically loaded at runtime (see Part 3) */
    @Override
    public List<Class<? extends Shape>> getSupportedShapes() {
        if(classes.size()<6) {
            classes.add(Line.class);
            classes.add(Circle.class);
            classes.add(Oval.class);
            classes.add(rectangle.class);
            classes.add(Square.class);
            classes.add(Triangle.class);
        }
        System.out.println(classes);
        // TODO Auto-generated method stub
        return classes;
    }

    /* add to the supported shapes the new shape class (see Part 3) */
    public void installPluginShape(String jarPath) {
        File jf = new File(jarPath);
        //  ExtensionLoader.addToClasspath(jf);

    }

    /* limited to 20 steps. Only consider in undo & redo
     * these actions: addShape, removeShape, updateShape */
    public void undo() {
        System.out.println("undo:"+Myshapes.size());
        if(!op.isEmpty()) {
            Map<String,Shape> temp=new HashMap<String,Shape>();
            temp=op.peek();
            if(temp.containsKey("a"))
                Myshapes.remove(temp.get("a"));

            else if(temp.containsKey("r"))
                Myshapes.add(temp.get("r"));

            else if(temp.containsKey("u")) {
                for(int i=0;i<Myshapes.size();i++) {
                    if(Myshapes.get(i)== temp.get("un")) {
                        Myshapes.set(i, temp.get("u"));
                        System.out.println(Myshapes.get(i));
                        break;
                    }
                }
            }

            te.push(op.pop());
        }
        else {
            NullPointerException e = null;
            System.out.println("NO MORE MOVES");
            throw e;
        }

    }


    public void redo() {
        if(!te.isEmpty()) {
            Map<String,Shape> temp=new HashMap<String,Shape>();
            temp=te.peek();
            if(temp.containsKey("a"))
                Myshapes.add(temp.get("a"));

            else if(temp.containsKey("r"))
                Myshapes.remove(temp.get("r"));

            else if(temp.containsKey("u")) {
                for(int i=0;i<Myshapes.size();i++) {
                    if(Myshapes.get(i)== temp.get("u")) {
                        Myshapes.set(i, temp.get("un"));
                        break;
                    }
                }
            }
            op.push(te.pop());
        }
        else {
            NullPointerException e = null;
            System.out.println("NO MORE MOVES");
            throw new RuntimeException("No more moves");
        }

    }

    /* use the file extension to determine the type,
     * or throw runtime exception when unexpected extension */
    public void save(String path)  {
        try {
            FileOutputStream fos=new FileOutputStream(new File(path));
            XMLEncoder encoder=new XMLEncoder(fos);
            total=Myshapes.size();
            encoder.writeObject(total);
            for(int i=0;i<Myshapes.size();i++)
                encoder.writeObject(Myshapes.get(i));
            encoder.close();
            fos.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void load(String path) {
        try {
            FileInputStream fis=new FileInputStream(new File(path));
            XMLDecoder decoder=new XMLDecoder(fis);
            total=(Object)decoder.readObject();
            Myshapes.clear();
            for (int i=0;i<(int)total;i++)
                Myshapes.add((Shape)decoder.readObject());
            decoder.close();
            fis.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        for(int i=0;i<Myshapes.size();i++) {
            System.out.println(Myshapes.get(i));
        }

    }


}