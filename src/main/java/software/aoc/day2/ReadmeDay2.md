--------------------------------------------
DIA2
---------------------------------------------------------------------------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent basado en el análisis 
de rangos numéricos de identificadores (IDs).
El objetivo es calcular la suma de todos los IDs "inválidos" dentro de una lista de rangos dados (ej. "10-20,30-40").
En esta parte, un ID se considera inválido si su longitud es par y está compuesto por dos mitades idénticas (por ejemplo, "1212" o "55").
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Al igual que en el día anterior, al ser un código enfocado en algoritmos directos, la aplicación de patrones es ligera.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: en loadInput. En lugar de instanciar un lector de archivos directamente en el main,
pasamos la carga del contenido del archivo a un método estático que encapsula la E/S y devuelve el String crudo.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Patrón Iterator: Mediante el bucle for-each de Java al recorrer los rangos (ranges). Esto nos permite procesar
cada intervalo numérico sin exponer la lógica de cómo se dividió la cadena original.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En loadInput, se adapta IOException a RuntimeException para no ensuciar el main con bloques try-catch obligatorios.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: SolveDay2A expone solo lo esencial (calculateInvalidIdsSum), ocultando la lógica de validación
del ID mediante un método private (isInvalidId).
*Modularidad: El código está dividido en clases con responsabilidades únicas (Main para ejecución, Solve para lógica)
facilitando el mantenimiento.
*Clean Code: Uso de nombres descriptivos (start, end, first, second) que explican la intención de partir el número en dos mitades.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema base es el mismo (sumar IDs inválidos en rangos), pero cambia la definición de "inválido".
Ahora un ID es inválido si está formado por la repetición de un sub-bloque de cualquier tamaño, no solo por la mitad.
(Ejemplo: "123123" es válido en la parte B porque repite "123", aunque no sea simplemente dos mitades de un solo dígito).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
La estructura del código es idéntica a la Parte A, variando únicamente la complejidad del algoritmo  de validación.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: en loadInput al igual que en la *PARTE A*,
delegando la creación de la cadena de entrada a un método estático auxiliar.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Patrón Iterator: Uso implícito en los bucles for-each para recorrer los rangos obtenidos del input.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En loadInput, se adapta el manejo de excepciones de E/S a excepciones en tiempo de ejecución, igual que en la *PARTE A*.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: La complejidad algorítmica de buscar patrones repetitivos se oculta completamente dentro del método privado isInvalidId.
El método público calculateInvalidIdsSum no cambia respecto a la Parte A, demostrando buena abstracción.
*Modularidad: Separación clara entre la entrada de datos y el procesamiento lógico.
*Codigo Expresivo: El uso de StringBuilder y variables como blockSize ayudan a entender que se está reconstruyendo el patrón para compararlo.
*Alta Cohesión: La clase SolveDay2B se encarga únicamente de la lógica de negocio de los IDs.
-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única): Cada clase tiene una única razón para cambiar.
- 'MainDay2B': Cambia solo si modificamos cómo ejecutar el programa (ej. leer argumentos de consola).
- 'SolveDay2B': Cambia solo si modificamos las reglas de validación de los IDs.
  *KISS (Keep It Simple, Stupid): En la validación de la Parte B, en lugar de usar Regex complejos o streams avanzados, se utiliza una lógica de bucles anidados explícita y legible para encontrar los bloques repetidos.
  *DRY (Don't Repeat Yourself): Aunque 'loadInput' aparece en ambas clases (A y B) por ser ejercicios aislados, internamente se reutiliza la lógica de validación para cada ID dentro del bucle, evitando duplicar el código de chequeo.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO 
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Variables como 'blockSize', 'repetitions' o 'repeated' comunican claramente la intención del algoritmo de reconstrucción de cadenas.
*Funciones Pequeñas: Los métodos se mantienen cortos y enfocados, facilitando su lectura y testeo.