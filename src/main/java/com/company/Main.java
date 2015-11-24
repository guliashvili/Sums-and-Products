package com.company;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static SolInfo[] get(){

        SolInfo[] ret = new SolInfo[1000/40];
        for(int i = 0; i < ret.length; i++) ret[i]= new SolInfo(new int[1],Integer.MAX_VALUE);;
        try{

            File f = new File("a.dat");
            if(!f.exists())
                f.createNewFile();

            String s;
            try(FileReader r = new FileReader(f)){
                try(BufferedReader br = new BufferedReader(r)){
                    s = br.readLine();
                }
            }
            String ech[] = s.split(";");
            for(String e : ech){
                String[] nums = e.split(",");
                int ar[] = new int[nums.length];
                for(int i = 0; i < nums.length; i++) ar[i] = new Integer(nums[i]);
                ret[nums.length/40-1] = new SolInfo(ar);

            }
            return ret;

        }catch (Exception e){
            return ret;
        }

    }
    public static void write(SolInfo[] si){
        File f= new File("a.dat");
        if(f.exists()) {
            int x = 0;
            while(new File("backup"+x+".dat").exists()) x++;
            try {
                FileUtils.copyFile(f, new File("backup" + x + ".dat"));
            }catch (Exception e){

            }
            f.delete();
        }

        try(FileWriter fw = new FileWriter(f)){
            try(BufferedWriter bw = new BufferedWriter(fw)){
                for(SolInfo s : si){
                    bw.append(s.toString());
                }
            }

        }catch (IOException e){

        }


    }


    public static void main(String[] args) {


//
        List<SolI> pipe = new ArrayList<>();
        pipe.add(new Sol0());
        pipe.add(new Sol1());
        pipe.add(new Sol2());


        SolInfo[] ls = get();

        for(int i = 40; i <= 1000; i+= 40){
            int idx = i/40-1;
            SolInfo si = ls[idx];
            for(SolI p : pipe){
                SolInfo candidate = p.generate(i,100000,si);
                candidate.check();
                //System.out.printf("%15d", candidate.getDif());

                if(candidate.getDif() < si.getDif()) {
                    System.out.printf("length %d improvement by %d\n",i,si.getDif() - candidate.getDif());
                    si = candidate;
                }else{
                    System.out.printf("length %d could not improve\n",i,si.getDif() - candidate.getDif());

                }

            }
            ls[idx] = si;
            System.out.println();
        }

        write(ls);
    }
}
