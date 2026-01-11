package software.aoc.day9.a;

import java.util.List;

public class MainDay9A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay9A.loadCode("input.txt");
        long result = SolveDay9A.solve(rotations);
        System.out.println("Password: " + result);
    }
}
