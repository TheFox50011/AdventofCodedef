package software.aoc.day10.a;


import java.util.List;

public class MainDay10A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay10A.loadCode("input.txt");
        long result = SolveDay10A.solve(rotations);
        System.out.println("Password: " + result);
    }
}
