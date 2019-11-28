package com.grofers.javatesting;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 04/11/19
 */
public class StreamOfInt {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7,8,9);


        int sum = list.stream()
                .mapToInt(s -> s)
                .sum();


        System.out.println("Sum is ::"+ sum);

        IntStream.range(0,list.size()).forEach(s -> System.out.println("Hare Krishna") );
    }
}
