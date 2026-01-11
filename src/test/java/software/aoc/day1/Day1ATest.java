package software.aoc.day1;

import org.junit.jupiter.api.Test;
import software.aoc.day1.a.SolveDay1A;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Day1ATest {

    @Test
    void testCalculatePasswordWithInputFile() {

        String rutaArchivo = "src/test/resources/Day01input.txt";
        List<String> inputData = SolveDay1A.LoadRotations(rutaArchivo);
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");
        int resultado = SolveDay1A.CalculatePassword(inputData);

        System.out.println("--------------------------------------------------");
        System.out.println("El resultado es: " + resultado);
        System.out.println("--------------------------------------------------");

        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
