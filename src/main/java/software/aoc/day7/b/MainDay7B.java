package software.aoc.day7.b;

import java.util.List;

public class MainDay7B {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay7B.loadCode("input.txt");

        // Calcular resultado
        long result = SolveDay7B.calculateTotalTimelines(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
