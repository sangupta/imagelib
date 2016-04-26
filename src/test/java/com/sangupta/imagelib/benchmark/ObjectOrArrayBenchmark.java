/**
 *
 * imagelib - Simple image library
 * Copyright (c) 2015-2016, Sandeep Gupta
 * 
 * http://sangupta.com/projects/imagelib
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
 

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