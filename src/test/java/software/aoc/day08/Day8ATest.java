package software.aoc.day08;

import org.junit.jupiter.api.Test;
import software.aoc.day6.a.SolveDay6A;
import software.aoc.day7.a.SolveDay7A;
import software.aoc.day8.a.SolveDay8A;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day8ATest {
    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day08input.txt";


        List<String > inputData = SolveDay8A.loadCode(rutaArchivo);

        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        long resultado = SolveDay8A.solve(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
