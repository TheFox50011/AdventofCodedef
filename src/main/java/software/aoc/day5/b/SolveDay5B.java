package software.aoc.day5.b;

import software.aoc.day5.auxiliar.Range;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolveDay5B {

        public static List<String> loadCode(String filePath) {
            try {
                return Files.readAllLines(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error reading input file: " + filePath, e);
            }
        }

        public static long calculateTotalFreshIngredients(List<String> lines) {
            List<Range> ranges = parseRanges(lines);

            Collections.sort(ranges);

            List<Range> mergedRanges = mergeRanges(ranges);

            return calculateTotalSize(mergedRanges);
        }

        private static List<Range> parseRanges(List<String> lines) {
            List<Range> ranges = new ArrayList<>();
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    break;
                }
                ranges.add(Range.fromString(trimmed));
            }
            return ranges;
        }

        private static List<Range> mergeRanges(List<Range> sortedRanges) {
            if (sortedRanges.isEmpty()) return Collections.emptyList();

            List<Range> merged = new ArrayList<>();
            Range current = sortedRanges.get(0);

            for (int i = 1; i < sortedRanges.size(); i++) {
                Range next = sortedRanges.get(i);

                if (current.overlapsOrAdjoins(next)) {

                    current = current.merge(next);
                } else {

                    merged.add(current);
                    current = next;
                }
            }

            merged.add(current);

            return merged;
        }

        private static long calculateTotalSize(List<Range> ranges) {
            return ranges.stream()
                    .mapToLong(Range::size)
                    .sum();
        }
    }
