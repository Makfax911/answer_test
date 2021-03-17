package com.javarush.task.task19.task1920;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/*
Самый богатый
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<String, Double> tm = new TreeMap<String, Double>();
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        while (file.ready()) {
            String[] fields = file.readLine().split(" ");
            Double a = Double.parseDouble(fields[1]);
            Double value = tm.get(fields[0]);
            tm.put(fields[0], (value == null)? a : value + a);
        }
        file.close();

        // looking for a rich
        double max = 0;
        for (Map.Entry<String, Double> item : tm.entrySet())
            if (item.getValue() > max)
                max = item.getValue();

        // output
        for (Map.Entry<String, Double> item : tm.entrySet())
            if (item.getValue() == max)
                System.out.println(item.getKey());
    }
}