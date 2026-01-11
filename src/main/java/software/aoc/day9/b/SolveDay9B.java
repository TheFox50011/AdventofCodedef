package software.aoc.day9.b;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import software.aoc.day9.Record.Point;
import software.aoc.day9.auxiliar.Coordinates;

import static software.aoc.day9.auxiliar.Coordinates.*;

public class SolveDay9B {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        List<Point> redTiles = parseInput(lines);
        if (redTiles.size() < 2) return 0;

        List<Integer> sortedX = getRelevantCoordinates(redTiles, true);
        List<Integer> sortedY = getRelevantCoordinates(redTiles, false);

        Map<Integer, Integer> mapX = createIndexMap(sortedX);
        Map<Integer, Integer> mapY = createIndexMap(sortedY);

        int W = sortedX.size();
        int H = sortedY.size();
        boolean[][] compressedGrid = new boolean[W][H];

        drawCompressedBoundary(compressedGrid, redTiles, mapX, mapY);
        fillInterior(compressedGrid, W, H);
        int[][] prefixSum = buildPrefixSum(compressedGrid, W, H);

        long maxArea = 0;

        for (int i = 0; i < redTiles.size(); i++) {
            for (int j = i + 1; j < redTiles.size(); j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);

                int ix1 = mapX.get(p1.x());
                int ix2 = mapX.get(p2.x());
                int iy1 = mapY.get(p1.y());
                int iy2 = mapY.get(p2.y());

                int xStart = Math.min(ix1, ix2);
                int xEnd = Math.max(ix1, ix2);
                int yStart = Math.min(iy1, iy2);
                int yEnd = Math.max(iy1, iy2);
                long totalBlocks = (long) (xEnd - xStart + 1) * (yEnd - yStart + 1);
                long validBlocks = getSumRegion(prefixSum, xStart, yStart, xEnd, yEnd);

                if (totalBlocks == validBlocks) {
                    long realWidth = Math.abs(p1.x() - p2.x()) + 1;
                    long realHeight = Math.abs(p1.y() - p2.y()) + 1;
                    long area = realWidth * realHeight;

                    if (area > maxArea) {
                        maxArea = area;
                    }
                }
            }
        }

        return maxArea;
    }


}