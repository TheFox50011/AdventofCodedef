package software.aoc.day1.a;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SolveDay1A {

    static List<String> loadRotations(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    static int calculatePassword(List<String> rotations) {
        int position = 50;
        int zeroHits = 0;

        for (String rotation : rotations) {
            rotation = rotation.trim();
            if (rotation.isEmpty()) continue;

            char direction = rotation.charAt(0);
            int distance = Integer.parseInt(rotation.substring(1));

            position = applyRotation(position, direction, distance);

            if (position == 0) {
                zeroHits++;
            }
        }

        return zeroHits;
    }

    private static int applyRotation(int currentPosition, char direction, int distance) {
        return switch (direction) {
            case 'R' -> (currentPosition + distance) % 100;
            case 'L' -> (currentPosition - distance + 100) % 100;
            default -> throw new IllegalArgumentException("Invalid rotation direction: " + direction);
        };
    }
}
