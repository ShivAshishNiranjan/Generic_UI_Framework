package com.grofers.javatesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 04/11/19
 */
public class IntStream {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7,8,9);


        int sum = list.stream()
                .mapToInt(s -> s)
                .sum();


        System.out.println("Sum is ::"+ sum);


    }
}
