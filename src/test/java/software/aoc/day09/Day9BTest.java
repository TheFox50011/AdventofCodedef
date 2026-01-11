package software.aoc.day09;

import org.junit.jupiter.api.Test;
import software.aoc.day8.b.SolveDay8B;
import software.aoc.day9.b.SolveDay9B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day9BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day09input.txt";

        List<String > inputData = SolveDay9B.loadCode(rutaArchivo);
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay9B.solve(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
