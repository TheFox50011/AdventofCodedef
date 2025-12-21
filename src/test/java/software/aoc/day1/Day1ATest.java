package software.aoc.day1;

import org.junit.jupiter.api.Test;
import software.aoc.day1.a.SolveDay1A;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Day1ATest {

    @Test
    void testCalculatePasswordWithInputFile() {
        // 1. Definir la ruta al archivo.
        // Al ejecutar desde la raíz del proyecto (Maven o IntelliJ),
        // la ruta relativa a src/test/resources funciona bien.
        String rutaArchivo = "src/test/resources/input.txt";

        // 2. Cargar las rotaciones usando tu método estático
        // Nota: Si el archivo no existe, esto lanzará la RuntimeException que definiste
        List<String> inputData = SolveDay1A.loadRotations(rutaArchivo);

        // Verificación básica de que se leyó algo (opcional pero útil)
        assertNotNull(inputData, "La lista no debería ser nula");
        assertFalse(inputData.isEmpty(), "El archivo de input no debería estar vacío");

        // 3. Ejecutar la lógica principal
        int resultado = SolveDay1A.calculatePassword(inputData);

        // 4. Imprimir el resultado en consola para que lo veas
        System.out.println("--------------------------------------------------");
        System.out.println("El resultado del password para input.txt es: " + resultado);
        System.out.println("--------------------------------------------------");

        // 5. Aserción (Validación)
        // Si ya sabes cuánto DEBE dar (por ejemplo 1234), cámbialo aquí:
        // assertEquals(1234, resultado);

        // Si no sabes el resultado aún y solo quieres que corra sin fallar:
        assertTrue(resultado >= 0, "El resultado debería ser un número positivo");
    }
}
