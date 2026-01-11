package software.aoc.day6.a;

import software.aoc.day6.auxiliar.ProblemBlock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay6A {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long calculateGrandTotal(List<String> lines) {
        List<ProblemBlock> problems = parseProblems(lines);
        return problems.stream()
                .mapToLong(ProblemBlock::solve)
                .sum();
    }

    private static List<ProblemBlock> parseProblems(List<String> lines) {
        List<ProblemBlock> problems = new ArrayList<>();
        int maxWidth = lines.stream().mapToInt(String::length).max().orElse(0);

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
            problems.add(extractBlock(lines, startCol, endCol));
        }
        return problems;
    }

    private static boolean isColumnEmpty(List<String> lines, int colIndex) {
        for (String line : lines) {
            if (colIndex < line.length() && line.charAt(colIndex) != ' ') {
                return false;
            }
        }
        return true;
    }

    private static ProblemBlock extractBlock(List<String> lines, int startCol, int endCol) {
        List<String> tokens = new ArrayList<>();
        for (String line : lines) {
            if (startCol >= line.length()) continue;

            int end = Math.min(line.length(), endCol);
            if (startCol < end) {
                String token = line.substring(startCol, end).trim();
                if (!token.isEmpty()) {
                    tokens.add(token);
                }
            }
        }
        return new ProblemBlock(tokens);
    }
}
