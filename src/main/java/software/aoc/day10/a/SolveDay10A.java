package software.aoc.day10.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SolveDay10A {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        long totalPresses = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            Machine machine = MachineParser.parse(line);
            int presses = findMinPresses(machine);
            if (presses == -1) {
                throw new IllegalStateException("No solution found for machine: " + line);
            }
            totalPresses += presses;
        }
        return totalPresses;
    }

    private static int findMinPresses(Machine machine) {
        long target = machine.targetState();
        long start = 0L; // Lights initially off

        if (start == target) return 0;


        Queue<Long> queue = new ArrayDeque<>();
        queue.add(start);

        Set<Long> visited = new HashSet<>();
        visited.add(start);

        int presses = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            presses++;

            for (int i = 0; i < levelSize; i++) {
                long currentState = queue.poll();

                for (long buttonMask : machine.buttonMasks()) {
                    // Pressing a button toggles bits (XOR)
                    long nextState = currentState ^ buttonMask;

                    if (nextState == target) {
                        return presses;
                    }

                    if (visited.add(nextState)) {
                        queue.add(nextState);
                    }
                }
            }
        }

        return -1;
    }
}
