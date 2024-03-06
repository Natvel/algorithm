package com.nt.bit.operator;

/**
 * @author Enzo
 * @date   : 2024/3/6
 * 可怜的小猪
 */
public class PoorPig {

    public int poorPigs(int buckets,int minutesToDie,int minutesToTest){

        int k = minutesToTest / minutesToDie;

        return (int) Math.ceil(Math.log(buckets) / Math.log(k + 1));

    }

    public static void main(String[] args) {
        PoorPig poorPig = new PoorPig();

        System.out.println(poorPig.poorPigs(4, 15, 15));
        System.out.println(poorPig.poorPigs(4, 15, 30));
        System.out.println(poorPig.poorPigs(1000, 15, 60));
    }
}

