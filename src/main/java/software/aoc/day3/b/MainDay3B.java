package software.aoc.day3.b;


import software.aoc.day3.a.SolveDay3A;

import java.util.List;

public class MainDay3B {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = SolveDay3A.LoadCode("input.txt");

        // Calcular resultado
        long result = SolveDay3A.calculateTotalJoltage(rotations);

        // Imprimir resultado
        System.out.println("Password: " + result);
    }

}
