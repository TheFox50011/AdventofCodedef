package software.aoc.day11.a;



import java.util.List;

public class MainDay11A {
    public static void main(String[] args) {

        List<String> rotations = SolveDay11A.loadCode("input.txt");
        long result = SolveDay11A.solve(rotations);
        System.out.println("Password: " + result);
    }

}
