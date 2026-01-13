package software.aoc.day2;

import org.junit.jupiter.api.Test;
import software.aoc.day2.a.SolveDay2A;

import static org.junit.jupiter.api.Assertions.*;

public class Day2ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day02input.txt";


        String inputData = SolveDay2A.loadInput(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay2A.calculateInvalidIdsSum(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor  es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        String inputData = "10-12,20-23";
        long resultado = SolveDay2A.calculateInvalidIdsSum(inputData);
        assertEquals(33, resultado, "La suma de 11 + 22 debería ser 33");
    }
}
