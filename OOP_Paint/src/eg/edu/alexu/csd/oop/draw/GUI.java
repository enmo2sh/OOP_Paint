package eg.edu.alexu.csd.oop.draw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
    public JFrame frame = new JFrame();
    public Canvas canvas = new Canvas();
    private static Color out=Color.WHITE;
    private static Color fill=Color.black;
    Point p1 = new Point();
    Point p2 = new Point();
    Point p3 = new Point();
    Cdrawing k = new Cdrawing();
    boolean li, ci, ov, re, sq, tr, move, resize =false;
    private int count=1;
    private String currentSh;
    double width =0;
    double height =0;
    int diffx=0;
    int diffy =0;
    int diffx2=0;
    int diffy2 =0;
    static LinkedList <Integer> index = new LinkedList <Integer> ();
    int counter=0;
    Double x;
    Double y;
    Boolean check=false;
    Shape n=new Cshape();
    Shape[]Shapes=k.getShapes();
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI() {
        initialize();
    }

    private void initialize(){
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setForeground(Color.PINK);
        frame.setBackground(Color.BLACK);
        frame.setBounds(0, 0, 974, 735);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        JLabel img = new JLabel(new ImageIcon("back.jpeg"));
        frame.setContentPane(img);

        frame.getContentPane().add(canvas);
        Canvas canvas = new Canvas();
        canvas.setLocation(160, 40);
        canvas.setSize(650, 500);
        canvas.setBackground(new Color(0, 0, 0));
        frame.getContentPane().add(canvas);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setForeground(new Color(255, 0, 51));
        comboBox_1.setFont(new Font("Sylfaen", Font.BOLD, 16));
        comboBox_1.setMaximumRowCount(15);
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"white", "black", "red", "blue", "green", "yellow", "pink", "orange", "cyan", "megenta", "dark gray"}));
        comboBox_1.setToolTipText("Colors");
        comboBox_1.setSelectedIndex(1);
        comboBox_1.setBackground(new Color(0, 0, 0));
        comboBox_1.setBounds(84, 447, 70, 31);
        frame.getContentPane().add(comboBox_1);

        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setForeground(new Color(255, 0, 51));
        comboBox_2.setBackground(new Color(0, 0, 0));
        comboBox_2.setBounds(84, 405, 70, 31);
        frame.getContentPane().add(comboBox_2);
        comboBox_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSh = (String)comboBox_2.getSelectedItem();
                if(currentSh!=null)
                    index.add(Character.getNumericValue(currentSh.charAt(currentSh.length()-1)));
            }
        });

        JButton btnLine = new JButton("Line");
        frame.getContentPane().add(btnLine);
        btnLine.setBackground(new Color(0, 0, 0));
        btnLine.setForeground(new Color(255, 20, 147));
        btnLine.setFont(new Font("Bell MT", Font.BOLD, 19));
        btnLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Line l= new Line();
                li=true; ci=false; ov=false; re=false; sq=false; tr=false; move=false; resize=false;
                Map<String, Double> pr = new HashMap<String, Double>();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(li==true) {
                            p1 = e.getPoint();
                            l.setPosition(p1);
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(li==true) {
                            p2 = e.getPoint();
                            Map<String, Double> pr2 = new HashMap<String, Double>();
                            Line l2= new Line();
                            l2.setPosition(p1);
                            pr2.put("sec_x",p2.getX() );
                            pr2.put("sec_y",p2.getY() );
                            pr2.put("type", 1.0);

                            l2.setProperties(pr2);
                            l2.setColor(out);
                            l2.setFillColor(fill);
                            l2.draw(canvas.getGraphics());
                            comboBox_2.addItem("shape "+String.valueOf(count));
                            count++;
                            k.addShape(l2);
                            Shapes=k.getShapes();
                            li=false;
                        }
                    }
                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(li==true) {
                            p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650,500);
                            k.refresh(canvas.getGraphics());
                            pr.put("type", 10.0);
                            pr.put("sec_x",p2.getX() );
                            pr.put("sec_y",p2.getY() );
                            l.setProperties(pr);
                            l.setColor(out);
                            l.draw(canvas.getGraphics());
                        }
                    }
                });
            }
        });
        btnLine.setBounds(839, 69, 91, 31);
        frame.getContentPane().add(btnLine);


        JButton btncircle = new JButton("Circle");
        frame.getContentPane().add(btncircle);
        btncircle.setBackground(Color.BLACK);
        btncircle.setForeground(new Color(255, 20, 147));
        btncircle.setFont(new Font("Bell MT", Font.BOLD, 19));
        btncircle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=true; ov=false; re=false; sq=false; tr=false; move=false; resize=false;
                Circle c = new Circle();
                Map<String, Double> pr = new HashMap<String, Double>();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(ci == true) {
                            p1 = e.getPoint();
                            c.setPosition(p1);
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(ci == true) {
                            p2 = e.getPoint();
                            Double r =e.getPoint().distance(p1);
                            Map<String, Double> pr2 = new HashMap<String, Double>();
                            Circle c2 = new Circle();
                            c2.setPosition(p1);
                            pr2.put("circle",r);
                            pr2.put("type", 2.0);
                            c2.setProperties(pr2);
                            c2.setColor(out);
                            c2.setFillColor(fill);
                            c2.draw(canvas.getGraphics());
                            fill=Color.black;
                            comboBox_2.addItem("shape "+String.valueOf(count));
                            count++;
                            k.addShape(c2);
                            Shapes=k.getShapes();
                            ci=false;
                        }
                    }
                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(ci == true) {
                            if(Shapes.length>0)

                                p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650,500);
                            k.refresh(canvas.getGraphics());
                            pr.clear();
                            Double r =e.getPoint().distance(p1);
                            pr.put("circle",r);
                            pr.put("type", 10.0);
                            c.setProperties(pr);
                            c.setColor(out);
                            c.setFillColor(fill);
                            c.draw(canvas.getGraphics());
                        }
                    }
                });

            }
        });
        btncircle.setBounds(839, 138, 91, 31);
        frame.getContentPane().add(btncircle);


        JButton btnoval = new JButton("Oval");
        frame.getContentPane().add(btnoval);
        btnoval.setBackground(new Color(0, 0, 0));
        btnoval.setForeground(new Color(255, 20, 147));
        btnoval.setFont(new Font("Bell MT", Font.BOLD, 19));
        btnoval.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=false; ov=true; re=false; sq=false; tr=false; move=false; resize=false;
                Oval o = new Oval();
                Map<String, Double> pr = new HashMap<String, Double>();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(ov==true) {
                            p1 = e.getPoint();
                            o.setPosition(p1);
                            //repaint();
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(ov==true) {
                            Oval o2 = new Oval();
                            p2 = e.getPoint();
                            Map<String, Double> pr2 = new HashMap<String, Double>();
                            pr2.put("type", 3.0);
                            pr2.put("sec_x",p2.getX() );
                            pr2.put("sec_y",p2.getY() );
                            o2.setPosition(p1);
                            o2.setProperties(pr2);
                            o2.setColor(out);
                            o2.setFillColor(fill);
                            o2.draw(canvas.getGraphics());

                            fill=Color.black;
                            comboBox_2.addItem("shape "+String.valueOf(count));
                            count++;
                            k.addShape(o2);
                            Shapes=k.getShapes();
                            ov=false;
                        }
                    }
                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(ov==true) {
                            p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650,500);
                            k.refresh(canvas.getGraphics());
                            pr.clear();
                            pr.put("type", 10.0);
                            pr.put("sec_x",p2.getX() );
                            pr.put("sec_y",p2.getY() );
                            o.setProperties(pr);
                            o.setColor(out);
                            o.setFillColor(fill);
                            o.draw(canvas.getGraphics());
                            //repaint();
                        }
                    }
                });

            }
        });
        btnoval.setBounds(839, 207, 91, 31);
        frame.getContentPane().add(btnoval);


        JButton btnrec = new JButton("Rect.");
        frame.getContentPane().add(btnrec);
        btnrec.setBackground(new Color(0, 0, 0));
        btnrec.setForeground(new Color(255, 20, 147));
        btnrec.setFont(new Font("Bell MT", Font.BOLD, 19));
        btnrec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=false; ov=false; re=true; sq=false; tr=false; move=false; resize=false;
                rectangle rec = new rectangle();
                Map<String, Double> pr = new HashMap<String, Double>();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(re==true) {
                            p1 = e.getPoint();
                            rec.setPosition(p1);
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(re==true) {
                            rectangle rec2 = new rectangle();
                            p2 = e.getPoint();
                            Map<String, Double> pr2 = new HashMap<String, Double>();
                            pr2.put("type", 4.0);
                            pr2.put("sec_x",p2.getX() );
                            pr2.put("sec_y",p2.getY() );
                            rec2.setPosition(p1);
                            rec2.setProperties(pr2);
                            rec2.setColor(out);
                            rec2.setFillColor(fill);
                            rec2.draw(canvas.getGraphics());
                            fill=Color.black;
                            comboBox_2.addItem("shape "+String.valueOf(count));
                            count++;
                            k.addShape(rec2);
                            Shapes=k.getShapes();
                            re=false;
                        }
                    }
                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(re==true) {
                            p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650,500);
                            k.refresh(canvas.getGraphics());
                            pr.clear();
                            pr.put("type", 10.0);
                            pr.put("sec_x",p2.getX() );
                            pr.put("sec_y",p2.getY() );
                            rec.setProperties(pr);
                            rec.setColor(out);
                            rec.setFillColor(fill);
                            rec.draw(canvas.getGraphics());
                        }
                    }
                });
            }
        });
        btnrec.setBounds(839, 345, 91, 31);
        frame.getContentPane().add(btnrec);


        JButton btnsqr = new JButton("Square");
        frame.getContentPane().add(btnsqr);
        btnsqr.setBackground(new Color(0, 0, 0));
        btnsqr.setForeground(new Color(255, 20, 147));
        btnsqr.setFont(new Font("Bell MT", Font.BOLD, 19));
        btnsqr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=false; ov=false; re=false; sq=true; tr=false; move=false; resize=false;
                Square sqr = new Square();
                Map<String, Double> pr = new HashMap<String, Double>();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(sq==true) {
                            p1 = e.getPoint();
                            sqr.setPosition(p1);
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(sq==true) {
                            Square sqr2 = new Square();
                            p2 = e.getPoint();
                            Map<String, Double> pr2 = new HashMap<String, Double>();
                            pr2.put("type", 5.0);
                            pr2.put("sec_x",p2.getX() );
                            pr2.put("sec_y",p2.getY() );
                            sqr2.setPosition(p1);
                            sqr2.setProperties(pr2);
                            sqr2.setColor(out);
                            sqr2.setFillColor(fill);
                            sqr2.draw(canvas.getGraphics());
                            fill=Color.black;
                            comboBox_2.addItem("shape "+String.valueOf(count));
                            count++;
                            k.addShape(sqr2);
                            Shapes=k.getShapes();
                            sq=false;
                        }

                    }
                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(sq==true) {
                            p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650,500);
                            k.refresh(canvas.getGraphics());
                            pr.clear();
                            pr.put("type", 10.0);
                            pr.put("sec_x",p2.getX() );
                            pr.put("sec_y",p2.getY() );
                            sqr.setProperties(pr);
                            sqr.setColor(out);
                            sqr.setFillColor(fill);
                            sqr.draw(canvas.getGraphics());
                        }
                    }
                });

            }
        });
        btnsqr.setBounds(839, 276, 91, 31);
        frame.getContentPane().add(btnsqr);

        JButton btntria = new JButton("Tria.");
        frame.getContentPane().add(btntria);
        btntria.setBackground(new Color(0, 0, 0));
        btntria.setForeground(new Color(255, 20, 147));
        btntria.setFont(new Font("Bell MT", Font.BOLD, 19));
        btntria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter=0;
                check=false;
                li=false; ci=false; ov=false; re=false; sq=false; tr=true; move=false; resize=false;
                Triangle t = new Triangle();
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(tr==true) {
                            if(counter%2==0) {
                                p1 = e.getPoint();
                                t.setPosition(p1);
                            }
                            else {
                                p3 = e.getPoint();
                            }

                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(tr==true) {

                            if(counter%2==0) {
                                Map<String, Double> pr2 = new HashMap<String, Double>();
                                p2 = e.getPoint();
                                t.setColor(out);
                                t.setFillColor(fill);
                                pr2.clear();
                                pr2.put("type", 10.0);
                                pr2.put("sec_x",p2.getX() );
                                pr2.put("sec_y",p2.getY() );
                                pr2.put("sec_x2",p2.getX() );
                                pr2.put("sec_y2",p2.getY() );
                                t.setProperties(pr2);
                                x=p2.getX();
                                y=p2.getY();
                                t.draw(canvas.getGraphics());
                                counter++;

                            }
                            else {
                                if(check) {
                                    Triangle t2 = new Triangle();
                                    Map<String, Double> pr3 = new HashMap<String, Double>();
                                    p3 = e.getPoint();
                                    t2.setColor(out);
                                    t2.setFillColor(fill);
                                    t2.setPosition(p1);
                                    pr3.clear();
                                    pr3.put("type", 6.0);
                                    pr3.put("sec_x",x);
                                    pr3.put("sec_y",y);
                                    pr3.put("sec_x2",p3.getX() );
                                    pr3.put("sec_y2",p3.getY() );
                                    t2.setProperties(pr3);
                                    t2.draw(canvas.getGraphics());
                                    counter++;
                                    fill=Color.black;
                                    comboBox_2.addItem("shape "+String.valueOf(count));
                                    count++;
                                    k.addShape(t2);
                                    Shapes=k.getShapes();
                                    tr=false;
                                }
                            }

                        }

                    }

                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(tr==true) {
                            Map<String, Double> pr = new HashMap<String, Double>();
                            if(counter%2==0) {
                                p2 = e.getPoint();
                                t.setColor(out);
                                t.setFillColor(fill);
                                canvas.getGraphics().clearRect(0, 0, 650,500);
                                k.refresh(canvas.getGraphics());
                                pr.clear();
                                pr.put("type", 10.0);
                                pr.put("sec_x",p2.getX() );
                                pr.put("sec_y",p2.getY() );
                                pr.put("sec_x2",p2.getX() );
                                pr.put("sec_y2",p2.getY() );
                                t.setProperties(pr);
                                t.draw(canvas.getGraphics());
                            }
                            else {
                                p3 = e.getPoint();
                                t.setColor(out);
                                t.setFillColor(fill);
                                canvas.getGraphics().clearRect(0, 0, 650,500);
                                k.refresh(canvas.getGraphics());
                                pr.clear();
                                pr.put("type", 10.0);
                                pr.put("sec_x",x);
                                pr.put("sec_y",y);
                                pr.put("sec_x2",p3.getX() );
                                pr.put("sec_y2",p3.getY() );
                                t.setProperties(pr);
                                t.draw(canvas.getGraphics());
                                check=true;
                            }
                        }
                    }
                });

            }
        });
        btntria.setBounds(839, 414, 91, 31);
        frame.getContentPane().add(btntria);

        JButton btnUndo = new JButton("Undo");
        btnUndo.setForeground(new Color(51, 255, 51));
        btnUndo.setFont(new Font("Stencil", Font.BOLD, 16));
        btnUndo.setBackground(Color.BLACK);
        btnUndo.setBounds(34, 157, 91, 31);
        frame.getContentPane().add(btnUndo);
        btnUndo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                k.undo();
                canvas.getGraphics().clearRect(0,0,650,500);
                k.refresh(canvas.getGraphics());
                Shapes=k.getShapes();
                if(Shapes.length<(count-1)) {
                    comboBox_2.removeItemAt(count-2);
                    count--;
                }
                else if(Shapes.length>(count-1)) {
                    comboBox_2.addItem("shape "+String.valueOf(count));
                    count++;
                }

            }
        });

        JButton btnRedo = new JButton("Redo");
        btnRedo.setForeground(new Color(51, 255, 51));
        btnRedo.setFont(new Font("Stencil", Font.BOLD, 16));
        btnRedo.setBackground(Color.BLACK);
        btnRedo.setBounds(34, 192, 91, 31);
        frame.getContentPane().add(btnRedo);
        btnRedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                k.redo();
                canvas.getGraphics().clearRect(0,0,650,500);
                k.refresh(canvas.getGraphics());
                Shapes=k.getShapes();
                if(Shapes.length<(count-1)) {
                    comboBox_2.removeItemAt(count-2);
                    count--;
                }
                else if(Shapes.length>(count-1)) {
                    comboBox_2.addItem("shape "+String.valueOf(count));
                    count++;
                }
            }
        });


        JButton btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(255, 255, 0));
        btnDelete.setBackground(new Color(0, 0, 0));
        btnDelete.setFont(new Font("Bell MT", Font.BOLD, 16));
        btnDelete.setBounds(34, 279, 91, 31);
        frame.getContentPane().add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = Character.getNumericValue(currentSh.charAt(currentSh.length()-1));
                k.removeShape(Shapes[i-1]);
                canvas.getGraphics().clearRect(0,0,650,500);
                k.refresh(canvas.getGraphics());
                comboBox_2.removeItemAt(count-2);
                count--;
                Shapes=k.getShapes();
            }
        });

        JButton btnMove = new JButton("Move");
        btnMove.setForeground(Color.YELLOW);
        btnMove.setBackground(new Color(0, 0, 0));
        btnMove.setFont(new Font("Bell MT", Font.BOLD, 16));
        btnMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=false; ov=false; re=false; sq=false; tr=false; move=true; resize=false;
                try {
                    n=(Shape)Shapes[index.getLast()-1].clone();
                } catch (CloneNotSupportedException e1) {
                    e1.printStackTrace();
                }
                if(Shapes[index.getLast()-1].getProperties().get("type")==4.0 || Shapes[index.getLast()-1].getProperties().get("type")==5.0  || Shapes[index.getLast()-1].getProperties().get("type")==3.0) {
                    width = Math.abs(Shapes[index.getLast()-1].getPosition().x - Shapes[index.getLast()-1].getProperties().get("sec_x"));
                    height = Math.abs(Shapes[index.getLast()-1].getPosition().y - Shapes[index.getLast()-1].getProperties().get("sec_y"));

                }
                if(Shapes[index.getLast()-1].getProperties().get("type")==1.0) {
                    diffx = Shapes[index.getLast()-1].getPosition().x - Shapes[index.getLast()-1].getProperties().get("sec_x").intValue();
                    diffy = Shapes[index.getLast()-1].getPosition().y - Shapes[index.getLast()-1].getProperties().get("sec_y").intValue();

                }
                if(Shapes[index.getLast()-1].getProperties().get("type")==6.0) {
                    diffx = Shapes[index.getLast()-1].getPosition().x - Shapes[index.getLast()-1].getProperties().get("sec_x").intValue();
                    diffy = Shapes[index.getLast()-1].getPosition().y - Shapes[index.getLast()-1].getProperties().get("sec_y").intValue();
                    diffx2 =Shapes[index.getLast()-1].getPosition().x- Shapes[index.getLast()-1].getProperties().get("sec_x2").intValue();
                    diffy2 =Shapes[index.getLast()-1].getPosition().y- Shapes[index.getLast()-1].getProperties().get("sec_y2").intValue();

                }
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(move == true) {
                            p1 = e.getPoint();
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(move == true) {
                            p1 = e.getPoint();
                            n.setPosition(p1);
                            if(Shapes[index.getLast()-1].getProperties().get("type")==1.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                Point p2= new Point();
                                p2.x=p1.x-diffx;
                                p2.y=p1.y-diffy;
                                pr.put("sec_x", p2.getX());
                                pr.put("sec_y", p2.getY());
                                pr.put("type", Shapes[index.getLast()-1].getProperties().get("type"));
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==2.0) {
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==4.0 || Shapes[index.getLast()-1].getProperties().get("type")==5.0||Shapes[index.getLast()-1].getProperties().get("type")==3.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                Point p2= new Point();
                                p2.x=p1.x+(int)width;
                                p2.y=p1.y+(int)height;
                                pr.put("sec_x", p2.getX());
                                pr.put("sec_y", p2.getY());
                                pr.put("type", Shapes[index.getLast()-1].getProperties().get("type"));
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==6.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                Point p2= new Point();
                                p2.x=p1.x-diffx;
                                p2.y=p1.y-diffy;
                                Point p3= new Point();
                                p3.x=p1.x-diffx2;
                                p3.y=p1.y-diffy2;
                                pr.put("sec_x", p2.getX());
                                pr.put("sec_y", p2.getY());
                                pr.put("sec_x2", p3.getX());
                                pr.put("sec_y2", p3.getY());
                                pr.put("type", Shapes[index.getLast()-1].getProperties().get("type"));
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            move = false;
                        }
                    }

                });

                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(move == true) {
                            p1 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650, 500);
                            k.refresh(canvas.getGraphics());
                            if(Shapes[index.getLast()-1].getProperties().get("type")==1.0) {
                                canvas.getGraphics().drawLine(p1.x,p1.y,p1.x-diffx,p1.y-diffy);
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==2.0) {
                                Double r = Shapes[index.getLast()-1].getProperties().get("circle");

                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawOval(p1.x-r.intValue(),p1.y-r.intValue(),2*r.intValue(),2*r.intValue());
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillOval(p1.x-r.intValue(),p1.y-r.intValue(),2*r.intValue(),2*r.intValue());
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==3.0) {
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawOval(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillOval(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==4.0) {
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==5.0) {
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(width));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(width));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==6.0) {
                                int xs[]=new int[3];
                                int ys[]=new int [3];
                                xs[0]=p1.x;
                                ys[0]=p1.y;
                                xs[1]=p1.x-diffx;
                                ys[1]=p1.y-diffy;
                                xs[2]=p1.x-diffx2;
                                ys[2]=p1.y-diffy2;

                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawPolygon(xs, ys, 3);
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillPolygon(xs, ys, 3);
                                }
                            }
                        }
                    }
                });
            }
        });


        btnMove.setBounds(34, 244, 91, 31);
        frame.getContentPane().add(btnMove);

        JButton btnSetcolor = new JButton("SetColor");
        btnSetcolor.setBackground(new Color(0, 0, 0));
        btnSetcolor.setForeground(new Color(255, 255, 0));
        btnSetcolor.setFont(new Font("Bell MT", Font.BOLD, 15));
        btnSetcolor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    n=(Shape)Shapes[index.getLast()-1].clone();
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                n.setColor(out);
                n.setFillColor(fill);
                k.updateShape(Shapes[index.getLast()-1], n);
                canvas.getGraphics().clearRect(0,0,650,500);
                k.refresh(canvas.getGraphics());
                Shapes=k.getShapes();
                fill=Color.black;
            }
        });


        btnSetcolor.setBounds(34, 349, 91, 31);
        frame.getContentPane().add(btnSetcolor);
        comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String color = (String)comboBox_1.getSelectedItem();
                fill=setColors(color);
            }
        });

        JButton btnNewButton = new JButton("Resize");
        btnNewButton.setForeground(Color.YELLOW);
        btnNewButton.setBackground(new Color(0, 0, 0));
        btnNewButton.setFont(new Font("Bell MT", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                li=false; ci=false; ov=false; re=false; sq=false; tr=false; move=false; resize=true;
                try {
                    n=(Shape)Shapes[index.getLast()-1].clone();
                } catch (CloneNotSupportedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                canvas.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(resize == true) {
                            p1 = Shapes[index.getLast()-1].getPosition();
                        }
                    }
                    public void mouseReleased(MouseEvent e) {
                        if(resize == true) {
                            p2 = e.getPoint();
                            if(Shapes[index.getLast()-1].getProperties().get("type")==1.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                pr.put("sec_x", p2.getX());
                                pr.put("sec_y", p2.getY());
                                pr.put("type", 1.0);
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==2.0) {
                                Double r =e.getPoint().distance(p1);
                                Map<String, Double> pr = new HashMap<String, Double>();
                                pr.put("circle", r);
                                pr.put("type", 2.0);
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==4.0 || Shapes[index.getLast()-1].getProperties().get("type")==5.0||Shapes[index.getLast()-1].getProperties().get("type")==3.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                pr.put("sec_x", p2.getX());
                                pr.put("sec_y", p2.getY());
                                if(Shapes[index.getLast()-1].getProperties().get("type")==4.0)
                                    pr.put("type", 4.0);
                                else if(Shapes[index.getLast()-1].getProperties().get("type")==5.0)
                                    pr.put("type", 5.0);
                                else if(Shapes[index.getLast()-1].getProperties().get("type")==3.0)
                                    pr.put("type", 3.0);
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==6.0) {
                                Map<String, Double> pr = new HashMap<String, Double>();
                                Point p3=new Point();
                                p3.x=Shapes[index.getLast()-1].getProperties().get("sec_x").intValue();
                                p3.y=Shapes[index.getLast()-1].getProperties().get("sec_y").intValue();
                                pr.put("sec_x", p3.getX());
                                pr.put("sec_y", p3.getY());
                                pr.put("sec_x2", p2.getX());
                                pr.put("sec_y2", p2.getY());
                                pr.put("type", 6.0);
                                n.setProperties(pr);
                                k.updateShape(Shapes[index.getLast()-1],n);
                                canvas.getGraphics().clearRect(0,0,650,500);
                                k.refresh(canvas.getGraphics());
                                Shapes=k.getShapes();
                            }
                            resize = false;
                        }
                    }

                });
                canvas.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        if(resize == true) {
                            p2 = e.getPoint();
                            canvas.getGraphics().clearRect(0, 0, 650, 500);
                            k.refresh(canvas.getGraphics());
                            if(Shapes[index.getLast()-1].getProperties().get("type")==1.0) {
                                canvas.getGraphics().drawLine(p1.x,p1.y,p2.x,p2.y);
                            }
                            else if(Shapes[index.getLast()-1].getProperties().get("type")==2.0) {
                                Double r =e.getPoint().distance(p1);
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawOval(p1.x-r.intValue(),p1.y-r.intValue(),2*r.intValue(),2*r.intValue());
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillOval(p1.x-r.intValue(),p1.y-r.intValue(),2*r.intValue(),2*r.intValue());
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==3.0) {
                                int minX = Math.min(p2.x, p1.x);
                                int minY = Math.min(p2.y, p1.y);
                                int width = Math.abs(p2.x - p1.x);
                                int height = Math.abs(p2.y - p1.y);
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawOval(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillOval(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==4.0) {
                                int minX = Math.min(p2.x, p1.x);
                                int minY = Math.min(p2.y, p1.y);
                                int width = Math.abs(p2.x - p1.x);
                                int height = Math.abs(p2.y - p1.y);
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(height));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==5.0) {
                                int minX = Math.min(p2.x, p1.x);
                                int minY = Math.min(p2.y, p1.y);
                                int width = Math.abs(p2.x - p1.x);
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(width));
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillRect(p1.x, p1.y, (int) Math.round(width), (int) Math.round(width));
                                }
                            }
                            else if (Shapes[index.getLast()-1].getProperties().get("type")==6.0) {
                                int xs[]=new int[3];
                                int ys[]=new int [3];
                                xs[0]=p1.x;
                                ys[0]=p1.y;
                                xs[1]=Shapes[index.getLast()-1].getProperties().get("sec_x").intValue();
                                ys[1]=Shapes[index.getLast()-1].getProperties().get("sec_y").intValue();
                                xs[2]=p2.x;
                                ys[2]=p2.y;
                                if(Shapes[index.getLast()-1].getFillColor()==Color.DARK_GRAY)
                                    canvas.getGraphics().drawPolygon(xs, ys, 3);
                                else {
                                    canvas.getGraphics().setColor(Shapes[index.getLast()-1].getFillColor());
                                    canvas.getGraphics().fillPolygon(xs, ys, 3);
                                }
                            }
                        }
                    }
                });
            }
        });
        btnNewButton.setBounds(34, 314, 91, 31);
        frame.getContentPane().add(btnNewButton);




        JButton btnSave = new JButton("Save");
        btnSave.setForeground(new Color(100, 149, 237));
        btnSave.setFont(new Font("Stencil", Font.BOLD, 16));
        btnSave.setBackground(new Color(0, 0, 0));
        btnSave.setBounds(34, 70, 91, 31);
        frame.getContentPane().add(btnSave);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs=new JFileChooser(new File(""));
                fs.setDialogTitle("save a file");
                fs.setFileFilter(new FileTypeFilter(".xml","XML FILE"));
                fs.setFileFilter(new FileTypeFilter(".json","JSON FILE"));
                int result=fs.showSaveDialog(null);
                if(result==JFileChooser.APPROVE_OPTION) {
                    File fi=fs.getSelectedFile();
                    if((fi.getPath().charAt(fi.getPath().length()-1)=='l')&&(fi.getPath().charAt(fi.getPath().length()-2)=='m')&&(fi.getPath().charAt(fi.getPath().length()-3)=='x')&&(fi.getPath().charAt(fi.getPath().length()-4)=='.'))
                        k.save(fi.getPath());
                    else
                        k.save(fi.getPath()+".xml");
                }
            }
        });

        JButton btnLoad = new JButton("load");
        btnLoad.setForeground(new Color(100, 149, 237));
        btnLoad.setFont(new Font("Stencil", Font.BOLD, 16));
        btnLoad.setBackground(new Color(0, 0, 0));
        btnLoad.setBounds(34, 105, 91, 31);
        frame.getContentPane().add(btnLoad);
        btnLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs=new JFileChooser(new File(""));
                fs.setDialogTitle("load a file");
                fs.setFileFilter(new FileTypeFilter(".xml","XML FILE"));
                fs.setFileFilter(new FileTypeFilter(".json","JSON FILE"));
                int result=fs.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION) {
                    try{
                        File fi=fs.getSelectedFile();
                        k.load(fi.getPath());
                        canvas.getGraphics().clearRect(0,0,650,500);
                        k.refresh(canvas.getGraphics());
                        Shapes=k.getShapes();
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        JLabel lblNewLabel = new JLabel("border co:");
        lblNewLabel.setBackground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setForeground(Color.CYAN);
        lblNewLabel.setBounds(4, 478, 121, 42);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblFillColor = new JLabel("fill color:");
        lblFillColor.setForeground(new Color(0, 255, 255));
        lblFillColor.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFillColor.setBounds(4, 439, 121, 42);
        frame.getContentPane().add(lblFillColor);

        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(new Color(255, 0, 51));
        comboBox.setFont(new Font("Sylfaen", Font.BOLD, 16));
        comboBox.setMaximumRowCount(15);
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"white", "black", "red", "blue", "green", "yellow", "pink", "orange", "cyan", "megenta", "dark gray"}));
        comboBox.setToolTipText("Colors");
        comboBox.setBounds(84, 489, 70, 31);
        comboBox.setBackground(new Color(0, 0, 0));
        comboBox.setSelectedIndex(0);
        frame.getContentPane().add(comboBox);

        JLabel lblNewLabel_1 = new JLabel("Shapes");
        lblNewLabel_1.setBackground(Color.PINK);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setForeground(new Color(0, 255, 255));
        lblNewLabel_1.setBounds(4, 409, 101, 19);
        frame.getContentPane().add(lblNewLabel_1);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String color = (String)comboBox.getSelectedItem();
                out=setColors(color);
            }
        });

        JButton button = new JButton("Add");
        button.setForeground(new Color(255, 20, 147));
        button.setFont(new Font("Bell MT", Font.BOLD, 19));
        button.setBackground(Color.BLACK);
        button.setBounds(839, 483, 91, 31);
        frame.getContentPane().add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf=new JFileChooser(new File(""));
                jf.setDialogTitle("choose your jar file");
                jf.setFileFilter(new FileTypeFilter(".jar","JAR FILE"));
                int result=jf.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION) {
                    File jar=jf.getSelectedFile();
                    k.installPluginShape(jar.getPath());
                    canvas.getGraphics().clearRect(0,0,650,500);
                    k.refresh(canvas.getGraphics());

                }
            }
        });
    }

    private Color setColors(String color) {
        Color c=Color.red;
        switch(color) {
            case "white":
                c = Color.white;
                break;
            case "black":
                c = Color.black;
                break;
            case "red":
                c = Color.RED;
                break;
            case "blue":
                c = Color.blue;
                break;
            case "green":
                c = Color.green;
                break;
            case "yellow":
                c = Color.yellow;
                break;
            case "pink":
                c = Color.pink;
                break;
            case "orange":
                c = Color.orange;
                break;
            case "cyan":
                c = Color.cyan;
                break;
            case "megenta":
                c = Color.magenta;
                break;
            case "dark gray":
                c = Color.DARK_GRAY;
                break;

        }
        return c;
    }
}