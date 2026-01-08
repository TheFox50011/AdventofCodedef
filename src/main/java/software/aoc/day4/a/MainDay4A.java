package software.aoc.day4.a;

import software.aoc.day3.a.SolveDay3A;

import java.util.List;

public class MainDay4A {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay4A.loadGrid("input.txt");

        // Calcular resultado
        long result = SolveDay4A.countAccessibleRolls(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
