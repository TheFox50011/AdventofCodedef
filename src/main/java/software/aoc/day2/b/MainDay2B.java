package software.aoc.day2.b;

import software.aoc.day2.a.SolveDay2A;

public class MainDay2B {
    public static void main(String[] args) {

        // Cargar input
        String rotations = SolveDay2B.loadInput("input.txt");

        // Calcular resultado
        long result = SolveDay2B.calculateInvalidIdsSum(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
