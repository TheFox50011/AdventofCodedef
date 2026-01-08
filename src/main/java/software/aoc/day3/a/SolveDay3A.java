package software.aoc.day3.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SolveDay3A {
    public static List<String> LoadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }


        public static int calculateTotalJoltage(List<String> banks) {
            return banks.stream()
                    .mapToInt(SolveDay3A::maxJoltageForBank)
                    .sum();
        }

        static int maxJoltageForBank(String bank) {
            int length = bank.length();
            return java.util.stream.IntStream.range(0, length - 1)
                    .flatMap(i ->
                            java.util.stream.IntStream.range(i + 1, length)
                                    .map(j ->
                                            (bank.charAt(i) - '0') * 10
                                                    + (bank.charAt(j) - '0')
                                    )
                    )
                    .max()
                    .orElse(0);
        }
    }
