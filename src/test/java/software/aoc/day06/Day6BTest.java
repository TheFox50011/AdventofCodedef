package software.aoc.day06;

import org.junit.jupiter.api.Test;
import software.aoc.day6.b.SolveDay6B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day6BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day06input.txt";


        List<String > inputData = SolveDay6B.loadCode(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay6B.calculateGrandTotal(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {


        List<String> mockInput = List.of(
                "1",
                "2",
                "+"
        );

        long resultado = SolveDay6B.calculateGrandTotal(mockInput);

        assertEquals(12, resultado, "Debería leer verticalmente los dígitos formando el 12");
    }
}
