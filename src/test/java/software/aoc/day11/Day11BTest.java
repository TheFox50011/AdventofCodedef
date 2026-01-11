package software.aoc.day11;

import org.junit.jupiter.api.Test;
import software.aoc.day11.b.SolveDay11B;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day11BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day11input.txt";


        List<String > inputData = SolveDay11B.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay11B.solve(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
