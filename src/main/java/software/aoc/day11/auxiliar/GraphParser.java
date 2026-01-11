package software.aoc.day11.auxiliar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphParser {
    public static Map<String, List<String>> parse(List<String> lines) {
        Map<String, List<String>> graph = new HashMap<>();

        for (String line : lines) {
            if (line.isBlank()) continue;

            String[] parts = line.split(":");
            String source = parts[0].trim();

            String[] destinations = parts[1].trim().split("\\s+");

            graph.put(source, Arrays.asList(destinations));
        }
        return graph;
    }
}