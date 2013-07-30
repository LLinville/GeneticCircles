class CircleGenome implements Comparable<CircleGenome>{
  private String xGene;
  private String yGene;
  private double radius;
  
  private int xPos,yPos;
  private static double sMutationConstant=0.01;//probability of a bit mutating
  private double fitness=0;
  
  public CircleGenome(String xG,String yG, double r){
    xGene=xG;
    yGene=yG;
    radius=r;
    xPos=Integer.parseInt(xG,2);
    yPos=Integer.parseInt(yG,2);
  }
  
  public int getX(){return xPos;}
  public int getY(){return yPos;}
  public double getR(){return radius;}
  
  public int compareTo(CircleGenome comp){//return positive if this>comp, 0 if equal, negative if this<comp
    if(radius<comp.getR()) return -1;
    if(radius>comp.getR()) return 1;
    return 0;
  }
  
  public static CircleGenome breed(CircleGenome circle1,CircleGenome circle2){
    //cross over the genes of circle1 and circle2
    //return the crossed over CircleGenome
    return null;
  }
  
  public void mutate(double mutationConstant){
    //mutate bits in xGene with probability mutationConstant
    //mutate bits in xGene with probability mutationConstant
    //mutate radius by adding or subtracting a number from a bell curve centered around radius with sd rMutationConstant
  }
  
  //returns the area if the circle doesn't intersect with any in circles in pop
  //if there is an intersection, return 0
  public double getFitness(Circle[] pop){
    int x1,y1;
    double r1;
    for(Circle cir : pop){
      x1=cir.getX();
      y1=cir.getY();
      r1=cir.getR();
      if(x1==xPos && y1==yPos &&r1==radius){
        return radius*radius*Math.PI;
      }
      if( (radius+r1)*(radius+r1) < (x1-xPos)*(x1-xPos)+(y1-yPos)*(y1-yPos) ){//if they intersect
        return 0;
      }
    }
    return radius;//default to return radius if it doesn't intersect with any circles
  }
}