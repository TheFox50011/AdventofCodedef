package software.aoc.day11.auxiliar;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathCounter {
    private final Map<String, List<String>> graph;
    private final Map<String, Long> memo;

    public PathCounter(Map<String, List<String>> graph) {
        this.graph = graph;
        this.memo = new HashMap<>();
    }


    public long countPaths(String start, String end) {
        memo.clear(); return countRecursive(start, end);
    }

    private long countRecursive(String current, String target) {
        if (current.equals(target)) {
            return 1;
        }

        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        long totalPaths = 0;
        List<String> neighbors = graph.getOrDefault(current, Collections.emptyList());

        for (String neighbor : neighbors) {
            totalPaths += countRecursive(neighbor, target);
        }

        memo.put(current, totalPaths);
        return totalPaths;
    }
}