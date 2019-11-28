package com.grofers.java8andbeyond;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : shiv.ashish@grofers.com
 * Contact me on san.mnnit11@gmail.com or 8105234517 for any query
 * file created on 28/11/19
 */
public class FirstNameAssignment {
    public static void main(String[] args) throws IOException {


        Path path = Paths.get("/Users/grofers/Desktop/AAA_myGitRepo/Generic_UI_Framework/src/main/java/com/grofers/java8andbeyond/first-names.txt");
        List<String> list = Files.readAllLines(path);

        // find the count of names starting with B
        long count = list.stream().
                filter(s -> s.startsWith("B"))
                .count();
        System.out.println("Count of Names Starting with B ->" + count);

        // find the names which starts with C and contains s in it and print the list and count
        List<String> output = list.stream()
                .filter(s -> s.startsWith("C") && s.toLowerCase().contains("s"))
                .collect(Collectors.toList());
        System.out.println("Names Starting with C and contains s ->" + output);
        System.out.println("Count of Names Starting with C and contains s ->" + output.size());

        // print the total number of chars for all the names starting with M
        long totalCount = list.stream()
                .filter(s -> s.startsWith("M"))
                .map(String::trim)
                .map(String::length)
                .mapToInt(i -> i)
                .sum();

        System.out.println("Total Count of Char for Given Condition is : " + totalCount);

        // find the names containing - in it and replace it with space : collect them to a list
        //find the name which has more number of chars

        List<String> names = list.stream()
                .filter(s -> s.contains("-"))
                .map(s -> s.replaceAll("-", " "))
                .collect(Collectors.toList());

        System.out.println("Names for Given Condition is : " + names);

        //find the name which has more number of chars

        System.out.println(
                list.stream()
                        .max(Comparator.comparing(s -> s.length()))
                        .get()
        );
    }
}
