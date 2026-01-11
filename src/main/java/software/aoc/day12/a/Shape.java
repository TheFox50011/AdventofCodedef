package software.aoc.day12.a;

import software.aoc.day12.record.Point;

import java.util.*;

public class Shape {
    List<List<Point>> normalizedVariations;
    int area;

    public Shape(boolean[][] baseGrid) {
        this.area = countArea(baseGrid);
        this.normalizedVariations = generateUniqueVariations(baseGrid);
    }

    private int countArea(boolean[][] g) {
        int c = 0;
        for (boolean[] row : g) for (boolean cell : row) if (cell) c++;
        return c;
    }

    private List<List<Point>> generateUniqueVariations(boolean[][] base) {
        Set<String> fingerprints = new HashSet<>();
        List<List<Point>> vars = new ArrayList<>();

        boolean[][] current = base;
        for (int r = 0; r < 4; r++) {
            addIfNew(vars, fingerprints, current);
            addIfNew(vars, fingerprints, flip(current));
            current = rotate90(current);
        }
        return vars;
    }

    private void addIfNew(List<List<Point>> vars, Set<String> fps, boolean[][] g) {

        List<Point> points = new ArrayList<>();
        int firstR = -1, firstC = -1;


        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j]) {
                    if (firstR == -1) {
                        firstR = i;
                        firstC = j;
                    }
                    points.add(new Point(i - firstR, j - firstC));
                }
            }
        }
        String fp = points.toString();
        if (fps.add(fp)) {
            vars.add(points);
        }
    }

    private boolean[][] rotate90(boolean[][] mat) {
        int h = mat.length;
        int w = mat[0].length;
        boolean[][] ret = new boolean[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                ret[j][h - 1 - i] = mat[i][j];
            }
        }
        return ret;
    }

    private boolean[][] flip(boolean[][] mat) {
        int h = mat.length;
        int w = mat[0].length;
        boolean[][] ret = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            ret[i] = mat[h - 1 - i].clone();
        }
        return ret;
    }
}

