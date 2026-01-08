package software.aoc.day6.a;

import java.util.List;

public class MainDay6A {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay6A.loadCode("input.txt");

        // Calcular resultado
        long result = SolveDay6A.calculateGrandTotal(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
