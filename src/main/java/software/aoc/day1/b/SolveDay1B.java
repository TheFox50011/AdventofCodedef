package software.aoc.day1.b;

import javax.swing.text.Position;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SolveDay1B {
    private static final int DIAL_SIZE = 100;
    private static final int START_POSITION = 50;

    public static List<String> loadRotations(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading input file: " + filePath, e);
        }
    }

    public static int CalculatePassword(List<String> rotations){
        int position = START_POSITION;
        int zeroHits = 0;

        for(String rotation: rotations ){
            rotation = rotation.trim();

            if (rotation.isEmpty()) continue;

            char direction = rotation.charAt(0);
            int distance = Integer.parseInt(rotation.substring(1));

            for(int i = 0; i < distance ; i++){
                position = moveOneClick(position,direction);
                if (position == 0) zeroHits++;
            }

        }

        return zeroHits;


    }

    private static int moveOneClick(int position, char direction) {
        return switch (direction){
            case 'R' -> (position + 1) % DIAL_SIZE;
            case 'L' -> ( position - 1 + DIAL_SIZE) % DIAL_SIZE;
            default -> throw new IllegalArgumentException("Invalid Rotation: " + direction);

        };

    }

}
