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
        for(int i=0; i<numObstacles; i++){
            add(new Circle(
                    random.nextInt(xlimit),
                    random.nextInt(ylimit),
                    random.nextDouble()*rlimit));
        }

        population = new CirclePopulation(xlimit,ylimit,rlimit,numObstacles);
    }

    public void stepGeneration(){
        CirclePopulation newPopulation = new CirclePopulation();
        for(int i=0; i<population.size(); i++){
            CircleGenome cg1 = population.selectFrom();
            CircleGenome cg2 = population.selectFrom();
            CircleGenome childCircle = CircleGenome.breed(cg1, cg2);
            childCircle.setF(childCircle.getFitness(obstacles));
            childCircle.mutate(mutationChance, mutationConstant);
            newPopulation.add(childCircle);
        }

        population = newPopulation;

    }
}
