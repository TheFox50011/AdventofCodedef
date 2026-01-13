package software.aoc.day3;

import org.junit.jupiter.api.Test;
import software.aoc.day3.a.SolveDay3A;
import software.aoc.day3.b.SolveDay3B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day3BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day03input.txt";


        List<String > inputData = SolveDay3B.LoadCode(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay3B.calculateTotalJoltage(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {

        String inputLargo = "987654321098765"; // > 12 chars
        List<String> mockInput = List.of(inputLargo);
        long resultado = SolveDay3B.calculateTotalJoltage(mockInput);
        assertTrue(resultado > 0);
    }
}
