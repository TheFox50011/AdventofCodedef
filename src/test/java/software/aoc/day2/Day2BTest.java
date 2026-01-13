package software.aoc.day2;

import org.junit.jupiter.api.Test;
import software.aoc.day2.a.SolveDay2A;
import software.aoc.day2.b.SolveDay2B;

import static org.junit.jupiter.api.Assertions.*;

public class Day2BTest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day02input.txt";


        String inputData = SolveDay2B.loadInput(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay2B.calculateInvalidIdsSum(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor  es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }

    @Test
    void testLogicWithExample() {

        String mockInput = "123123-123123,123456-123456";
        long resultado = SolveDay2B.calculateInvalidIdsSum(mockInput);
        assertEquals(123123, resultado, "Debería identificar 123123 como inválido por repetición de bloque");

    }
}
