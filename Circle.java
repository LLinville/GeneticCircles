class Circle implements Comparable<Circle>{
  private int x,y;
  private double r;
  
  public Circle(int x1,int y1,double r1){
    x=x1;
    y=y1;
    r=r1;
  }
  
  public int getX(){return x;}
  public int getY(){return y;}
  public double getR(){return r;}
  public void setR( int r ){this.r=r;}
  
  public int compareTo(Circle cir){
    if(r>cir.getR()) return 1;
    if(r<cir.getR()) return -1;
    return 0;
  }
  
  public void print(){
    System.out.println("x: "+x+"\ty: "+y+"\tr: "+r);
  }
  

  
}