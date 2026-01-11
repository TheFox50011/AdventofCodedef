package software.aoc.day10;

import org.junit.jupiter.api.Test;
import software.aoc.day10.a.SolveDay10A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day10ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day10input.txt";

        List<String > inputData = SolveDay10A.loadCode(rutaArchivo);


        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");


        long resultado = SolveDay10A.solve(inputData);


        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
