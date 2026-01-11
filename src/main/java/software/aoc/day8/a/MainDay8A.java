package software.aoc.day8.a;

import java.util.List;

public class MainDay8A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay8A.loadCode("input.txt");

        long result = SolveDay8A.solve(rotations);

        System.out.println("Password: " + result);
    }
}
