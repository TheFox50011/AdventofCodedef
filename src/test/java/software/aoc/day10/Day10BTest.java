package software.aoc.day10;

import org.junit.jupiter.api.Test;
import software.aoc.day10.b.SolveDay10B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day10BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day10input.txt";


        List<String > inputData = SolveDay10B.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay10B.solve(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        List<String> mockInput = List.of(
                "{2,2} (0) (1) (0,1)"
        );

        long resultado = SolveDay10B.solve(mockInput);

        assertEquals(2, resultado, "El algoritmo debería encontrar la solución óptima de 2 pulsaciones usando el botón compartido");
    }
}
