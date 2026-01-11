package software.aoc.day11.a;

import software.aoc.day11.auxiliar.GraphParser;
import software.aoc.day11.auxiliar.PathCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SolveDay11A {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        Map<String, List<String>> graph = GraphParser.parse(lines);

        PathCounter counter = new PathCounter(graph);
        return counter.countPaths("you", "out");
    }
}
