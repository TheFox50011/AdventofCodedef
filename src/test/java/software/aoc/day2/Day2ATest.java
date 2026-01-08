package software.aoc.day2;

import org.junit.jupiter.api.Test;
import software.aoc.day2.a.SolveDay2A;

import static org.junit.jupiter.api.Assertions.*;

public class Day2ATest {
    @Test
    void testCalculatePasswordWithInputFile() {
        // 1. Definir la ruta al archivo.
        String rutaArchivo = "src/test/resources/Day02input.txt";

        // 2. Cargar las rotaciones
        // Nota: Si el archivo no existe, esto lanzará la RuntimeException que definiste
        String inputData = SolveDay2A.loadInput(rutaArchivo);

        // Verificación básica de que se leyó algo
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        // 3. Ejecutar la lógica principal
        long resultado = SolveDay2A.calculateInvalidIdsSum(inputData);

        // 4. Imprimir el resultado en consola
        System.out.println("--------------------------------------------------");
        System.out.println("El valor  es: " + resultado);
        System.out.println("--------------------------------------------------");

        // 5. Comprobar que da el resutlado deseado
        // La condicion actual es un ejemplo
        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
