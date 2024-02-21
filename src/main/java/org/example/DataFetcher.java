package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DataFetcher {
    private final String filePath;

    public DataFetcher(String filePath) {
        this.filePath = filePath;
    }

    public List<Integer> getNums() {
        try {
            return Files.lines(Paths.get(filePath))
                    .map(Integer::parseInt)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
