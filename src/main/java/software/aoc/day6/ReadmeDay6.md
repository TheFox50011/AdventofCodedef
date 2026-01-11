---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent centrado en el análisis sintáctico (Parsing)
de expresiones matemáticas no convencionales.
El objetivo es procesar un texto donde múltiples operaciones matemáticas están dispuestas visualmente en columnas o bloques 
separados por espacios vacíos.
En esta parte, el algoritmo debe identificar los límites de cada "problema" (conjunto de columnas contiguas),
extraer los tokens (números y operadores '+' o '*') y calcular el resultado acumulado de todas las operaciones.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño se centra en la modularización del proceso de parsing para manejar la complejidad de la entrada bidimensional.

-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: en `loadCode`. Centraliza la lectura del archivo y el manejo de excepciones de E/S,
devolviendo una estructura de datos limpia (`List<String>`) al resto de la aplicación.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Strategy (Implícito): La lógica de resolución (`solve`) actúa de forma polimórfica basada en el operador detectado ('+' vs '*'). Aunque se implementa con condicionales, define dos estrategias de reducción distintas (suma vs producto).
*Pipeline (Streams): Se utiliza para sumar los resultados finales (`mapToLong(ProblemBlock::solve).sum()`), favoreciendo un estilo declarativo.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Adaptación de `IOException` a `RuntimeException` en la capa de persistencia.
*Composite (Ligero): La clase interna `ProblemBlock` actúa como un componente que agrupa una colección de 
tokens y una operación, encapsulando la lógica de "cómo resolverse a sí mismo".

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesion:En la separacion de  la lógica de 
"saber donde empieza y termina un problema" (`SolveDay6A`) de la lógica de "cómo calcular el resultado" (`ProblemBlock`).
exponiendo solo un método `solve()` al calculador principal.
*Abstracción: El método `calculateGrandTotal` opera a alto nivel (parsear -> resolver -> sumar),
ignorando los detalles de cómo se extrae texto de una cuadrícula de caracteres.
*Clean Code: Uso de métodos auxiliares como `isColumnEmpty` para mejorar la legibilidad de la lógica de detección de bloques visuales.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
La estructura visual de los bloques se mantiene (columnas separadas por espacios), pero la semántica de lectura cambia radicalmente.
Ahora, los números no se leen horizontalmente línea por línea, sino **verticalmente** columna por columna dentro de cada bloque.
El dígito más significativo está arriba y el menos significativo abajo.
Esto requiere reescribir la lógica de parsing para iterar primero por columnas (x) y luego por filas (y),
construyendo los números mediante `StringBuilder`.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se adapta la solución para manejar un flujo de datos vertical, 
eliminando la clase intermedia para un procesamiento más directo y eficiente.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Reutilización consistente de `loadCode`.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Uso explícito y anidado de bucles para recorrer la matriz de caracteres en el orden específico (columna-major order) requerido para reconstruir los números verticales.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Encapsulamiento de excepciones de E/S.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión: `SolveDay6B` contiene toda la lógica necesaria para interpretar la matriz verticalmente.
Al no depender de la clase `ProblemBlock` de la Parte A (que estaba diseñada para lectura horizontal),
evita un acoplamiento forzado e incorrecto.
*Modularidad: El método `solveBlock` aísla completamente la lógica de "cómo leer un bloque vertical" del algoritmo 
principal de detección de columnas vacías.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
    `MainDay6B`: Orquestación.
    `SolveDay6B`: Lógica de parsing vertical y aritmética.
*KISS (Keep It Simple, Stupid): En lugar de rotar toda la matriz de caracteres para reutilizar 
    el código de la Parte A (lo cual sería costoso en memoria), se opta por simplemente cambiar
    el orden de los bucles de lectura (`for col` -> `for row`), que es la solución más simple y eficiente.
*DRY (Don't Repeat Yourself): Aunque la lógica de detección de columnas vacías (`isColumnEmpty`) es similar a la Parte A, 
    la lógica de extracción es fundamentalmente distinta. Se reutilizan conceptos pero se implementa la variación 
    específica sin forzar abstracciones prematuras.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: El uso de `numBuilder` sugiere claramente que el número se está construyendo pieza a pieza.
*Manejo de Complejidad: El algoritmo maneja elegantemente el caso de líneas de diferente longitud
dentro del bucle de lectura vertical (`if (c >= line.length()) continue;`), evitando `IndexOutOfBoundsException` 
sin ensuciar la lógica principal.