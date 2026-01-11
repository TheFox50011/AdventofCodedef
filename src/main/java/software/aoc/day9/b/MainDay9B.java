package software.aoc.day9.b;

import java.util.List;

public class MainDay9B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay9B.loadCode("input.txt");
        long result = SolveDay9B.solve(rotations);
        System.out.println("Password: " + result);
    }
}
