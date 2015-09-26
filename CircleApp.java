import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CircleApp extends Frame {
    CircleSimulation csim = new CircleSimulation(0.2,5);

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
                    csim.initializeRandom(500,500,10,20);
                }
            });
            int delay = 5; //milliseconds
            ActionListener taskPerformer = new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    csim.stepGeneration();
                    System.out.println("Best guess: " + csim.population.getBest().getR());
                    repaint();
                }
            };
            new Timer(delay, taskPerformer).start();
        }

        public void paint(Graphics g) {
            Font f = new Font("TimesRoman",Font.BOLD,36);

            g.setColor(Color.BLACK);

            for(Circle c : csim.getObstacles()){
                g.drawOval(
                        (int) Math.round(c.getX()-c.getR()),
                        (int) Math.round(c.getY()-c.getR()),
                        (int) Math.round(c.getR()),
                        (int) Math.round(c.getR())
                );
            }

            g.setColor(Color.RED);
            for(CircleGenome c : csim.getPopulation().asList()){
                g.drawOval(
                        (int) Math.round(c.getX()-c.getR()),
                        (int) Math.round(c.getY()-c.getR()),
                        (int) Math.round(c.getR()),
                        (int) Math.round(c.getR())
                );
            }

            g.setColor(Color.GREEN);
            double bestR=csim.getPopulation().getBest().getR();
            g.drawOval(
                    (int) Math.round(csim.getPopulation().getBest().getX()-bestR),
                    (int) Math.round(csim.getPopulation().getBest().getY()-bestR),
                    (int) Math.round(bestR),
                    (int) Math.round(bestR)
            );
        }
    }

    public void init() {
        csim.initializeRandom(500,500,100,2);
    }

    public static void main(String[] args){
        new CircleApp();
        while(true){
            CircleApp.getFrames()[0].repaint();
        }
    }

}