package software.aoc.day6.b;

import software.aoc.day6.a.SolveDay6A;

import java.util.List;

public class MainDay6B {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay6B.loadCode("input.txt");

        // Calcular resultado
        long result = SolveDay6B.calculateGrandTotal(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
