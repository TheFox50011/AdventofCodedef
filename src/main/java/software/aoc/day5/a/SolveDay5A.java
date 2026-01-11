package software.aoc.day5.a;

import software.aoc.day5.auxiliar.Range;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay5A {
    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long calculateFreshIngredients(List<String> inputLines) {
        List<Range> freshRanges = new ArrayList<>();
        List<Long> availableIds = new ArrayList<>();

        parseInput(inputLines, freshRanges, availableIds);

        return countFreshIds(freshRanges, availableIds);
    }

    private static void parseInput(List<String> lines, List<Range> rangesOut, List<Long> idsOut) {
        boolean parsingRanges = true;

        for (String line : lines) {
            String trimmed = line.trim();


            if (trimmed.isEmpty()) {
                parsingRanges = false;
                continue;
            }

            if (parsingRanges) {
                rangesOut.add(Range.fromString(trimmed));
            } else {
                idsOut.add(Long.parseLong(trimmed));
            }
        }
    }

    private static long countFreshIds(List<Range> ranges, List<Long> ids) {
        return ids.stream()
                .filter(id -> isFresh(id, ranges))
                .count();
    }

    private static boolean isFresh(long id, List<Range> ranges) {
        return ranges.stream()
                .anyMatch(range -> range.contains(id));
    }
}
