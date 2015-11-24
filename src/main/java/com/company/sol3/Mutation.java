package com.company.sol3;

import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * Created by gguliash on 10/15/15.
 */
public class Mutation implements EvolutionaryOperator<Chromosome> {
    private final NumberGenerator<Probability> mutationProbability;

    public Mutation(Probability mutationProbability) {
        this.mutationProbability = new ConstantGenerator<>(mutationProbability);
    }

    @Override
    public List<Chromosome> apply(List<Chromosome> list, Random random) {
        List<Chromosome> ret = new ArrayList<>(list.size());
        for (Chromosome elem : list) ret.add(mutate(elem, random));
        return ret;
    }

    private Chromosome mutate(Chromosome elem, Random random) {
        Chromosome ret = new Chromosome(elem.genes,elem.lowLimit,elem.uppLimit);

        for (int i = 0; i < ret.genes.length; i++) {
            if (mutationProbability.nextValue().nextEvent(random)) {
                ret.genes[i] = elem.genes[i] + random.nextGaussian() / 2;
                ret.genes[i] = Math.max(0, ret.genes[i]);
            } else {
                ret.genes[i] = elem.genes[i];
            }
        }


        return ret;
    }
}
