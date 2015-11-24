package com.company;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by gguliash on 11/24/15.
 */
public abstract class SolA {
    protected static Random rnd = new Random();
    protected Map<Long,Integer> mp = new TreeMap<>();
    public int[] num;
    public int dif;

    public int getDif() {
        return dif;
    }

    protected void count(){
        for(int i = 0; i < num.length; i++)
            for(int j = i; j < num.length; j++){
                Integer x = mp.putIfAbsent((long)num[i]+num[j],1);
                Integer y = mp.putIfAbsent(num[i]*1L*num[j],1);
                if(x == null || x == 0) dif++;
                else mp.put((long)num[i]+num[j],mp.get((long)num[i]+num[j])+1);

                if(y == null || y == 0) dif++;
                else mp.put(num[i]*1L*num[j],mp.get(num[i]*1L*num[j])+1);
            }
    }
    public boolean set(int idx,int val){
        for(int a : num) if(a == val) return false;

        for(int i = 0; i < num.length; i++){
            int a = mp.get((long)num[i]+num[idx]);
            int b = mp.get(num[i]*1L*num[idx]);
            if(a == 1){ mp.remove((long)num[i]+num[idx]); dif--;}
            else mp.put((long)num[i]+num[idx],mp.get((long)num[i]+num[idx])-1);

            if(b == 1){ mp.remove(num[i]*1L*num[idx]); dif--;}
            else mp.put(num[i]*1L*num[idx],mp.get(num[i]*1L*num[idx])-1);
        }
        num[idx] = val;

        for(int i = 0; i < num.length; i++){
            Integer x = mp.putIfAbsent((long)num[i]+num[idx],1);
            Integer y = mp.putIfAbsent(num[i]*1L*num[idx],1);
            if(x == null || x == 0) dif++;
            else mp.put((long)num[i]+num[idx],mp.get((long)num[i]+num[idx])+1);

            if(y == null || y == 0) dif++;
            else mp.put(num[i]*1L*num[idx],mp.get(num[i]*1L*num[idx])+1);
        }
        return true;
    }
}
