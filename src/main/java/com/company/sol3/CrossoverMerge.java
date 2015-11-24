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
public class CrossoverMerge extends AbstractCrossover<Chromosome>{
    public CrossoverMerge(NumberGenerator<Integer> crossoverPointsVariable, NumberGenerator<Probability> crossoverProbabilityVariable) {
        super(crossoverPointsVariable, crossoverProbabilityVariable);
    }

    public CrossoverMerge(int crossoverPoints) {
        super(crossoverPoints);
    }

    public CrossoverMerge(int crossoverPoints, Probability crossoverProbability) {
        super(crossoverPoints, crossoverProbability);
    }

    public CrossoverMerge(NumberGenerator<Integer> crossoverPointsVariable) {
        super(crossoverPointsVariable);
    }
    @Override
    protected List<Chromosome> mate(Chromosome parent1, Chromosome parent2, int numberOfCrossoverPoints, Random rng) {
        List<Chromosome> ret = new ArrayList<>(numberOfCrossoverPoints);
        while (numberOfCrossoverPoints-- > 0){
            Chromosome add = new Chromosome(new double[parent1.genes.length],parent1.lowLimit,parent1.uppLimit);
            for(int i = 0; i < add.genes.length; i++){
                double d = rng.nextDouble();
                add.genes[i] = Math.min(add.uppLimit[i],Math.max(add.lowLimit[i],parent1.genes[i]*d + parent2.genes[i]*(1-d)));
            }
            ret.add(add);
        }
        return ret;
    }
}
