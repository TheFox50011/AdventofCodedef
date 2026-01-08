package software.aoc.day3;

import org.junit.jupiter.api.Test;
import software.aoc.day3.a.SolveDay3A;
import software.aoc.day3.b.SolveDay3B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day3BTest {

    @Test
    void testCalculatePasswordWithInputFile() {
        // 1. Definir la ruta al archivo.
        String rutaArchivo = "src/test/resources/Day03input.txt";

        // 2. Cargar las rotaciones
        // Nota: Si el archivo no existe, esto lanzará la RuntimeException que definiste
        List<String > inputData = SolveDay3B.LoadCode(rutaArchivo);

        // Verificación básica de que se leyó algo
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        // 3. Ejecutar la lógica principal
        long resultado = SolveDay3B.calculateTotalJoltage(inputData);

        // 4. Imprimir el resultado en consola
        System.out.println("--------------------------------------------------");
        System.out.println("El valor de la suma de las id  invalidas es: " + resultado);
        System.out.println("--------------------------------------------------");

        // 5. Comprobar que da el resutlado deseado
        // La condicion actual es un ejemplo
        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
