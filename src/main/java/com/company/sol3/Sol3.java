package com.company.sol3;

import com.company.SolA;
import com.company.SolI;
import com.company.SolInfo;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.RingMigration;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.*;
import org.uncommons.watchmaker.framework.termination.ElapsedTime;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gguliash on 11/24/15.
 */
public class Sol3 extends SolA implements SolI {

    private int LIM = 50000000;
    private void generate(List<Integer> numbers,int n,double PR_LESS){
        List<Integer> pr = new ArrayList<>();
        for(int i =1; i <= PR_LESS ; i++) if(new BigInteger(i+"").isProbablePrime(1000)) pr.add(i);

        for(int i=1; i <= LIM && numbers.size() < n; i++){
            int a = i;
            for(int p:pr) {
                while (a%p == 0) a/=p;
                if(a == 1) break;
            }
            if(a == 1) {
                numbers.add(i);
            }
        }

    }


    public Sol3(){

    }
    public Sol3(int n, long mil, Chromosome chromosome){//,double PR_LESS,double gausMean,double gausVar){
        long e = System.currentTimeMillis() + mil;

        List<Integer> numbers = new ArrayList<>(n);
        generate(numbers,n,chromosome.genes[0]);
        num = new int[n];
        for(int i = 0; i < Math.min(numbers.size(),n); i++) num[i]=numbers.get(i);
        for(int i = numbers.size(); i < n; i++) {
            num[i] = numbers.get(numbers.size() -1) + i - numbers.size() + 1;
        }
        count();
        evolut1(e,mil,chromosome.genes[1],chromosome.genes[2]);

    }
    public void evolut1(long e,long milSec,double gausMean,double gausVar){

        int mx = 0;
        for(int a : num) mx = Math.max(mx,a);

        while(System.currentTimeMillis() < e){


            int piv = rnd.nextInt(num.length);
            int val = num[piv];

            int rn,cc;
            cc = dif;
            do{
                rn = Math.max(1,(int)(rnd.nextGaussian() * gausVar + gausMean));

            }while (set(piv,rn));
            if(dif < cc){
                //new SolInfo(num,dif).check();
                //System.out.println("wow");
            }else{
                set(piv,val);
               // new SolInfo(num,dif).check();
            }
        }
    }

    @Override
    public SolInfo generate(int n, long mil,SolInfo si) {
        double[] low = new double[3];
        double[] high = new double[3];
        low[0] = 2;high[0] = 100;
        low[1] = -n;
        high[1] = n;
        low[2] = 0;
        high[2] = n;

        Chromosome chromosome = new Chromosome(new double[3],low,high);



        List<EvolutionEngine<Chromosome>> engines = new ArrayList<>();


        List<EvolutionaryOperator<Chromosome>> operators = new ArrayList<>();
        operators.add(new CrossoverMerge(1));
        operators.add(new CrossoverSwap(2));
        operators.add(new Mutation(new Probability(0.1)));
        operators.add(new Mutation(new Probability(0.9)));

        EvolutionaryOperator<Chromosome> pipeline
                = new EvolutionPipeline<>(operators);

        FitnessEvaluator<Chromosome> fitnessEvaluator = new ChromosomeFitness(n,60*1000);


        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new RouletteWheelSelection(),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new RankSelection(),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new RankSelection(),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new SigmaScaling(),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new StochasticUniversalSampling(),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new TournamentSelection(new Probability(0.9)),
                new MersenneTwisterRNG()
        ));
        engines.add(new GenerationalEvolutionEngine<Chromosome>(
                new ChromosomeStripFactory(chromosome),
                pipeline,
                fitnessEvaluator,
                new TruncationSelection(0.1),
                new MersenneTwisterRNG()
        ));


        IslandEvolution<Chromosome> engine
                = new IslandEvolution<Chromosome>(engines,
                new RingMigration(),
                false, // Natural fitness?
                new MersenneTwisterRNG());



        Chromosome result = engine.evolve(20, 2, 3, 2, new ElapsedTime(mil));

        SolInfo ret = result.res;
        ret.setObj(result);

        return ret;
    }
}
