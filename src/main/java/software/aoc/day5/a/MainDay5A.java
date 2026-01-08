package software.aoc.day5.a;

import java.util.List;

public class MainDay5A {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay5A.loadCode("input.txt");

        // Calcular resultado
        long result = SolveDay5A.calculateFreshIngredients(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }
}
