package software.aoc.day1.a;

import java.util.List;

public class Day1Main {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = software.aoc.day1.a.SolveDay1A.loadRotations("input.txt");

        // Calcular resultado
        int password = software.aoc.day1.a.SolveDay1A.calculatePassword(rotations);

        // Imprimir resultado
        System.out.println("Password: " + password);
    }
}
