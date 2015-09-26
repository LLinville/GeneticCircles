import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleSimulation {
    List<Circle> obstacles = new ArrayList<Circle>();
    Random random = new Random();
    CirclePopulation population = new CirclePopulation();
    double mutationConstant;
    double mutationChance;

    public CircleSimulation(double mutationChance, double mutationConstant){
        this.mutationConstant = mutationConstant;
        this.mutationChance = mutationChance;
    }

    public void add(Circle circle){
        obstacles.add(circle);
    }

    public void add(List<Circle> circles){
        for(Circle circle : circles){
            add(circle);
        }
    }

    public CirclePopulation getPopulation(){ return population; }
    public List<Circle> getObstacles(){ return obstacles; }

    public void initializeRandom(int xlimit, int ylimit, double rlimit, int numObstacles){
        obstacles = new ArrayList<Circle>();
        for(int i=0; i<numObstacles; i++){
            add(new Circle(
                    random.nextInt(xlimit),
                    random.nextInt(ylimit),
                    random.nextDouble()*(rlimit-10)+10));
        }

        population = new CirclePopulation(xlimit,ylimit,rlimit,20);
    }

    public void stepGeneration(){
        CirclePopulation newPopulation = new CirclePopulation();
        newPopulation.add(population.getBest());
        for(int i=0; i<population.size()-1; i++){
            CircleGenome cg1 = population.selectFrom(obstacles);
            while(cg1.getF()<1){
                System.out.println("SelectFrom returned intersecting circle");
                cg1 = population.selectFrom(obstacles);
            }

            CircleGenome cg2 = population.selectFrom(obstacles);
            while(cg2.getF()<1){
                System.out.println("SelectFrom returned intersecting circle");
                cg2 = population.selectFrom(obstacles);
            }

            CircleGenome childCircle = CircleGenome.breed(cg1, cg2);
            childCircle.mutate(mutationChance, mutationConstant);
            childCircle.setF(childCircle.getFitness(obstacles));
            newPopulation.add(childCircle);
        }

        population = newPopulation;

    }
}
