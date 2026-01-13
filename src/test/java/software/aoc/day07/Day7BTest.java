package software.aoc.day07;

import org.junit.jupiter.api.Test;
import software.aoc.day6.b.SolveDay6B;
import software.aoc.day7.a.SolveDay7A;
import software.aoc.day7.b.SolveDay7B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day7BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day07input.txt";

        List<String > inputData = SolveDay7A.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay7B.calculateTotalTimelines(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {
        List<String> mockInput = List.of(
                ".S.",
                ".^."
        );

        long resultado = SolveDay7B.calculateTotalTimelines(mockInput);

        assertEquals(2, resultado, "El camino debería dividirse en 2 timelines distintos");
    }
}
