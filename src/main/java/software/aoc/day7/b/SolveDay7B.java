package software.aoc.day7.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay7B {

        public static List<String> loadCode(String filePath) {
            try {
                return Files.readAllLines(Path.of(filePath));
            } catch (IOException e) {
                throw new RuntimeException("Error reading input file: " + filePath, e);
            }
        }

        public static long calculateTotalTimelines(List<String> grid) {
            if (grid == null || grid.isEmpty()) return 0;

            int rows = grid.size();
            int cols = grid.get(0).length();

            // Tabla DP: dp[r][c] almacena cuántas líneas temporales activas existen en esa posición
            long[][] dp = new long[rows + 1][cols];

            // 1. Inicializar el estado buscando 'S' en todo el grid
            boolean startFound = false;
            for (int r = 0; r < rows; r++) {
                String rowStr = grid.get(r);
                for (int c = 0; c < cols; c++) {
                    if (rowStr.charAt(c) == 'S') {
                        dp[r][c] = 1; // Comienza una línea temporal aquí
                        startFound = true;
                    }
                }
            }

            if (!startFound) return 0;

            long finishedTimelines = 0;

            // 2. Propagar las líneas temporales fila por fila
            for (int r = 0; r < rows; r++) {
                String currentRow = grid.get(r);

                for (int c = 0; c < cols; c++) {
                    long activeTimelines = dp[r][c];

                    // Si no hay líneas temporales en esta celda, saltar
                    if (activeTimelines == 0) continue;

                    char tile = currentRow.charAt(c);

                    if (tile == '^') {
                        // --- SPLITTER: Divide la línea temporal en dos ---

                        // Rama Izquierda
                        if (c - 1 >= 0) {
                            dp[r + 1][c - 1] += activeTimelines;
                        } else {
                            // Si sale por la izquierda, la línea temporal termina (se cuenta)
                            finishedTimelines += activeTimelines;
                        }

                        // Rama Derecha
                        if (c + 1 < cols) {
                            dp[r + 1][c + 1] += activeTimelines;
                        } else {
                            // Si sale por la derecha, se cuenta
                            finishedTimelines += activeTimelines;
                        }
                    } else {
                        // --- ESPACIO (.) o START (S): Continúa hacia abajo ---
                        // Asumimos que cualquier carácter que no sea ^ deja pasar el haz
                        dp[r + 1][c] += activeTimelines;
                    }
                }
            }

            // 3. Sumar todas las líneas temporales que salieron por el fondo del grid
            for (int c = 0; c < cols; c++) {
                finishedTimelines += dp[rows][c];
            }

            return finishedTimelines;
        }
}