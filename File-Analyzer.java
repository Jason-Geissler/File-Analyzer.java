package com.geissler.fileAnalyzer;

import java.io.*;
import java.util.*;

public class FileAnalyzer {

    public static void main(String[] args) throws IOException {
        File file = new File("sample.txt");

        int lines = 0;
        int chars = 0;
        Map<String, Integer> wordCount = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines++;
                chars += line.length();

                String[] words = line.toLowerCase().split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        String mostCommon = wordCount.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        System.out.println("Lines: " + lines);
        System.out.println("Characters: " + chars);
        System.out.println("Most common word: " + mostCommon);
    }
}
