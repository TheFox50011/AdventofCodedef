package software.aoc.day6.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay6B {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long calculateGrandTotal(List<String> lines) {
        if (lines.isEmpty()) return 0;
        int maxWidth = lines.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        List<Long> problemResults = new ArrayList<>();
        int col = 0;

        while (col < maxWidth) {
            if (isColumnEmpty(lines, col)) {
                col++;
                continue;
            }

            int startCol = col;
            while (col < maxWidth && !isColumnEmpty(lines, col)) {
                col++;
            }
            int endCol = col;
            problemResults.add(solveBlock(lines, startCol, endCol));
        }

        return problemResults.stream().mapToLong(Long::longValue).sum();
    }

    private static boolean isColumnEmpty(List<String> lines, int col) {
        for (String line : lines) {

            if (col < line.length() && line.charAt(col) != ' ') {
                return false;
            }
        }
        return true;
    }

    private static long solveBlock(List<String> lines, int startCol, int endCol) {
        List<Long> numbers = new ArrayList<>();
        char operator = '?'; // Placeholder


        for (int c = startCol; c < endCol; c++) {
            StringBuilder numBuilder = new StringBuilder();

            for (String line : lines) {
                if (c >= line.length()) continue;

                char ch = line.charAt(c);
                if (Character.isDigit(ch)) {

                    numBuilder.append(ch);
                } else if (ch == '+' || ch == '*') {
                    operator = ch;
                }
            }

            if (numBuilder.length() > 0) {
                numbers.add(Long.parseLong(numBuilder.toString()));
            }
        }

        return applyOperator(numbers, operator);
    }

    private static long applyOperator(List<Long> numbers, char operator) {
        if (numbers.isEmpty()) return 0;

        if (operator == '+') {
            return numbers.stream().mapToLong(Long::longValue).sum();
        } else if (operator == '*') {
            return numbers.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
        } else {
            throw new IllegalStateException("Operador no encontrado o inválido en el bloque");
        }
    }
}