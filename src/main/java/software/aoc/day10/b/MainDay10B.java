package software.aoc.day10.b;


import java.util.List;

public class MainDay10B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay10B.loadCode("input.txt");

        long result = SolveDay10B.solve(rotations);

        System.out.println("Password: " + result);
    }
}
