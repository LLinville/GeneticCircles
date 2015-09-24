import java.awt.*;
import java.awt.event.*;

public class CircleApp extends Frame {
    CircleSimulation csim = new CircleSimulation(0.1,2);

    public CircleApp(){
        super("Genetic Circle Application");
        init();
        addWindowListener(new WindowAdapter()
        {public void windowClosing(WindowEvent e)
            {System.exit(0);}});
        setSize(500,500);
        add("Center", new AppCanvas());
        show();
    }

    class AppCanvas extends Canvas {


        public AppCanvas() {
            addMouseListener(new MouseAdapter()
            {public void mousePressed(MouseEvent evt)
                {
                    csim.stepGeneration();
                    repaint();
                }
            });
        }

        public void paint(Graphics g) {
            Font f = new Font("TimesRoman",Font.BOLD,36);

            g.setColor(Color.BLACK);

            for(Circle c : csim.getObstacles()){
                g.drawOval(
                        c.getX(),
                        c.getY(),
                        (int) Math.round(c.getR()),
                        (int) Math.round(c.getR())
                );
            }

            g.setColor(Color.RED);
            for(CircleGenome c : csim.getPopulation().asList()){
                g.drawOval(
                        (int) Math.round(c.getX()),
                        (int) Math.round(c.getY()),
                        (int) Math.round(c.getR()),
                        (int) Math.round(c.getR())
                );
            }
        }
    }

    public void init() {
        csim.initializeRandom(500,500,100,20);
    }

    public static void main(String[] args){
        new CircleApp();
    }

}