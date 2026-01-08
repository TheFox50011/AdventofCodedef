package software.aoc.day5.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay5B {
        public static List<String> LoadCode(String filePath) {
            try {
                return Files.readAllLines(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error reading input file: " + filePath, e);
            }
        }

        public static int calculateTotalRolls(List<String> input) {
            if (input == null || input.isEmpty()) {
                return 0;
            }

            int rows = input.size();
            int cols = input.get(0).length();
            char[][] grid = new char[rows][cols];

            // Inicializar el grid
            for (int i = 0; i < rows; i++) {
                grid[i] = input.get(i).toCharArray();
            }

            int totalRemoved = 0;
            boolean changed = true;

            // Repetir el proceso mientras se puedan eliminar rollos
            while (changed) {
                List<int[]> toRemove = new ArrayList<>();

                // Paso 1: Identificar todos los rollos accesibles en el estado actual
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        if (grid[r][c] == '@') {
                            // La regla: accesible si tiene MENOS de 4 vecinos que sean rollos
                            if (countAdjacentRolls(grid, r, c, rows, cols) < 4) {
                                toRemove.add(new int[]{r, c});
                            }
                        }
                    }
                }

                // Paso 2: Si no hay nada que eliminar, terminamos
                if (toRemove.isEmpty()) {
                    changed = false;
                } else {
                    // Paso 3: Eliminar los rollos identificados y sumar al total
                    totalRemoved += toRemove.size();
                    for (int[] pos : toRemove) {
                        grid[pos[0]][pos[1]] = '.'; // Marcar como eliminado (espacio vacío)
                    }
                }
            }

            return totalRemoved;
        }

        // Cuenta los rollos ('@') en las 8 celdas adyacentes
        private static int countAdjacentRolls(char[][] grid, int r, int c, int rows, int cols) {
            int count = 0;
            // Revisar los 8 vecinos (incluyendo diagonales)
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    if (dr == 0 && dc == 0) continue; // Saltar la propia celda

                    int nr = r + dr;
                    int nc = c + dc;

                    // Verificar límites del grid
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                        if (grid[nr][nc] == '@') {
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }
