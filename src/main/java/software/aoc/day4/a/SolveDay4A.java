package software.aoc.day4.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SolveDay4A {
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
        int accessible = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid.get(row).charAt(col) == '@') {
                    int adjacentRolls = countAdjacentRolls(grid, row, col);
                    if (adjacentRolls < 4) {
                        accessible++;
                    }
                }
            }
        }
        return accessible;
    }

    private static int countAdjacentRolls(List<String> grid, int row, int col) {
        int count = 0;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;

                int newRow = row + dr;
                int newCol = col + dc;

                if (isInsideGrid(grid, newRow, newCol)
                        && grid.get(newRow).charAt(newCol) == '@') {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isInsideGrid(List<String> grid, int row, int col) {
        return row >= 0
                && row < grid.size()
                && col >= 0
                && col < grid.get(0).length();
    }
}
