package software.aoc.day10.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SolveDay10B {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        long totalPresses = 0;
        for (String line : lines) {
            if (line.isBlank()) continue;
            Machine machine = MachineParser.parse(line);
            long presses = calculateMinPresses(machine);
            if (presses == -1) {
                System.err.println("Failed machine: " + line);
                throw new IllegalStateException("No valid configuration found for machine: " + line);
            }
            totalPresses += presses;
        }
        return totalPresses;
    }

    private static long calculateMinPresses(Machine machine) {
        int rows = machine.targets().size();
        int cols = machine.buttons().size();

        double[][] matrix = new double[rows][cols + 1];

        for (int r = 0; r < rows; r++) {
            matrix[r][cols] = machine.targets().get(r);
            for (int c = 0; c < cols; c++) {
                List<Integer> affectedCounters = machine.buttons().get(c);
                if (affectedCounters.contains(r)) {
                    matrix[r][c] = 1.0;
                } else {
                    matrix[r][c] = 0.0;
                }
            }
        }

        gaussianElimination(matrix, rows, cols);

        int searchLimit = machine.targets().stream().mapToInt(Integer::intValue).max().orElse(100);

        return findBestIntegerSolution(matrix, rows, cols, searchLimit);
    }

    private static void gaussianElimination(double[][] M, int rows, int cols) {
        int pivotRow = 0;
        for (int col = 0; col < cols && pivotRow < rows; col++) {
            int maxRow = pivotRow;
            for (int i = pivotRow + 1; i < rows; i++) {
                if (Math.abs(M[i][col]) > Math.abs(M[maxRow][col])) {
                    maxRow = i;
                }
            }

            if (Math.abs(M[maxRow][col]) < 1e-9) {
                continue;
            }

            double[] temp = M[pivotRow];
            M[pivotRow] = M[maxRow];
            M[maxRow] = temp;

            double pivotVal = M[pivotRow][col];
            for (int j = col; j <= cols; j++) {
                M[pivotRow][j] /= pivotVal;
            }

            for (int i = 0; i < rows; i++) {
                if (i != pivotRow) {
                    double factor = M[i][col];
                    for (int j = col; j <= cols; j++) {
                        M[i][j] -= factor * M[pivotRow][j];
                    }
                }
            }
            pivotRow++;
        }
    }

    private static long findBestIntegerSolution(double[][] rref, int rows, int cols, int limit) {
        int[] pivotColForRow = new int[rows];
        Arrays.fill(pivotColForRow, -1);
        boolean[] isPivotCol = new boolean[cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (Math.abs(rref[r][c] - 1.0) < 1e-9) {
                    pivotColForRow[r] = c;
                    isPivotCol[c] = true;
                    break;
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            boolean allZeros = true;
            for (int c = 0; c < cols; c++) {
                if (Math.abs(rref[r][c]) > 1e-9) {
                    allZeros = false;
                    break;
                }
            }
            if (allZeros && Math.abs(rref[r][cols]) > 1e-9) {
                return -1;
            }
        }

        List<Integer> freeVars = new ArrayList<>();
        for (int c = 0; c < cols; c++) {
            if (!isPivotCol[c]) freeVars.add(c);
        }

        return recursiveSearch(rref, rows, cols, pivotColForRow, freeVars, 0, new int[cols], limit);
    }

    private static long recursiveSearch(double[][] M, int rows, int cols,
                                        int[] pivotColForRow, List<Integer> freeVars,
                                        int freeVarIdx, int[] currentAssignment, int limit) {

        if (freeVarIdx == freeVars.size()) {
            long currentSum = 0;
            for (int fIdx : freeVars) currentSum += currentAssignment[fIdx];

            for (int r = 0; r < rows; r++) {
                int pCol = pivotColForRow[r];
                if (pCol == -1) continue;

                double val = M[r][cols];
                for (int fIdx : freeVars) {
                    val -= M[r][fIdx] * currentAssignment[fIdx];
                }

                long intVal = Math.round(val);
                if (Math.abs(val - intVal) > 1e-4 || intVal < 0) { // Tolerancia ligeramente relajada
                    return -1;
                }
                currentAssignment[pCol] = (int) intVal;
                currentSum += intVal;
            }
            return currentSum;
        }

        int fCol = freeVars.get(freeVarIdx);
        long minResult = -1;

        for (int val = 0; val <= limit; val++) {
            currentAssignment[fCol] = val;
            long res = recursiveSearch(M, rows, cols, pivotColForRow, freeVars, freeVarIdx + 1, currentAssignment, limit);

            if (res != -1) {
                if (minResult == -1 || res < minResult) {
                    minResult = res;
                }
            }
        }
        return minResult;
    }


}