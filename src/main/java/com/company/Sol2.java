package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gguliash on 11/24/15.
 */
public class Sol2 extends SolA implements SolI {
    private int PR_LESS = 10;
    private int LIM = 50000000;
    private List<Integer> numbers = new ArrayList<>();
    private final int[] tab = new int[LIM+10];
   private void generate(int n){
        List<Integer> pr = new ArrayList<>();
       PR_LESS = (int)Math.log(n)*2;
        for(int i =1; i <= PR_LESS ; i++) if(new BigInteger(i+"").isProbablePrime(1000)) pr.add(i);

        for(int i=1; i <= LIM; i++){
            int a = i;
            for(int p:pr) while (a%p == 0) a/=p;
            if(a == 1) {
                tab[i] = numbers.size();
                numbers.add(i);
            }
        }

    }


    public Sol2(){

    }
    public Sol2(int n,long mil){

        generate(n);
        num = new int[n];
        for(int i = 0; i < n; i++) num[i]=numbers.get(i);
        count();
        evolut1(mil);

    }
    public void evolut1(long milSec){
        long e = System.currentTimeMillis() + milSec;
        int mx = 0;
        for(int a : num) mx = Math.max(mx,a);

        while(System.currentTimeMillis() < e){


            int piv = rnd.nextInt(num.length);
            int val = num[piv];

            int rn,cc;
            cc = dif;
            do{
                rn = Math.max(1,(int)rnd.nextGaussian() * mx/20);

            }while (set(piv,rn));
            if(dif < cc){
                //new SolInfo(num,dif).check();
                //System.out.println("wow");
            }else{
                set(piv,val);
                new SolInfo(num,dif).check();
            }
        }
    }
    @Override
    public SolInfo generate(int n, long mil,SolInfo si) {
        Sol2 s = new Sol2(n,mil);

        return new SolInfo(s.num,s.dif);
    }
}
