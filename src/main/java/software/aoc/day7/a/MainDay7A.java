package software.aoc.day7.a;

import java.util.List;

public class MainDay7A {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay7A.loadCode("input.txt");

        // Calcular resultado
        long result = SolveDay7A.calculateTotalSplits(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
