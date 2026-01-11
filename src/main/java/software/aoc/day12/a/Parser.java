package software.aoc.day12.a;

import software.aoc.day12.record.InputData;
import software.aoc.day12.record.RegionQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static InputData parse(List<String> lines) {
        Map<Integer, Shape> shapes = new HashMap<>();
        List<RegionQuery> queries = new ArrayList<>();
        List<String> buffer = new ArrayList<>();
        int currentId = -1;
        boolean parsingShapes = true;

        for (String line : lines) {
            if (line.isBlank()) {
                if (currentId != -1 && !buffer.isEmpty()) {
                    shapes.put(currentId, parseGrid(buffer));
                    buffer.clear();
                    currentId = -1;
                }
                continue;
            }
            if (line.contains("x") && line.contains(":")) {
                parsingShapes = false;
                if (currentId != -1 && !buffer.isEmpty()) {
                    shapes.put(currentId, parseGrid(buffer));
                    buffer.clear();
                    currentId = -1;
                }
                queries.add(parseQuery(line));
            } else if (parsingShapes) {
                if (line.matches("\\d+:")) {
                    if (currentId != -1) {
                        shapes.put(currentId, parseGrid(buffer));
                        buffer.clear();
                    }
                    currentId = Integer.parseInt(line.replace(":", ""));
                } else {
                    buffer.add(line);
                }
            }
        }
        if (currentId != -1 && !buffer.isEmpty()) shapes.put(currentId, parseGrid(buffer));
        return new InputData(shapes, queries);
    }

    private static Shape parseGrid(List<String> lines) {
        int h = lines.size();
        int w = lines.stream().mapToInt(String::length).max().orElse(0);
        boolean[][] g = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            String l = lines.get(i);
            for (int j = 0; j < l.length(); j++) {
                if (l.charAt(j) == '#') g[i][j] = true;
            }
        }
        return new Shape(g);
    }

    private static RegionQuery parseQuery(String line) {
        String[] parts = line.split(":");
        String[] dims = parts[0].split("x");
        List<Integer> ids = new ArrayList<>();
        String[] counts = parts[1].trim().split("\\s+");
        for (int id = 0; id < counts.length; id++) {
            int count = Integer.parseInt(counts[id]);
            for (int k = 0; k < count; k++) ids.add(id);
        }
        return new RegionQuery(Integer.parseInt(dims[0]), Integer.parseInt(dims[1]), ids);
    }
}
