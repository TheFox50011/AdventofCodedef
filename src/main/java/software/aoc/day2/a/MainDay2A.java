package software.aoc.day2.a;


public class MainDay2A {
    public static void main(String[] args) {
        String rotations = software.aoc.day2.a.SolveDay2A.loadInput("input.txt");

        long result = software.aoc.day2.a.SolveDay2A.calculateInvalidIdsSum(rotations);

        System.out.println("Password: " + result);
    }
}
