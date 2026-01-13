package software.aoc.day5;

import org.junit.jupiter.api.Test;
import software.aoc.day5.b.SolveDay5B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day5BTest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day05input.txt";


        List<String > inputData = SolveDay5B.loadCode(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay5B.calculateTotalFreshIngredients(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");


        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
    @Test
    void testLogicWithExample() {


        List<String> mockInput = List.of(
                "1-5",
                "4-8"
        );
        long resultado = SolveDay5B.calculateTotalFreshIngredients(mockInput);

        assertEquals(8, resultado, "La unión de 1-5 y 4-8 debería cubrir 8 valores");
    }
}
