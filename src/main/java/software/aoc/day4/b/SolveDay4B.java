package software.aoc.day4.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolveDay4B {
    private static final char ROLL_CHAR = '@';
    private static final char EMPTY_CHAR = '.';
    private static final int MAX_NEIGHBORS_TO_SURVIVE = 4;
    private static final int[][] NEIGHBOR_DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
    };
    public static List<String> LoadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static int calculateTotalRolls(List<String> input) {
        if (input == null || input.isEmpty()) return 0;
        char[][] grid = initializeGrid(input);
        int totalRemoved = 0;

        while (true) {
            List<int[]> itemsToRemove = identifyRemovableItems(grid);

            if (itemsToRemove.isEmpty()) {
                break;
            }

            removeItems(grid, itemsToRemove);

            totalRemoved += itemsToRemove.size();
        }

        return totalRemoved;
    }

    private static char[][] initializeGrid(List<String> input) {
        int rows = input.size();
        int cols = input.get(0).length();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = input.get(i).toCharArray();
        }
        return grid;
    }


    private static List<int[]> identifyRemovableItems(char[][] grid) {
        List<int[]> toRemove = new ArrayList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (shouldBeRemoved(grid, r, c)) {
                    toRemove.add(new int[]{r, c});
                }
            }
        }
        return toRemove;
    }

    private static void removeItems(char[][] grid, List<int[]> itemsToRemove) {
        for (int[] pos : itemsToRemove) {
            grid[pos[0]][pos[1]] = EMPTY_CHAR;
        }
    }


    private static boolean shouldBeRemoved(char[][] grid, int r, int c) {
        if (grid[r][c] != ROLL_CHAR) {
            return false;
        }
        return countNeighbors(grid, r, c) < MAX_NEIGHBORS_TO_SURVIVE;
    }

    private static int countNeighbors(char[][] grid, int r, int c) {
        return (int) Arrays.stream(NEIGHBOR_DIRECTIONS)
                .filter(dir -> isOccupied(grid, r + dir[0], c + dir[1]))
                .count();
    }

    private static boolean isOccupied(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return false;
        }
        return grid[r][c] == ROLL_CHAR;
    }
    }
