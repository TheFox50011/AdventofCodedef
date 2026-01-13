package software.aoc.day4;

import org.junit.jupiter.api.Test;
import software.aoc.day3.a.SolveDay3A;
import software.aoc.day4.a.SolveDay4A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day4ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day04input.txt";


        List<String > inputData = SolveDay4A.loadGrid(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay4A.countAccessibleRolls(inputData);


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

        long resultado = SolveDay4A.countAccessibleRolls(mockInput);

        assertEquals(4, resultado, "Deberian ser 4 las @ accesibles");
    }
}
