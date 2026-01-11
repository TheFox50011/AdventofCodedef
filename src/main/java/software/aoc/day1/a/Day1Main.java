package software.aoc.day1.a;

import java.util.List;

public class Day1Main {
    public static void main(String[] args) {


        List<String> rotations = software.aoc.day1.a.SolveDay1A.LoadRotations("input.txt");

        int password = software.aoc.day1.a.SolveDay1A.CalculatePassword(rotations);


        System.out.println("Password: " + password);
    }
}
