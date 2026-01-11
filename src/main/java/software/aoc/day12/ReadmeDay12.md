DIA 12 
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto de optimización combinatoria y geometría computacional (Packing Problem).
El objetivo es determinar si un conjunto específico de piezas (formas geométricas definidas por caracteres '#') 
puede encajar perfectamente dentro de una región rectangular de dimensiones dadas (Ancho x Alto).
El problema tiene dos fases:
-Definición de formas: Se parsean bloques de texto que representan las piezas disponibles.
-Consultas (Queries): Se procesa una lista de solicitudes, cada una pidiendo encajar una lista específica de IDs de piezas
en un área determinada.
Las piezas pueden rotarse (90, 180, 270 grados) y voltearse (flip), lo que aumenta exponencialmente la complejidad
del espacio de búsqueda.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Debido a la alta complejidad computacional (NP-Hard en su forma general) el diseño prioriza la eficiencia
el paralelismo y la inmutabilidad de datos.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
En `Parser.parse`: Centraliza la compleja lógica de lectura que debe distinguir entre definiciones de formas y líneas de consulta
en el mismo archivo.
En la lógica de `Shape`: Se generan variaciones (rotaciones/flips) al instanciar la forma, actuando como 
una fábrica de configuraciones válidas.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Backtracking (Vuelta Atrás): El núcleo de la solución (`solveRecursive`). Es un algoritmo recursivo que intenta colocar una pieza
si tiene éxito, intenta con la siguiente. Si llega a un callejón sin salida, deshace el último paso (backtrack) y prueba 
una orientación o posición diferente.
*Parallel Streams (Pipeline): Se utiliza `parallelStream()` para procesar las múltiples consultas (`queries`) simultáneamente
aprovechando todos los núcleos del procesador. Esto es vital dado el coste computacional de cada consulta.
*State (Implícito): La matriz booleana `grid` en la recursión mantiene el estado actual del tablero, modificándose y restaurándose
en cada paso del backtracking.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Flyweight (Optimización): La clase `Shape` pre-calcula y almacena todas las variaciones únicas
(rotaciones y espejos) de una forma al inicio. Durante la recursión, no se crean nuevos objetos geométricos
sino que se reutilizan las listas de puntos normalizadas ahorrando memoria y ciclos de CPU.
*Adapter: Manejo encapsulado de excepciones en la carga de archivos.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Value Objects (Records): Uso extensivo de `record` (`Piece`, `Point`, `RegionQuery`, `InputData`) para garantizar la inmutabilidad
de los datos que viajan entre el parser y el solucionador.
*Separación de Intereses (SoC):
`Parser`: Interpreta el formato de texto mixto.
`Shape`: Maneja la lógica geométrica (normalizar coordenadas, rotar matrices).
`SolveDay12A`: Maneja la lógica de orquestación y concurrencia.
*Precomputación: En lugar de rotar las piezas "al vuelo" durante la recursión (lo cual sería lentísimo)
`Shape` genera todas las permutaciones válidas (`generateUniqueVariations`) una sola vez durante el parsing.
-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Inmutabilidad: Al usar `records` y listas inmutables para definir las formas, se evitan efectos colaterales peligrosos
cuando múltiples hilos leen los datos de las formas simultáneamente.
*Concurrencia Segura: Se utiliza `AtomicLong` para contar los éxitos dentro del `parallelStream`
evitando condiciones de carrera (race conditions) sin necesidad de bloqueos complejos (`synchronized`).
*Alta Cohesión: La clase `Shape` es altamente cohesiva; contiene tanto los datos de la matriz como los métodos 
para manipularla (rotar, flip, normalizar), sin depender de factores externos.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*KISS (Keep It Simple, Stupid) vs Performance: Aunque el Backtracking es complejo
la implementación se mantiene lo más simple posible: intentar poner -> recurrir -> quitar.
No se implementan heurísticas complejas excesivas a menos que sean necesarias (como el "slack" o holgura).
*SRP (Principio de Responsabilidad Única):
`Point`: Representa una coordenada 2D.
`Shape`: Representa la geometría y sus variaciones.
`SolveDay12A`: Ejecuta el algoritmo de búsqueda.
*DRY (Don't Repeat Yourself): La lógica de validación de límites (`canPlace`) se centraliza en un método
reutilizada para cualquier variación de cualquier pieza.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Métodos como `canFitPresents`, `solveRecursive` o `place` describen acciones claras.
*Manejo de Primitivos: Se utiliza una matriz de `boolean[][]` para el tablero en lugar de objetos complejos
optimizando el acceso a memoria y la velocidad de comprobación, crítico en algoritmos recursivos profundos.
*Normalización: La clase `Shape` normaliza las coordenadas (llevando el punto superior izquierdo a 0,0)
lo que simplifica enormemente la lógica de colocación en el grid principal.