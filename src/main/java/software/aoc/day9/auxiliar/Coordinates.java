package software.aoc.day9.auxiliar;

import software.aoc.day9.Record.Point;

import java.util.*;

public class Coordinates {
    public static List<Integer> getRelevantCoordinates(List<Point> points, boolean isX) {
        Set<Integer> coords = new HashSet<>();
        for (Point p : points) {
            int val = isX ? p.x() : p.y();
            coords.add(val);
            coords.add(val + 1);
        }

        int min = coords.stream().min(Integer::compareTo).orElse(0);
        int max = coords.stream().max(Integer::compareTo).orElse(0);
        coords.add(min - 1);
        coords.add(max + 1); // Padding extra por seguridad

        List<Integer> sorted = new ArrayList<>(coords);
        Collections.sort(sorted);
        return sorted;
    }

    public static Map<Integer, Integer> createIndexMap(List<Integer> sortedCoords) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sortedCoords.size(); i++) {
            map.put(sortedCoords.get(i), i);
        }
        return map;
    }

    public static void drawCompressedBoundary(boolean[][] grid, List<Point> points,
                                               Map<Integer, Integer> mapX, Map<Integer, Integer> mapY) {
        for (int i = 0; i < points.size(); i++) {
            Point p1 = points.get(i);
            Point p2 = points.get((i + 1) % points.size());

            int ix1 = mapX.get(p1.x());
            int iy1 = mapY.get(p1.y());
            int ix2 = mapX.get(p2.x());
            int iy2 = mapY.get(p2.y());


            if (ix1 == ix2) {
                int start = Math.min(iy1, iy2);
                int end = Math.max(iy1, iy2);
                for (int y = start; y <= end; y++) grid[ix1][y] = true;
            } else {
                int start = Math.min(ix1, ix2);
                int end = Math.max(ix1, ix2);
                for (int x = start; x <= end; x++) grid[x][iy1] = true;
            }
        }
    }

    public static void fillInterior(boolean[][] grid, int W, int H) {
        boolean[][] visitedExterior = new boolean[W][H];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0});
        visitedExterior[0][0] = true;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0];
            int cy = curr[1];

            for (int[] d : dirs) {
                int nx = cx + d[0];
                int ny = cy + d[1];

                if (nx >= 0 && nx < W && ny >= 0 && ny < H) {
                    if (!grid[nx][ny] && !visitedExterior[nx][ny]) {
                        visitedExterior[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }

        for (int x = 0; x < W; x++) {
            for (int y = 0; y < H; y++) {
                grid[x][y] = !visitedExterior[x][y];
            }
        }
    }

    public static int[][] buildPrefixSum(boolean[][] grid, int W, int H) {
        int[][] pSum = new int[W + 1][H + 1];
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                pSum[i + 1][j + 1] = (grid[i][j] ? 1 : 0)
                        + pSum[i][j + 1]
                        + pSum[i + 1][j]
                        - pSum[i][j];
            }
        }
        return pSum;
    }

    public static long getSumRegion(int[][] pSum, int x1, int y1, int x2, int y2) {
        x2++; y2++; // Ajuste por padding del prefix sum
        return pSum[x2][y2] - pSum[x1][y2] - pSum[x2][y1] + pSum[x1][y1];
    }

    public static List<Point> parseInput(List<String> lines) {
        List<Point> points = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(",");
            points.add(new Point(
                    Integer.parseInt(parts[0].trim()),
                    Integer.parseInt(parts[1].trim())
            ));
        }
        return points;
    }
}
