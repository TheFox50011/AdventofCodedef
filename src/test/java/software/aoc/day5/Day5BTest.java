package software.aoc.day5;

import org.junit.jupiter.api.Test;
import software.aoc.day4.b.SolveDay4B;
import software.aoc.day5.b.SolveDay5B;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day5BTest {

    @Test
    void testCalculatePasswordWithInputFile() {
        // 1. Definir la ruta al archivo.
        String rutaArchivo = "src/test/resources/Day05input.txt";

        // 2. Cargar las rotaciones
        // Nota: Si el archivo no existe, esto lanzará la RuntimeException que definiste
        List<String > inputData = SolveDay5B.loadCode(rutaArchivo);

        // Verificación básica de que se leyó algo
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        // 3. Ejecutar la lógica principal
        long resultado = SolveDay5B.calculateTotalFreshIngredients(inputData);

        // 4. Imprimir el resultado en consola
        System.out.println("--------------------------------------------------");
        System.out.println("El valor es: " + resultado);
        System.out.println("--------------------------------------------------");

        // 5. Comprobar que da el resutlado deseado
        // La condicion actual es un ejemplo
        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
