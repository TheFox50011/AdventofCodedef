package software.aoc.day5.b;

import software.aoc.day5.a.SolveDay5A;

import java.util.List;

public class MainDay5B {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay5A.loadGrid("input.txt");

        // Calcular resultado
        long result = SolveDay5A.countAccessibleRolls(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
