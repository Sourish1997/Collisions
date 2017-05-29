import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.event.*;
public class Collisions extends JPanel implements ActionListener
{
    Timer timer;
    static JFrame jf;
    static Ellipse2D ball1,ball2;
    Color c1=getColor(),c2=getColor();
    static boolean started=false;
    static int x1=0,y1=0,x2=553,y2=327;
    static double ux1=10,uy1=10,ux2=-10,uy2=-10;
    static JSlider sliders[]=new JSlider[8];
    static JLabel labels[]=new JLabel[8];
    static JPanel cbox;
    static JButton button;
    static ChangeListener cl=new ChangeListener()
    {
        public void stateChanged(ChangeEvent e)
        {
            if(e.getSource()==sliders[0])
            {
                labels[0].setText("                     x1="+sliders[0].getValue()+"                ");
                if(!(ball2.intersects(new Rectangle(sliders[0].getValue(),y1,40,40))))
                    x1=sliders[0].getValue();
            }
            else if(e.getSource()==sliders[1])
            {
                labels[1].setText("                                            y1="+sliders[1].getValue()+"                  ");
                if(!(ball2.intersects(new Rectangle(x1,sliders[1].getValue(),40,40))))
                    y1=sliders[1].getValue();
            }
            else if(e.getSource()==sliders[2])
            {
                labels[2].setText("                     x2="+sliders[2].getValue()+"                ");
                if(!(ball1.intersects(new Rectangle(sliders[2].getValue(),y2,40,40))))
                    x2=sliders[2].getValue();
            }
            else if(e.getSource()==sliders[3])
            {
                labels[3].setText("                                            y2="+sliders[3].getValue()+"                  ");
                if(!(ball1.intersects(new Rectangle(x2,sliders[3].getValue(),40,40))))
                    y2=sliders[3].getValue();
            }   
            else if(e.getSource()==sliders[4])
            {
                labels[4].setText("                     ux1="+sliders[4].getValue()+"                ");
                ux1=sliders[4].getValue();
            }
            else if(e.getSource()==sliders[5])
            {
                labels[5].setText("                                            uy1="+sliders[5].getValue()+"                  ");
                uy1=sliders[5].getValue();
            }
            else if(e.getSource()==sliders[6])
            {
                labels[6].setText("                     ux2="+sliders[6].getValue()+"                ");
                ux2=sliders[6].getValue();
            }
            else if(e.getSource()==sliders[7])
            {
                labels[7].setText("                                            uy2="+sliders[7].getValue()+"                    ");
                uy2=sliders[7].getValue();
            }
        }
    };
    public static void init()
    {
        sliders[0]=new JSlider(0,553,0);
        sliders[1]=new JSlider(0,327,0);
        sliders[2]=new JSlider(0,553,553);
        sliders[3]=new JSlider(0,327,327);
        for(int a=4;a<6;a++)
            sliders[a]=new JSlider(-15,15,10);
        for(int a=6;a<8;a++)
            sliders[a]=new JSlider(-15,15,-10);
        labels[0]=new JLabel("                     x1=0                ");
        labels[1]=new JLabel("                                            y1=0                  ");
        labels[2]=new JLabel("                     x2=553              ");
        labels[3]=new JLabel("                                            y2=327                ");
        labels[4]=new JLabel("                     ux1=10              ");
        labels[5]=new JLabel("                                            uy1=10                ");
        labels[6]=new JLabel("                     ux2=-10             ");
        labels[7]=new JLabel("                                            uy2=-10               ");
        button=new JButton("Start");
        cbox=new JPanel();
        cbox.setLayout(new FlowLayout());
        for(int a=0;a<8;a+=2)
        {
            cbox.add(sliders[a]);
            cbox.add(sliders[a+1]);
            cbox.add(labels[a]);
            cbox.add(labels[a+1]);
        }
        for(int a=0;a<8;a++)
            sliders[a].addChangeListener(cl);
        cbox.add(button);
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(started==false)
                {
                    started=true;
                    button.setText("Stop");
                    for(int a=0;a<8;a++)
                        sliders[a].setEnabled(false);
                    jf.requestFocus();
                }
                else
                {
                    started=false;
                    button.setText("Start");
                    for(int a=0;a<8;a++)
                        sliders[a].setEnabled(true);
                    refresh();
                }
            }
        });
    }
    public static void refresh()
    {
        x1=0;y1=0;x2=553;y2=327;
        ux1=10;uy1=10;ux2=-10;uy2=-10;
        sliders[0].setValue(0);
        sliders[1].setValue(0);
        sliders[2].setValue(553);
        sliders[3].setValue(327);
        for(int a=4;a<6;a++)
            sliders[a].setValue(10);
        for(int a=6;a<8;a++)
            sliders[a].setValue(-10);
        labels[0].setText("                     x1=0                ");
        labels[1].setText("                                            y1=0                  ");
        labels[2].setText("                     x2=553              ");
        labels[3].setText("                                            y2=327                ");
        labels[4].setText("                     ux1=10              ");
        labels[5].setText("                                            uy1=10                ");
        labels[6].setText("                     ux2=-10             ");
        labels[7].setText("                                            uy2=-10                   ");
    }
    public Collisions()
    {
        timer=new Timer(50,this);
        ball1=new Ellipse2D.Double(x1,y1,40,40);
        ball2=new Ellipse2D.Double(x2,y2,40,40);
        timer.start();
    }
    public static void main(String args[])
    {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
        JOptionPane.showMessageDialog(null,"This application demonstrates perfectly elastic collisions in 2D.\n           Thanks to Yash for helping me out with the maths!\n                           Created by Sourish Banerjee.\n                                    Achieved with java."); 
        jf=new JFrame("Collisions");
        JFrame jf2=new JFrame("Control Box");
        JFrame tempJf=new JFrame();
        tempJf.setSize(600,657);
        tempJf.setLocationRelativeTo(null); 
        jf.setSize(600,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocation(tempJf.getX(),tempJf.getY());
        jf2.setSize(450,250);
        jf2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jf2.setLocation(jf.getX()+75,jf.getY()+420);
        jf2.setResizable(false);
        init();
        jf2.add(cbox);
        Collisions obj2=new Collisions();
        jf.add(obj2);
        jf.setVisible(true);
        jf2.setVisible(true); 
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        ball1.setFrame(x1,y1,40,40);
        ball2.setFrame(x2,y2,40,40);
        g2.setPaint(c1);
        g2.fill(ball1);
        g2.setPaint(c2);
        g2.fill(ball2);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(started)
        {
            if(Intersection(ball1,ball2))
            {
                int nx=x2-x1,ny=y2-y1;
                double mn=Math.sqrt(Math.pow(nx,2)+Math.pow(ny,2));
                double unx=nx/mn,uny=ny/mn,utx=-uny,uty=unx;
                double u1n=((ux1*unx)+(uy1*uny)),u1t=((ux1*utx)+(uy1*uty));
                double u2n=((ux2*unx)+(uy2*uny)),u2t=((ux2*utx)+(uy2*uty));
                double v1n=u2n,v1t=u1t;
                double v2n=u1n,v2t=u2t;
                double vx1=((v1n*unx)+(v1t*utx)),vy1=((v1n*uny)+(v1t*uty));
                double vx2=((v2n*unx)+(v2t*utx)),vy2=((v2n*uny)+(v2t*uty));
                ux1=vx1;
                uy1=vy1;
                ux2=vx2;
                uy2=vy2;
                c1=getColor();
                c2=getColor();
            }
            if(x1>553||x1<0)
            {           
                ux1=-ux1;
                c1=getColor();
            }
            if(y1>327||y1<0)
            {   
                uy1=-uy1;
                c1=getColor();
            }
            if(x2>553||x2<0)
            {
                ux2=-ux2;
                c2=getColor();
            }
            if(y2>327||y2<0)
            {
                uy2=-uy2;
                c2=getColor();
            }
            x1+=(int)ux1;
            x2+=(int)ux2;
            y1+=(int)uy1;
            y2+=(int)uy2;
        }
        repaint();
    }
    public static boolean Intersection(Shape shapeA, Shape shapeB) 
    {
        Area area = new Area(shapeA);
        area.intersect(new Area(shapeB));
        return !area.isEmpty();
    }
    public Color getColor()
    {
        int a1=(int)(Math.random()*170);
        int b1=(int)(Math.random()*170);
        int c1=(int)(Math.random()*170);
        return(new Color(a1,b1,c1));
    }
}