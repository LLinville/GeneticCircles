import java.util.*;

class CirclePopulation{
  public static int size=10;
  CircleGenome[] population=new CircleGenome[size];
  boolean isSorted=false;
  
  public CircleGenome getMember(int n){
    return population[n];
  }
  
  public void setMember(int n,CircleGenome cir){
    population[n]=cir;
  }
  
  public CircleGenome selectFrom(){
    if(!isSorted) Arrays.sort(population);
    
    
    double totalWeight = size*(size+1)*(2*size+1)/6;//sum of size^2
    int randomIndex = 0;
    double random = Math.random() * totalWeight;
    
    for (int i = 0; i < size; ++i){//return first time the sum is greater than random
      random -= (i+1)*(i+1);
      if (random <= 0.0){
        return population[size-randomIndex];
      }
    }
    return null;
  }
    
}