package software.aoc.day8.b;

import java.util.List;

public class MainDay8B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay8B.loadCode("input.txt");

        long result = SolveDay8B.solve(rotations);

        System.out.println("Password: " + result);
    }
}
