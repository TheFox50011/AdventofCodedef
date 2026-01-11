package software.aoc.day8.a;

import software.aoc.day8.auxiliar.Point3D;
import software.aoc.day8.auxiliar.CircuitManager;
import software.aoc.day8.auxiliar.Connection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolveDay8A {

    private static final int MAX_CONNECTIONS_TO_PROCESS = 1000;

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {

        List<Point3D> points = parseInput(lines);
        List<Connection> potentialConnections = generateSortedConnections(points);
        CircuitManager circuitManager = new CircuitManager(points.size());


        int limit = Math.min(MAX_CONNECTIONS_TO_PROCESS, potentialConnections.size());
        for (int i = 0; i < limit; i++) {
            Connection conn = potentialConnections.get(i);
            circuitManager.connect(conn.pointIndexA(), conn.pointIndexB());
        }

        return calculateTop3Product(circuitManager);
    }

    private static List<Point3D> parseInput(List<String> lines) {
        List<Point3D> points = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            points.add(Point3D.parse(lines.get(i), i));
        }
        return points;
    }

    private static List<Connection> generateSortedConnections(List<Point3D> points) {
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                connections.add(new Connection(points.get(i), points.get(j)));
            }
        }
        connections.sort(Comparator.comparingLong(Connection::distanceSq));
        return connections;
    }

    private static long calculateTop3Product(CircuitManager circuitManager) {
        List<Integer> sizes = circuitManager.getAllCircuitSizes();
        sizes.sort((a, b) -> b.compareTo(a));

        long product = 1;
        int count = Math.min(3, sizes.size());
        for (int i = 0; i < count; i++) {
            product *= sizes.get(i);
        }
        return product;
    }

}
