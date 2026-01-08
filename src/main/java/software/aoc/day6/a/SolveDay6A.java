package software.aoc.day6.a;

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
            // Saltar columnas vacías iniciales (espaciado entre problemas)
            if (isColumnEmpty(lines, col)) {
                col++;
                continue;
            }

            // Encontrar el final del problema actual (primera columna vacía o fin de línea)
            int startCol = col;
            while (col < maxWidth && !isColumnEmpty(lines, col)) {
                col++;
            }
            int endCol = col;

            // Extraer el bloque y añadirlo a la lista
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

            // Extraer el texto de la columna correspondiente al problema
            // Math.min maneja el caso donde la línea es más corta que endCol
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

    // Clase interna para encapsular la lógica de un solo problema
    private static class ProblemBlock {
        private final List<Long> numbers = new ArrayList<>();
        private String operator;

        public ProblemBlock(List<String> tokens) {
            parseTokens(tokens);
        }

        private void parseTokens(List<String> tokens) {
            for (String token : tokens) {
                if (token.equals("+") || token.equals("*")) {
                    this.operator = token;
                } else {
                    this.numbers.add(Long.parseLong(token));
                }
            }
        }

        public long solve() {
            if (numbers.isEmpty()) return 0;
            if ("+".equals(operator)) {
                return numbers.stream().mapToLong(Long::longValue).sum();
            } else if ("*".equals(operator)) {
                return numbers.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
            }
            throw new IllegalStateException("Operador desconocido o faltante: " + operator);
        }
    }
}
