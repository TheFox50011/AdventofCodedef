package software.aoc.day1.b;

import java.util.List;

public class Day1Main {
    public static void main(String[] args) {

        // Cargar input
        List<String> rotations = software.aoc.day1.b.SolveDay1B.loadRotations("input.txt");

        // Calcular resultado
        int password = software.aoc.day1.b.SolveDay1B.CalculatePassword(rotations);

        // Imprimir resultado
        System.out.println("Password: " + password);
    }

}
