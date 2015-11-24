package com.company;

import java.math.BigInteger;
import java.util.TreeSet;

/**
 * Created by gguliash on 11/24/15.
 */
public class SolInfo implements Comparable<SolInfo> {
    private Object[] a = null;
    public int[] b = null;
    private long[] c = null;
    private int dif;
    public SolInfo(Object[] num,int dif){
        a = num;
        this.dif = dif;
    }
    public SolInfo(int[] num,int dif){
        b = num;
        this.dif = dif;
    }
    public SolInfo(int[] num){
        b = num;
        this.dif = compDif();
    }
    public SolInfo(long[] num,int dif){
        c = num;
        this.dif = dif;
    }
    public int compDif(){

        if(b != null) {
            TreeSet<Long> ts = new TreeSet<>();
            for (int x : b)
                for (int y : b) {
                    ts.add((long) x + y);
                    ts.add(x * 1L * y);
                }
            return ts.size();
        }else if(c != null){
            TreeSet<BigInteger> ts = new TreeSet<>();
            for (long x : c)
                for (long y : b) {
                    ts.add(new BigInteger(x+"").add(new BigInteger(y+"")));
                    ts.add(new BigInteger(x+"").multiply(new BigInteger(y+"")));
                }
            return ts.size();
        }else{
            throw  new RuntimeException("none");
        }
    }
    public void check(){

        if(compDif() != dif) throw  new RuntimeException("incorrect dif");
        if(b != null) {
            TreeSet<Integer> nums = new TreeSet<>();
            for(int x : b) {
                if(nums.contains(x)) throw  new RuntimeException("equal number");
                nums.add(x);
                if(x <= 0)throw  new RuntimeException("non natural integer");
            }
        }else if(c != null){

            TreeSet<Long> nums = new TreeSet<>();
            for(long x : c) {
                if(nums.contains(x)) throw new RuntimeException("equal number");
                nums.add(x);
                if(x <= 0)throw  new RuntimeException("non natural integer");
            }
        }

    }

    public int getDif() {
        return dif;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();

        if(a != null){
            for(int i = 0; i < a.length; i++){
                sb.append(a[i].toString());
                if(i + 1 != a.length) sb.append(",");
            }
            if(a.length != 1000) sb.append(";");
        }else if(b != null){
            for(int i = 0; i < b.length; i++){
                sb.append(b[i]);
                if(i + 1 != b.length) sb.append(",");
            }
            if(b.length != 1000) sb.append(";");
        }else if(c != null){
            for(int i = 0; i < c.length; i++){
                sb.append(c[i]);
                if(i + 1 != c.length) sb.append(",");
            }
            if(c.length != 1000) sb.append(";");
        }else{
            throw  new RuntimeException("all is null");
        }


        return  sb.toString();
    }



    @Override
    public int compareTo(SolInfo o) {
        return this.dif - o.dif;
    }
}
