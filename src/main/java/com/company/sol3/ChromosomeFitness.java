package com.company.sol3;

import com.company.SolInfo;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

/**
 * Created by gguliash on 11/24/15.
 */
public class ChromosomeFitness implements FitnessEvaluator<Chromosome> {
    private int n;
    private long tm;


    public ChromosomeFitness(int n,long tm) {
        this.n = n;
        this.tm = tm;
    }

    @Override
    public double getFitness(Chromosome candidate, List<? extends Chromosome> population) {
        Sol3 ans = new Sol3(n,tm,candidate);
        candidate.res = new SolInfo(ans.num,ans.dif);
        return candidate.res.getDif();
    }

    @Override
    public boolean isNatural() {
        return false;
    }
}
