package com.company.sol3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;

/**
 * Created by gguliash on 11/24/15.
 */
public class ChromosomeStripFactory extends AbstractCandidateFactory<Chromosome> {

    private Chromosome ex;
    public ChromosomeStripFactory(Chromosome ex) {
        this.ex = ex;
    }

    @Override
    public Chromosome generateRandomCandidate(Random rng) {
        Chromosome ret = new Chromosome(new double[ex.genes.length],ex.lowLimit,ex.uppLimit);
        for(int i = 0; i < ex.genes.length; i++){
            ret.genes[i] = rng.nextDouble() * (ex.uppLimit[i]-ex.lowLimit[i])+ex.lowLimit[i];
        }
        return ret;
    }
}
