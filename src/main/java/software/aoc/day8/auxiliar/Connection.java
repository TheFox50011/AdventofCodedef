package software.aoc.day8.auxiliar;

public record Connection(int pointIndexA, int pointIndexB, long distanceSq) {
    public Connection(Point3D a, Point3D b) {
        this(a.id(), b.id(), a.distanceSqTo(b));
    }
}