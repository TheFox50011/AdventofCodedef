package software.aoc.day5;

import org.junit.jupiter.api.Test;
import software.aoc.day4.a.SolveDay4A;
import software.aoc.day5.a.SolveDay5A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day5ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day05input.txt";


        List<String > inputData = SolveDay5A.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay5A.calculateFreshIngredients(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        List<String> mockInput = List.of(
                "10-20",
                "",
                "5",
                "15",
                "25"
        );

        long resultado = SolveDay5A.calculateFreshIngredients(mockInput);

        assertEquals(1, resultado, "Debería encontrar 1 ingrediente fresco (el 15)");
    }
}
