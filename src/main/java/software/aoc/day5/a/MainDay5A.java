package software.aoc.day5.a;

import java.util.List;

public class MainDay5A {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay5A.loadGrid("input.txt");

        // Calcular resultado
        long result = SolveDay5A.countAccessibleRolls(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
