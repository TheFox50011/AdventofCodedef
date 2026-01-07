package software.aoc.day2.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SolveDay2B {
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

        for (int blockSize = 1; blockSize <= length / 2; blockSize++) {
            if (length % blockSize != 0) {
                continue;
            }

            String block = value.substring(0, blockSize);
            int repetitions = length / blockSize;

            StringBuilder repeated = new StringBuilder();
            for (int i = 0; i < repetitions; i++) {
                repeated.append(block);
            }

            if (repeated.toString().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
