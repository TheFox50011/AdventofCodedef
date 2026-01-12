package software.aoc.day6.a;

import java.util.List;

public class MainDay6A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay6A.loadCode("input.txt");
        long result = SolveDay6A.calculateGrandTotal(rotations);

        System.out.println("Password: " + result);
    }
}
