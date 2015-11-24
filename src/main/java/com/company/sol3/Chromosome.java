package com.company.sol3;

import com.company.SolInfo;

/**
 * Created by gguliash on 11/24/15.
 */
public class Chromosome {
    public double[] genes;
    public double[] lowLimit;
    public double[] uppLimit;

    public SolInfo res = null;

    public Chromosome(double[] genes, double[] lowLimit, double[] uppLimit) {
        this.genes = genes;
        this.lowLimit = lowLimit;
        this.uppLimit = uppLimit;
    }

    @Override
    public String toString() {
        return genes[0] + " " + genes[1] + " " + genes[2];
    }
}
