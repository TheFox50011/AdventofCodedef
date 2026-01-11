package software.aoc.day8.b;

import software.aoc.day8.auxiliar.CircuitManager;
import software.aoc.day8.auxiliar.Connection;
import software.aoc.day8.auxiliar.Point3D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolveDay8B {

    public static List<String> loadCode(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static long solve(List<String> lines) {
        List<Point3D> points = parseInput(lines);
        int n = points.size();

        List<Connection> connections = generateSortedConnections(points);

        CircuitManager circuitManager = new CircuitManager(n);


        for (Connection conn : connections) {
            boolean merged = circuitManager.connect(conn.pointIndexA(), conn.pointIndexB());

            if (merged) {
                if (circuitManager.getComponentCount() == 1) {
                    Point3D pA = points.get(conn.pointIndexA());
                    Point3D pB = points.get(conn.pointIndexB());

                    return (long) pA.x() * pB.x();
                }
            }
        }

        throw new IllegalStateException("No se pudo conectar todos los puntos en un único circuito.");
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
                connections.add(new Connection(i, j, points.get(i).distanceSqTo(points.get(j))));
            }
        }
        connections.sort(Comparator.comparingLong(Connection::distanceSq));
        return connections;
    }

}