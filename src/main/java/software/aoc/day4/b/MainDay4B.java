package software.aoc.day4.b;

import software.aoc.day4.a.SolveDay4A;

import java.util.List;

public class MainDay4B {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay4A.loadGrid("input.txt");

        // Calcular resultado
        long result = SolveDay4A.countAccessibleRolls(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
