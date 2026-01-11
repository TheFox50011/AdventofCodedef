package software.aoc.day4.a;

import software.aoc.day3.a.SolveDay3A;

import java.util.List;

public class MainDay4A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay4A.loadGrid("input.txt");

        long result = SolveDay4A.countAccessibleRolls(rotations);

        System.out.println("Password: " + result);
    }
}
