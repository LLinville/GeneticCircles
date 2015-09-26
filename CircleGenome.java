import java.util.List;
import java.util.Random;

class CircleGenome implements Comparable<CircleGenome>{
    private double radius;
    private double xPos,yPos;
    private double fitness;

    private static Random random = new Random();

    public CircleGenome(double xG, double yG, double r){
        radius=r;
        xPos=xG;
        yPos=yG;
    }

    public String toString(){
        return "X: " + xPos + "   Y: " + yPos + "   R: " + radius;
    }

    public void setF(double f) {fitness = f;}
    public double getX(){return xPos;}
    public double getY(){return yPos;}
    public double getR(){return radius;}
    public double getF(){return fitness;}

    public int compareTo(CircleGenome comp){//return positive if this>comp, 0 if equal, negative if this<comp
        if(fitness<comp.getF()) return 1;
        if(fitness>comp.getF()) return -1;
        return 0;
    }

    public static CircleGenome breed(CircleGenome circle1,CircleGenome circle2){
        //cross over the genes of circle1 and circle2
        //return the crossed over CircleGenome
        double childX = (circle1.getX()+circle2.getX())/2;
        double childY = (circle1.getY()+circle2.getY())/2;
        double childR = (circle1.getR()+circle2.getR())/2;
        CircleGenome childCircle = new CircleGenome(childX,childY,childR);
        return childCircle;
    }

    public void mutate(double mutationChance, double mutationConstant){
        if(random.nextDouble()<mutationChance) xPos += random.nextGaussian()*mutationConstant;
        if(random.nextDouble()<mutationChance) yPos += random.nextGaussian()*mutationConstant;
        if(random.nextDouble()<mutationChance) radius += random.nextGaussian()*mutationConstant;
        if(radius<0)radius=0;
        //if(random.nextDouble()<mutationChance) radius += 0.1;
        //mutate radius by adding or subtracting a number from a normal distribution centered around radius with sd rMutationConstant
    }

    //returns the area if the circle doesn't intersect with any in circles in obstacles
    //if there is an intersection, return 0
    public double getFitness(List<Circle> obstacles){
        double x1,y1;
        double r1;
        for(Circle cir : obstacles){
            x1=cir.getX();
            y1=cir.getY();
            r1=cir.getR();
//            if(x1==xPos && y1==yPos &&r1==radius){
//                return radius*radius*Math.PI;
//            }
            if( (radius+r1)*(radius+r1) > (x1-xPos)*(x1-xPos)+(y1-yPos)*(y1-yPos) ){//if they intersect
                //System.out.println("\ndistance between: "+((x1-xPos)*(x1-xPos)+(y1-yPos)*(y1-yPos)));
                //System.out.println("sum of radii: " + (radius+r1)*(radius+r1));
                return 0;
            }
            if(xPos-radius<0 || yPos-radius<0 || xPos+radius>500 || yPos+radius>500){
                return 0;
            }
        }
        //System.out.println("Returning fitness >0");
        fitness = radius*radius*Math.PI;
        return radius*radius*Math.PI;//default to return radius if it doesn't intersect with any circles
    }
}