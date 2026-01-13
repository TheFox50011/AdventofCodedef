package software.aoc.day2.b;


public class MainDay2B {
    public static void main(String[] args) {

        String rotations = SolveDay2B.loadInput("input.txt");
        long result = SolveDay2B.calculateInvalidIdsSum(rotations);
        System.out.println("Password: " + result);
    }
}
