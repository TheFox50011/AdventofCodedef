package software.aoc.day8.auxiliar;

public record Point3D(int id, int x, int y, int z) {
    public static Point3D parse(String line, int id) {
        String[] parts = line.split(",");
        return new Point3D(
                id,
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        );
    }

    public long distanceSqTo(Point3D other) {
        long dx = this.x - other.x;
        long dy = this.y - other.y;
        long dz = this.z - other.z;
        return dx * dx + dy * dy + dz * dz;
    }
}