package org.example;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        DataFetcher fetcher = new DataFetcher("10m.txt");
        List<Integer> nums = fetcher.getNums();
        DataCollector collector = new DataCollector(nums);
        collector.writeToFile("result.txt");
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
    }
}