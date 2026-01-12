package software.aoc.day5.b;

import software.aoc.day5.a.SolveDay5A;

import java.util.List;

public class MainDay5B {
    public static void main(String[] args) {

        List<String> rotations = SolveDay5A.loadCode("input.txt");

        long result = SolveDay5A.calculateFreshIngredients(rotations);

        System.out.println("Password: " + result);
    }
}
