DIA11
---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent basado en la teoría de grafos.
El objetivo es procesar una lista de conexiones que definen un grafo dirigido (donde "A: B C" significa que desde A se puede ir a B y a C).
El problema solicita calcular el número total de caminos distintos posibles 
desde un nodo de origen ("you") hasta un nodo de destino ("out").
Debido a la naturaleza de los grafos, el número de caminos puede crecer exponencialmente, 
requiriendo algoritmos eficientes de recorrido.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño se centra en la separación de la estructura de datos (el grafo) del algoritmo de recorrido (el contador),
utilizando técnicas de optimización.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
En `loadCode`: Centraliza la lectura de archivos[cite: 665].
En `GraphParser.parse`: Método estático que encapsula la lógica de conversión de líneas de texto
a una estructura de datos `Map<String, List<String>>`, actuando como una fábrica del grafo.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Memoization (Optimización Algorítmica): Implementado en `PathCounter`. Se utiliza un mapa (`memo`) para almacenar 
los resultados de los caminos ya calculados desde un nodo específico. Esto evita recalcular sub-rutas repetidas, transformando una complejidad exponencial en una mucho más manejable.
*Iterator: Uso implícito al recorrer los vecinos de cada nodo (`neighbors`) para expandir 
la búsqueda en profundidad (DFS)[cite: 608].
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Adaptación de `IOException` a `RuntimeException` en la carga de archivos[cite: 709].

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión: Se han separado las responsabilidades en clases auxiliares (`software.aoc.day11.auxiliar`):
`GraphParser`: Solo sabe interpretar texto.
`PathCounter`: Solo sabe contar caminos sobre un mapa dado.
`SolveDay11A`: Solo orquesta la ejecución[cite: 771].
*Abstracción: El problema se modela abstractamente como un Grafo (Map), ocultando si los nodos representan servidores,
habitaciones o ciudades. El algoritmo funciona independientemente de lo que representen los Strings[cite: 528].
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema añade una restricción de ruta compleja. Ahora no basta con ir del inicio al "fin",
se debe pasar obligatoriamente por dos nodos intermedios clave ("dac" y "fft") antes de llegar a la salida ("out").
El orden entre estos dos nodos intermedios importa, por lo que existen dos macro-rutas válidas:
-Inicio -> ... -> "dac" -> ... -> "fft" -> ... -> "out"
-Inicio -> ... -> "fft" -> ... -> "dac" -> ... -> "out"
La solución implica descomponer el problema en segmentos más pequeños y multiplicar 
las combinaciones posibles (Principio de Multiplicación).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se reutiliza la arquitectura modular de la Parte A, demostrando la flexibilidad del diseño para componer soluciones complejas
a partir de piezas simples.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Se mantiene el uso de `GraphParser` y `loadCode`[cite: 665].
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Divide y Vencerás (Algorítmico): El problema global se divide en sub-problemas independientes (caminos de A a B, de B a C, etc.).
*Reuse (Reutilización): La clase `PathCounter` se instancia una sola vez pero se reutiliza
para calcular todos los segmentos necesarios (`counter.countPaths(...)`), aprovechando la lógica de DFS genérica.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo encapsulado de excepciones.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Modularidad: Al haber extraído `PathCounter` a una clase externa en la Parte A, la Parte B simplemente "compone" la solución 
llamando a este componente varias veces con diferentes parámetros, sin duplicar la lógica de búsqueda[cite: 554].
*Bajo Acoplamiento: `SolveDay11B` no conoce los detalles de cómo se realiza la búsqueda en profundidad (DFS) ni cómo se gestiona 
la caché (memoización); solo confía en el contrato público de `PathCounter`[cite: 552].

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
`GraphParser`: Parsing.
`PathCounter`: Algoritmia de grafos.
`SolveDay11B`: Lógica de negocio específica del problema B (rutas compuestas)[cite: 786].
*DRY (Don't Repeat Yourself): La lógica de recorrido de grafos es idéntica en ambas partes y reside en 
un único lugar (`auxiliar.PathCounter`), evitando discrepancias y errores[cite: 797].
*KISS (Keep It Simple, Stupid): En lugar de modificar el algoritmo de búsqueda para soportar "waypoints" (puntos de paso obligatorios)
se optó por una solución matemática simple: calcular tramos individuales y multiplicar los resultados lo cual es más sencillo
de entender y verificar.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Variables como `pathsDacThenFft` describen explícitamente la intención de la ruta calculada, en lugar de usar nombres genéricos como `path1` o `resultA`[cite: 522].
*Legibilidad: El uso de métodos auxiliares y la separación en pasos lógicos hace que la fórmula matemática final (`pathsX + pathsY`) 
sea evidente a primera vista.
