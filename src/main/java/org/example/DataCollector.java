package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataCollector {

    private final List<Integer> nums;
    private final List<Integer> sortedNums;
    private final int size;

    public DataCollector(List<Integer> nums) {
        this.nums = nums;
        sortedNums = nums.stream()
                .sorted()
                .toList();
        size = nums.size();
    }

    public void writeToFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(generateData());
        } catch (IOException ex) {
            throw new RuntimeException("Can't write data to file: " + file, ex);
        }
    }

    private String generateData() {
        return new StringBuilder("Info about sequence")
                .append("\n\tMax number: ").append(getMax())
                .append("\n\tMin number: ").append(getMin())
                .append("\n\tMedian: ").append(getMedian())
                .append("\n\tAverage value: ").append(getAvg())
                .append("\n\tMax increasing subsequence:\n")
                .append(getIncreasingSubsequence().toString("Increasing"))
                .append("\n\tMax descending subsequence:\n")
                .append(getDescendingSubsequence().toString("Descending")).toString();
    }

    private int getMax() {
        return sortedNums.get(size - 1);
    }

    private int getMin() {
        return sortedNums.get(1);
    }

    private double getMedian() {
        int index = size / 2;
        if (size % 2 == 0) {
            int sum = sortedNums.get(index - 1) + sortedNums.get(index);
            return (double) sum / 2;
        } else {
            return nums.get(index);
        }
    }

    private double getAvg() {
        return (double) sortedNums.stream()
                .reduce(0, Integer::sum) / size;
    }

    private Subsequence getIncreasingSubsequence() {
        int maxSize = 1;
        int startOfSubsequence = 0;
        int endOfSubsequence = 1;

        int currentStart = 0;
        int currentEnd = 0;
        int currentSize = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) >= nums.get(i - 1)) {
                currentSize++;
            } else {
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                    startOfSubsequence = currentStart;
                    endOfSubsequence = currentEnd;
                }
                currentStart = i;
                currentSize = 1;
            }
            currentEnd = i;
        }
        if (currentSize > maxSize) {
            maxSize = currentSize;
            startOfSubsequence = currentStart;
            endOfSubsequence = currentEnd;
        }
        return new Subsequence(startOfSubsequence, endOfSubsequence, maxSize);
    }

    private Subsequence getDescendingSubsequence() {
        int maxSize = 1;
        int startOfSubsequence = 0;
        int endOfSubsequence = 1;

        int currentStart = 0;
        int currentEnd = 0;
        int currentSize = 1;
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) <= nums.get(i - 1)) {
                currentSize++;
            } else {
                if (currentSize > maxSize) {
                    maxSize = currentSize;
                    startOfSubsequence = currentStart;
                    endOfSubsequence = currentEnd;
                }
                currentStart = i;
                currentSize = 1;
            }
            currentEnd = i;
        }
        if (currentSize > maxSize) {
            maxSize = currentSize;
            startOfSubsequence = currentStart;
            endOfSubsequence = currentEnd;
        }
        return new Subsequence(startOfSubsequence, endOfSubsequence, maxSize);
    }


    private static class Subsequence {
        int start;
        int end;
        int size;

        public Subsequence(int start, int end, int size) {
            this.start = start;
            this.end = end;
            this.size = size;
        }

        public String toString(String typeOfSubsequence) {
            return '\t' + typeOfSubsequence + " subsequence {"
                    + "\n\t\tstart=" + start
                    + "\n\t\tend=" + end
                    + "\n\t\tsize=" + size
                    + "\n\t}";
        }
    }
}
