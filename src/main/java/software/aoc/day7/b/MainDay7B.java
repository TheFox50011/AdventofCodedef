package software.aoc.day7.b;

import java.util.List;

public class MainDay7B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay7B.loadCode("input.txt");

        long result = SolveDay7B.calculateTotalTimelines(rotations);

        System.out.println("Password: " + result);
    }
}
