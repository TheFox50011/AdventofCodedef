package software.aoc.day12.a;

import java.util.List;

public class MainDay12A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay12A.loadCode("input.txt");
        long result = SolveDay12A.solve(rotations);
        System.out.println("Password: " + result);
    }
}
