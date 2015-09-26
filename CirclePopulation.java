import java.util.*;

class CirclePopulation{
    private static Random random = new Random();
    List<CircleGenome> population=new ArrayList<CircleGenome>();

    public CirclePopulation(){}

    public CirclePopulation(double xlimit, double ylimit, double rlimit, int size){
        for(int i=0; i<size; i++){
            CircleGenome toAdd = new CircleGenome(
                 random.nextDouble()*xlimit,
                 random.nextDouble()*ylimit,
                 random.nextDouble()*rlimit
            );
            toAdd.setF(toAdd.getR());
            population.add(toAdd);

        }
    }

    public void add(CircleGenome circleGenome){
        population.add(circleGenome);
    }

    public int size(){
        return population.size();
    }

    public List<CircleGenome> asList(){ return population; }

    public CircleGenome getBest(){
        CircleGenome[] poparray = population.toArray(new CircleGenome[population.size()]);
        Arrays.sort(poparray);
        System.out.println("Returning best of R: "+poparray[0].getR());
        return poparray[0];
    }

    public CircleGenome selectFrom(List<Circle> obstacles){
        CircleGenome[] poparray = population.toArray(new CircleGenome[population.size()]);
        Arrays.sort(poparray);

        int size = population.size();
        double totalWeight = 0;
        for(int i=0; i<size; i++){
            //totalWeight+=Math.pow(poparray[i].getFitness(obstacles),2);
            //if(poparray[i].getFitness(obstacles) >0.01) totalWeight += i;
            totalWeight+=i;
        }

        double random = Math.random() * totalWeight;

        double total = 0;
        for (int i = 0; i < size; ++i){//return first time the sum is greater than random
            //random -= Math.pow(poparray[i].getFitness(obstacles),2);
            total += i;
            if (total > random){
                //System.out.println(size-i-1);
                return poparray[size-i-1];
            }
        }
        System.out.println("ERROR");
        return null;
    }

}