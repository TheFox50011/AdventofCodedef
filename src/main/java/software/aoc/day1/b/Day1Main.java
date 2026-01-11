package software.aoc.day1.b;

import java.util.List;

public class Day1Main {
    public static void main(String[] args) {
        List<String> rotations = software.aoc.day1.b.SolveDay1B.loadRotations("input.txt");

        int password = software.aoc.day1.b.SolveDay1B.CalculatePassword(rotations);

        System.out.println("Password: " + password);
    }

}
