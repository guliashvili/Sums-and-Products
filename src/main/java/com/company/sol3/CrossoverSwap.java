package com.company.sol3;

import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gguliash on 11/24/15.
 */
public class CrossoverSwap extends AbstractCrossover<Chromosome>{
    public CrossoverSwap(NumberGenerator<Integer> crossoverPointsVariable, NumberGenerator<Probability> crossoverProbabilityVariable) {
        super(crossoverPointsVariable, crossoverProbabilityVariable);
    }

    public CrossoverSwap(int crossoverPoints) {
        super(crossoverPoints);
    }

    public CrossoverSwap(int crossoverPoints, Probability crossoverProbability) {
        super(crossoverPoints, crossoverProbability);
    }

    public CrossoverSwap(NumberGenerator<Integer> crossoverPointsVariable) {
        super(crossoverPointsVariable);
    }
    @Override
    protected List<Chromosome> mate(Chromosome parent1, Chromosome parent2, int numberOfCrossoverPoints, Random rng) {
        List<Chromosome> ret = new ArrayList<>(numberOfCrossoverPoints);
        while (numberOfCrossoverPoints-- > 0){
            Chromosome add = new Chromosome(new double[parent1.genes.length],parent1.lowLimit,parent1.uppLimit);
            for(int i = 0; i < add.genes.length; i++){
                if(rng.nextBoolean()) add.genes[i] = parent1.genes[i];
                else add.genes[i] = parent2.genes[i];
            }
            ret.add(add);
        }
        return ret;
    }
}
