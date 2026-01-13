package software.aoc.day4;

import org.junit.jupiter.api.Test;
import software.aoc.day3.b.SolveDay3B;
import software.aoc.day4.b.SolveDay4B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day4BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day05input.txt";


        List<String > inputData = SolveDay4B.LoadCode(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay4B.calculateTotalRolls(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor  es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        List<String> mockInput = List.of(
                "@@@",
                "@.@",
                "@@@"
        );

        long resultado = SolveDay4B.calculateTotalRolls(mockInput);
        assertEquals(8, resultado);
    }
}
