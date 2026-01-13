package software.aoc.day09;

import org.junit.jupiter.api.Test;
import software.aoc.day8.a.SolveDay8A;
import software.aoc.day9.a.SolveDay9A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day9ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day09input.txt";

        List<String > inputData = SolveDay9A.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay9A.solve(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        List<String> mockInput = List.of(
                "0, 0",
                "2, 3"
        );

        long resultado = SolveDay9A.solve(mockInput);

        assertEquals(12, resultado, "El área del rectángulo debería ser 3x4 = 12");
    }
}
