package software.aoc.day7.a;

import java.util.List;

public class MainDay7A {
    public static void main(String[] args) {


        List<String> rotations = SolveDay7A.loadCode("input.txt");

        long result = SolveDay7A.calculateTotalSplits(rotations);

        System.out.println("Password: " + result);
    }
}
