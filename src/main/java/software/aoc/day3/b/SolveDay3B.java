package software.aoc.day3.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SolveDay3B {
    private static final int DIGITS_TO_KEEP = 12;
    public static List<String> LoadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }


    public static long calculateTotalJoltage(List<String> banks) {
        return banks.stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .mapToLong(SolveDay3B::maxJoltageForBank)
                .sum();
    }

    static long maxJoltageForBank(String bank) {
        int digitsToRemove = bank.length() - DIGITS_TO_KEEP;
        StringBuilder stack = new StringBuilder();

        for (char digit : bank.toCharArray()) {
            while (digitsToRemove > 0
                    && !stack.isEmpty()
                    && stack.charAt(stack.length() - 1) < digit) {
                stack.deleteCharAt(stack.length() - 1);
                digitsToRemove--;
            }
            stack.append(digit);
        }

        String result = stack.substring(0, DIGITS_TO_KEEP);
        return Long.parseLong(result);
    }
    }
