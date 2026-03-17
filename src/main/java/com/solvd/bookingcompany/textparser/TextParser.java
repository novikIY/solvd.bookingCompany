package com.solvd.bookingcompany.textparser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class TextParser {

    public Map<String, Integer> parseText(String inputPath) {

        Map<String, Integer> counts = new TreeMap<>();

        try {
            String text = FileUtils.readFileToString(
                    new File(inputPath),
                    StandardCharsets.UTF_8);

            for (String word : text.toLowerCase().split("[^a-zA-Z']+")) {
                if (StringUtils.isNotEmpty(word)) {
                    counts.merge(word, 1, Integer::sum);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return counts;
    }

    public void saveResult(Map<String, Integer> counts, String outputPath) {

        StringBuilder sb = new StringBuilder();

        counts.forEach((word, count) -> sb.append(word).append(" : ").append(count)
                        .append("\n"));

        try {
            FileUtils.writeStringToFile(
                    new File(outputPath),
                    sb.toString(),
                    StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}