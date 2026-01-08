package software.aoc.day5.a;

import java.util.Objects;

// Debe ser public para ser visible en day5.a y day5.b
public class Range implements Comparable<Range> {
    private final long start;
    private final long end;

    public Range(long start, long end) {
        this.start = start;
        this.end = end;
    }

    // --- Métodos para Parte A ---

    public boolean contains(long value) {
        return value >= start && value <= end;
    }

    public static Range fromString(String rangeString) {
        String[] parts = rangeString.split("-");
        return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }

    // --- Métodos para Parte B ---

    public long size() {
        return end - start + 1;
    }

    public boolean overlapsOrAdjoins(Range other) {
        // Verifica si se tocan o solapan (ej: 3-5 y 6-8 están conectados)
        return this.start <= other.end + 1 && other.start <= this.end + 1;
    }

    public Range merge(Range other) {
        long newStart = Math.min(this.start, other.start);
        long newEnd = Math.max(this.end, other.end);
        return new Range(newStart, newEnd);
    }

    @Override
    public int compareTo(Range other) {
        return Long.compare(this.start, other.start);
    }

    // Equals/HashCode/ToString para depuración y buenas prácticas
    @Override
    public String toString() {
        return start + "-" + end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return start == range.start && end == range.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
