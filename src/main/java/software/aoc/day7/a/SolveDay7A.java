package software.aoc.day7.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveDay7A {


        public static List<String> loadCode(String filePath) {
            try {
                return Files.readAllLines(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error reading input file: " + filePath, e);
            }
        }

    public static long calculateTotalSplits(List<String> grid) {
        if (grid == null || grid.isEmpty()) return 0;

        int cols = grid.get(0).length();
        Set<Integer> activeBeams = findStartColumns(grid.get(0));

        long totalSplits = 0;

        for (String row : grid) {
            Set<Integer> nextRowBeams = new HashSet<>();

            for (int col : activeBeams) {
                if (col < 0 || col >= cols) continue;

                char cell = row.charAt(col);


                switch (cell) {
                    case '^' -> {
                        totalSplits++;
                        if (col - 1 >= 0) nextRowBeams.add(col - 1);
                        if (col + 1 < cols) nextRowBeams.add(col + 1);
                    }
                    default -> nextRowBeams.add(col);
                }
            }

            activeBeams = nextRowBeams;
            if (activeBeams.isEmpty()) break;
        }

        return totalSplits;
    }

        private static Set<Integer> findStartColumns(String firstRow) {
            Set<Integer> starts = new HashSet<>();
            for (int i = 0; i < firstRow.length(); i++) {
                if (firstRow.charAt(i) == 'S') {
                    starts.add(i);
                }
            }
            return starts;
        }
    }
