package com.company;

/**
 * Created by gguliash on 11/24/15.
 */
public class Sol0 extends SolA implements SolI {

    public Sol0(int[] num) {
        this.num = num;
        count();
    }
    public Sol0(){

    }

    @Override
    public SolInfo generate(int n,long mil,SolInfo si) {
        int[] ar = new int[n];
        for(int i = 0; i < n; i++) ar[i] = i+1;
        Sol0 s = new Sol0(ar);

        return new SolInfo(s.num,s.dif);
    }
}
