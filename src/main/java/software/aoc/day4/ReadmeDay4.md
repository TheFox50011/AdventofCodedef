--------------------------------------------------------------------------------------------------------------------------------------
DIA 4
-------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------
BASE DEL PROBLEMA
-------------------------------------------------------------------------------------------------------------------------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent basado en el análisis de una cuadrícula  (Grid).
El objetivo es procesar un mapa de caracteres donde el símbolo '@' representa un elemento ("rollo") 
y determinar cuántos de estos son "accesibles".
En esta parte, un rollo se considera accesible si tiene menos de 4 vecinos inmediatos (en las 8 direcciones posibles:
horizontal, vertical y diagonal) que también sean rollos.
-------------------------------------------------------------------------------------------------------------------------------------
PATRONES Y DISEÑOS UTILIZADOS
-------------------------------------------------------------------------------------------------------------------------------------
El código utiliza un enfoque  limpio priorizando la legibilidad y la semántica

-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: En `loadGrid`. Se mantiene el patrón de encapsular 
la lectura del archivo y la conversión a `List<String>`, ocultando la complejidad.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator (Refactorizado): Se utiliza un enfoque declarativo para recorrer 
vecinos mediante una constante de vectores (`DIRECTIONS`) eliminando la complejidad ciclomática de bucles anidados.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En `loadGrid`, se adapta la excepción verificada `IOException` a una no verificada `RuntimeException`.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS 
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción Semántica: El código se lee como lenguaje natural. El método principal contiene `if (isAccessible(...))`,
delegando la lógica matemática compleja a métodos privados con nombres descriptivos.
*Clean Code: El umbral de vecinos (4) y los caracteres especiales ('@') se han extraído a constantes 
con nombre (`MAX_NEIGHBORS_FOR_ACCESS`, `TARGET_CHAR`), facilitando el mantenimiento si las reglas cambian.
*Fail-Fast: Las validaciones de límites (`isOccupied`) 
retornan `false` inmediatamente si la coordenada es inválida, evitando el anidamiento profundo (Arrow Anti-pattern).

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
- `countAccessibleRolls`: Orquestador del recorrido.
- `isAccessible`: Validador de la regla de negocio.
- `countNeighbors`: Motor de búsqueda espacial.
--------------------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
------------------------------------------------------------------------------------------------------------------------------------
El problema evoluciona de un análisis estático a una simulación dinámica.
Ahora no solo contamos los accesibles, sino que los eliminamos iterativamente hasta que el 
tablero se estabilice (ningún rollo cumpla la condición de eliminación).
------------------------------------------------------------------------------------------------------------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------------------------------------------------------------------------------------------------------------
Se estructura la solución para gestionar la mutabilidad del estado.

-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: `loadCode` para la carga inicial.

--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*State / Simulation Loop: Un bucle `while(true)` que representa el ciclo de vida de la simulación,
con condiciones de parada explícitas.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo de excepciones.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*CQS (Command-Query Separation): Se separa estrictamente la lógica en dos fases dentro del bucle:
-Query (`identifyRemovableItems`): Analiza el tablero y decide qué debe cambiar, sin modificar nada. 
-Command (`removeItems`): Ejecuta los cambios sobre el tablero basándose en la lista obtenida.
Esto evita efectos colaterales (bugs donde eliminar un elemento afecta al cálculo del vecino siguiente en el mismo paso).
*Alta Cohesión: La lógica de vecindad se encapsula utilizando vectores de dirección (`DIRECTIONS`)
centralizando la definición de "qué es un vecino" en un solo lugar.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*OCP (Open/Closed Principle): El uso de la matriz `DIRECTIONS` hace que el código esté abierto a extensión (ej: cambiar a vecindad de Von Neumann de 4 direcciones)
pero cerrado a modificación del algoritmo de conteo.
*KISS (Keep It Simple, Stupid): Uso de matriz de `char[][]` para mutabilidad eficiente, envuelta en métodos semánticos para no perder expresividad.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Métodos como `shouldBeRemoved` expresan la intención del negocio, ocultando la comparación numérica (`< 4`).
*Eliminación de Bucles Anidados: El uso de `for (int[] dir : DIRECTIONS)` 
reemplaza los bucles anidados `for(dr)...for(dc)` y sus condicionales `if(0,0)`
reduciendo drásticamente el ruido visual y la carga cognitiva.