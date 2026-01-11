package software.aoc.day11.b;

import software.aoc.day10.b.SolveDay10B;

import java.util.List;

public class MainDay11B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay11B.loadCode("input.txt");
        long result = SolveDay11B.solve(rotations);
        System.out.println("Password: " + result);
    }
}
