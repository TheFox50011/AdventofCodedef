package software.aoc.day12;

import org.junit.jupiter.api.Test;
import software.aoc.day12.a.SolveDay12A;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class Day12ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day12input.txt";

        List<String> inputData = SolveDay12A.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay12A.solve(inputData);
        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }

    @Test
    void testLogicWithExample() {
        String blob = """
                0:
                #
                
                1:
                ###
                
                2:
                ##
                ##
                
                3:
                .#
                ##
                .#
                
                4:
                ##.
                .##
                
                3x3: 9 0 0 0 0
                3x3: 0 3 0 0 0
                4x4: 0 0 4 0 0
                4x2: 0 0 0 2 0
                5x5: 1 1 1 1 1
                2x2: 0 0 0 0 1
                """;

        List<String> mockInput = Arrays.stream(blob.split("\n"))
                .collect(Collectors.toList());
        long resultado = SolveDay12A.solve(mockInput);
        assertEquals(4, resultado, "Deberían encajar 4 de las 6 consultas propuestas");
    }
}