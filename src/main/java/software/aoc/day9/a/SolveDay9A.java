package software.aoc.day9.a;

import software.aoc.day9.Record.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SolveDay9A {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        List<Point> redTiles = parseInput(lines);

        if (redTiles.size() < 2) {
            return 0;
        }

        long maxArea = 0;

        for (int i = 0; i < redTiles.size(); i++) {
            for (int j = i + 1; j < redTiles.size(); j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);

                long currentArea = calculateArea(p1, p2);

                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }

        return maxArea;
    }

    private static List<Point> parseInput(List<String> lines) {
        List<Point> points = new ArrayList<>();
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;

            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());

            points.add(new Point(x, y));
        }
        return points;
    }

    private static long calculateArea(Point p1, Point p2) {
        long width = Math.abs(p1.x() - p2.x()) + 1;
        long height = Math.abs(p1.y() - p2.y()) + 1;

        return width * height;
    }
}
