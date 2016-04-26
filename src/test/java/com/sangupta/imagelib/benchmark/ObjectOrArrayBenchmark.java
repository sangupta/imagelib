package com.sangupta.imagelib.benchmark;

import java.util.concurrent.TimeUnit;

public class ObjectOrArrayBenchmark {

    private static final int MAX_ELEMENTS = 1000 * 1000;
    
    private static final int MAX_RUNS = 10;
    
    public static void main(String[] args) {
        // warm up the JVM
        for(int index = 0; index < 3; index++) {
            arrayCheck();
            objectCheck();
        }
        
        // run actual check
        final long as = System.nanoTime();
        for(int index = 0; index < MAX_RUNS; index++) {
            arrayCheck();
        }
        final long ae = System.nanoTime();
        
        final long os = System.nanoTime();
        for(int index = 0; index < 10; index++) {
            objectCheck();
        }
        final long oe = System.nanoTime();
        
        System.out.println("Time with arrays: " + getTime(ae, as) + " for a run of " + MAX_ELEMENTS + " elements");
        System.out.println("Time with objects: " + getTime(oe, os) + " for a run of " + MAX_ELEMENTS + " elements");
    }

    private static String getTime(long end, long start) {
        long nanos = end - start;
        long time = TimeUnit.NANOSECONDS.toMillis(nanos);
        return String.valueOf(time);
    }

    private static void objectCheck() {
        for(int j = 0; j < MAX_ELEMENTS; j++) {
            getObject();
        }
    }
    
    private static Dimensions getObject() {
        return new Dimensions(2, 2);
    }

    private static void arrayCheck() {
        for(int j = 0; j < MAX_ELEMENTS; j++) {
            getArray();
        }
    }
    
    private static int[] getArray() {
        return new int[] { 2, 2 };
    }
    
    private static class Dimensions {
        
        int width;
        
        int height;

        Dimensions(int w, int h) {
            this.width = w;
            this.height = h;
        }
    }
    
}
