package software.aoc.day4.b;

import software.aoc.day4.a.SolveDay4A;

import java.util.List;

public class MainDay4B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay4A.loadGrid("input.txt");

        long result = SolveDay4A.countAccessibleRolls(rotations);

        System.out.println("Password: " + result);
    }
}
