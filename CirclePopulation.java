import java.util.*;

class CirclePopulation{
    private static Random random = new Random();
    List<CircleGenome> population=new ArrayList<CircleGenome>();

    public CirclePopulation(){}

    public CirclePopulation(double xlimit, double ylimit, double rlimit, int size){
        for(int i=0; i<size; i++){
            population.add(new CircleGenome(
                 random.nextDouble()*xlimit,
                 random.nextDouble()*ylimit,
                 random.nextDouble()*rlimit
            ));

        }
    }

    public void add(CircleGenome circleGenome){
        population.add(circleGenome);
    }

    public int size(){
        return population.size();
    }

    public List<CircleGenome> asList(){ return population; }

    public CircleGenome selectFrom(){
        CircleGenome[] poparray = population.toArray();
        Arrays.sort(population.toArray());

        int size = population.size();
        double totalWeight = size*(size+1)*(2*size+1)/6;//sum of size^2
        int randomIndex = 0;
        double random = Math.random() * totalWeight;

        for (int i = 0; i < size; ++i){//return first time the sum is greater than random
            random -= (i+1)*(i+1);
            if (random <= 0.0){
                System.out.println(i);
                return population.get(size-randomIndex-1);
            }
        }
        return null;
    }

}