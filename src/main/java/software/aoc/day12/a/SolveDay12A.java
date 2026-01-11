package software.aoc.day12.a;

import software.aoc.day12.record.InputData;
import software.aoc.day12.record.Piece;
import software.aoc.day12.record.Point;
import software.aoc.day12.record.RegionQuery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class SolveDay12A {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        InputData input = Parser.parse(lines);

        AtomicLong possibleCount = new AtomicLong(0);

        input.queries().parallelStream().forEach(query -> {
            if (canFitPresents(query, input.shapes())) {
                possibleCount.incrementAndGet();
            }
        });

        return possibleCount.get();
    }

    private static boolean canFitPresents(RegionQuery query, Map<Integer, Shape> shapes) {
        List<Piece> piecesToPlace = new ArrayList<>();
        int totalPresentArea = 0;

        for (int shapeId : query.shapeIds()) {
            Shape s = shapes.get(shapeId);
            totalPresentArea += s.area;
            piecesToPlace.add(new Piece(shapeId, s));
        }

        int regionArea = query.width() * query.height();

        if (totalPresentArea > regionArea) {
            return false;
        }

        int slack = regionArea - totalPresentArea;
        boolean[][] grid = new boolean[query.height()][query.width()];

        piecesToPlace.sort(Comparator.comparingInt((Piece p) -> p.baseShape().area).reversed()
                .thenComparingInt(Piece::id));

        boolean[] used = new boolean[piecesToPlace.size()];

        return solveRecursive(grid, piecesToPlace, used, slack);
    }

    private static boolean solveRecursive(boolean[][] grid, List<Piece> pieces, boolean[] used, int slack) {
        int r = -1, c = -1;
        int H = grid.length;
        int W = grid[0].length;

        outer:
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!grid[i][j]) {
                    r = i;
                    c = j;
                    break outer;
                }
            }
        }


        if (r == -1) {
            return true;
        }


        for (int i = 0; i < pieces.size(); i++) {
            if (used[i]) continue;


            if (i > 0 && pieces.get(i).id() == pieces.get(i - 1).id() && !used[i - 1]) {
                continue;
            }

            Piece p = pieces.get(i);

            for (List<Point> variant : p.baseShape().normalizedVariations) {
                if (canPlace(grid, r, c, variant)) {

                    place(grid, r, c, variant, true);
                    used[i] = true;

                    if (solveRecursive(grid, pieces, used, slack)) return true;
                    used[i] = false;
                    place(grid, r, c, variant, false);
                }
            }
        }

        if (slack > 0) {
            grid[r][c] = true;
            if (solveRecursive(grid, pieces, used, slack - 1)) return true;
            grid[r][c] = false;
        }

        return false;
    }

    private static boolean canPlace(boolean[][] grid, int r, int c, List<Point> shapePoints) {
        int H = grid.length;
        int W = grid[0].length;

        for (Point p : shapePoints) {
            int nr = r + p.r();
            int nc = c + p.c();
            if (nr < 0 || nr >= H || nc < 0 || nc >= W || grid[nr][nc]) {
                return false;
            }
        }
        return true;
    }

    private static void place(boolean[][] grid, int r, int c, List<Point> shapePoints, boolean val) {
        for (Point p : shapePoints) {
            grid[r + p.r()][c + p.c()] = val;
        }
    }
}