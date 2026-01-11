package software.aoc.day3.a;


import software.aoc.day3.a.SolveDay3A;
import java.util.List;

public class MainDay3A {
    public static void main(String[] args) {

        List <String> rotations = SolveDay3A.LoadCode("input.txt");

        long result = SolveDay3A.calculateTotalJoltage(rotations);

        System.out.println("Password: " + result);
    }
}
