package software.aoc.day3;

import org.junit.jupiter.api.Test;
import software.aoc.day2.a.SolveDay2A;
import software.aoc.day3.a.SolveDay3A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day3ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day03input.txt";


        List<String > inputData = SolveDay3A.LoadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay3A.calculateTotalJoltage(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor  es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        List<String> mockInput = List.of("195");
        long resultado = SolveDay3A.calculateTotalJoltage(mockInput);
        assertEquals(95, resultado, "El joltaje máximo de 195 debería ser 95");
    }
}
