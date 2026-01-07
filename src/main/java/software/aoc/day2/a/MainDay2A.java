package software.aoc.day2.a;

import java.util.List;

public class MainDay2A {
    public static void main(String[] args) {

        // Cargar input
        String rotations = software.aoc.day2.a.SolveDay2A.loadInput("input.txt");

        // Calcular resultado
        long result = software.aoc.day2.a.SolveDay2A.calculateInvalidIdsSum(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
