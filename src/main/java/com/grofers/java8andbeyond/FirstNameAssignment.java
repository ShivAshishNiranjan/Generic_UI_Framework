package com.grofers.java8andbeyond;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
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

        long count = list.stream().
                filter(s -> s.startsWith("B"))
                .count();
        System.out.println("Count of Names Starting with B ->" + count);

        List<String> output = list.stream()
                .filter(s -> s.startsWith("C") && s.contains("s"))
                .collect(Collectors.toList());
        System.out.println("Names Starting with C and contains s ->" + output);


        long totalCount = list.stream()
                .filter(s -> s.startsWith("M"))
                .map(s -> s.trim())
                .map(s -> s.length())
                .mapToInt(i -> i)
                .count();

        System.out.println("Total Count of Char for Give Condition is : "+ totalCount );

    }
}
