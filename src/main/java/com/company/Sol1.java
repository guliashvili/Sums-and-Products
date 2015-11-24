package com.company;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by gguliash on 11/24/15.
 */
public class Sol1 extends SolA implements SolI {

    public Sol1(int[] num,long mil){
        this.num = num;
        count();
        evolut1(mil);
    }
    public void evolut1(long milSec){
        long e = System.currentTimeMillis() + milSec;

        while(System.currentTimeMillis() < e){
            int mx = 0;
            for(int a : num) mx = Math.max(mx,a);

            int piv = rnd.nextInt(num.length);
            int val = num[piv];
            int rn = rnd.nextInt(mx/2+1) + mx + 1;
            int cc = dif;
            set(piv,rn);
            if(dif < cc){
                //new SolInfo(num,dif).check();
                //System.out.println("wow");
            }else{
                set(piv,val);
                new SolInfo(num,dif).check();
            }
        }
    }
    public  Sol1(){

    }


    @Override
    public SolInfo generate(int n,long mil,SolInfo si) {
        int[] ar = new int[n];
        if(si.b.length == n) ar = si.b;
        else for(int i = 0; i < n; i++) ar[i] = i+1;
        Sol1 s = new Sol1(ar,mil);

        return new SolInfo(s.num,s.dif);
    }
}
