package software.aoc.day4.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

public class SolveDay4A {
    private static final int MAX_NEIGHBORS_FOR_ACCESS = 4;
    private static final char TARGET_CHAR = '@';
    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };

    public static List<String> loadGrid(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static int countAccessibleRolls(List<String> grid) {
        int rows = grid.size();
        int cols = grid.get(0).length();

        return (int) IntStream.range(0, rows)
                .boxed()
                .flatMap(r -> IntStream.range(0, cols).mapToObj(c -> new int[]{r, c})) // Generar coordenadas [r, c]
                .filter(pos -> isAccessible(grid, pos[0], pos[1])) // Filtrar los válidos
                .count(); // Contar
    }

        private static boolean isAccessible(List<String> grid, int r, int c) {
            if (grid.get(r).charAt(c) != TARGET_CHAR) {
                return false;
            }

            return countNeighbors(grid, r, c) < MAX_NEIGHBORS_FOR_ACCESS;
        }

        private static int countNeighbors(List<String> grid, int row, int col) {
            int count = 0;
            for (int[] dir : DIRECTIONS) {
                if (isOccupied(grid, row + dir[0], col + dir[1])) {
                    count++;
                }
            }
            return count;
        }

        private static boolean isOccupied(List<String> grid, int r, int c) {
            if (r < 0 || r >= grid.size() || c < 0 || c >= grid.get(0).length()) {
                return false;
            }
            return grid.get(r).charAt(c) == TARGET_CHAR;
        }
    }
