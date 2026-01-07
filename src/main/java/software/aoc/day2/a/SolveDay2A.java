package software.aoc.day2.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SolveDay2A {
    public static String loadInput(String filePath) {
        try {
            return Files.readString(Path.of(filePath)).trim();
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long calculateInvalidIdsSum(String input) {
        String[] ranges = input.split(",");
        long sum = 0;

        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long id = start; id <= end; id++) {
                if (isInvalidId(id)) {
                    sum += id;
                }
            }
        }
        return sum;
    }

    private static boolean isInvalidId(long id) {
        String value = Long.toString(id);
        int length = value.length();

        if (length % 2 != 0) {
            return false;
        }

        int half = length / 2;
        String first = value.substring(0, half);
        String second = value.substring(half);

        return first.equals(second);
    }
}
