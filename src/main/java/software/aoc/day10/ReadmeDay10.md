DIA10
---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico basado en la manipulación
de estados binarios (luces encendidas/apagadas).
El objetivo es encontrar la secuencia mínima de botones que deben presionarse para lograr 
una configuración de luces objetivo (Target State).
Cada botón invierte el estado de un conjunto específico de luces (operación XOR). 
Dado que se busca el "mínimo" número de pasos, se modela como un problema de camino más corto en un grafo no ponderado.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño aprovecha la eficiencia de las operaciones a nivel de bits (Bitwise operations) para gestionar el estado,
encapsulando la complejidad en objetos inmutables.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
En `loadCode`: Centraliza la lectura de archivos[cite: 153].
En `MachineParser.parse`: Método estático que encapsula la lógica compleja de interpretar el diagrama de luces y convertirlo en máscaras
de bits (`long`), devolviendo una instancia limpia de `Machine`.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Uso explícito mediante el bucle `for-each` para recorrer 
las máscaras de los botones (`machine.buttonMasks()`) dentro del algoritmo BFS[cite: 96].
*Algoritmo de Búsqueda (BFS): Se implementa una búsqueda en anchura (Breadth-First Search) utilizando una `Queue` 
para garantizar que la primera vez que se alcanza el estado objetivo sea con el mínimo número de pulsaciones.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En `loadCode`, se adapta la excepción verificada `IOException` a una `RuntimeException` para simplificar 
el flujo en el `Main`[cite: 197].

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: El uso del record `Machine` oculta la representación del estado. 
Para el algoritmo, una máquina es solo un "estado objetivo" y una "lista de máscaras", 
abstrayendo la complejidad del diagrama visual original[cite: 16].
*Value Objects (Records): Se utiliza `record Machine(long targetState, ...)` para modelar los datos de forma inmutable. 
Esto es crucial en el BFS para asegurar que el estado de la máquina no cambie inesperadamente entre iteraciones.
*Optimización (Bit Manipulation): En lugar de usar arrays de booleanos, se usan variables `long` y operadores XOR (`^`).
Esto permite procesar hasta 64 luces en una sola operación de CPU, haciendo el código extremadamente eficiente y expresivo.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema cambia de naturaleza: la complejidad de las máquinas o el número de estados hace inviable una búsqueda por fuerza bruta (BFS).
Se replantea el problema como un **Sistema de Ecuaciones Lineales**. 
Cada botón es un vector, y buscamos una combinación lineal de botones que resulte en el vector objetivo.
La solución implementa **Eliminación Gaussiana** para resolver la matriz, 
seguida de una búsqueda recursiva para encontrar soluciones enteras válidas (ya que no se puede presionar "medio botón").
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se destaca la Modularidad al separar completamente la implementación de la Parte B en un paquete propio, 
ya que la definición de "Máquina" cambia drásticamente (de bits a vectores de enteros).
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Se mantiene en `MachineParser.parse`, 
pero ahora implementa una lógica de parseo distinta (vectores numéricos) adecuada para la matriz matemática[cite: 153].
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Recursividad (Backtracking): Utilizada en `recursiveSearch` para encontrar la mejor solución entera después 
de simplificar la matriz con Gauss.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo encapsulado de excepciones de E/S.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión:
El paquete `software.aoc.day10.b` es totalmente independiente del `a`.
`SolveDay10B` se centra exclusivamente en álgebra lineal.
`MachineParser` (versión B) se centra solo en parsear listas de enteros[cite: 259].
*Bajo Acoplamiento: Al duplicar y adaptar las clases `Machine` y `Parser` en el paquete B en lugar de forzar
una herencia extraña con la Parte A, se reduce el acoplamiento.Un cambio en la lógica de bits de la Parte A
no rompe la lógica matricial de la Parte B[cite: 260].
*Modularidad: El diseño permite intercambiar el algoritmo de resolución (de BFS a Gauss) sin afectar la estructura general 
del programa (Input -> Parse -> Solve -> Output)[cite: 261].

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
`Machine`: Contenedor de datos (DTO).
`MachineParser`: Lógica de interpretación de texto.
`SolveDay10B`: Lógica matemática compleja.
*KISS (Keep It Simple, Stupid) vs Complejidad Necesaria: Aunque la Eliminación Gaussiana es compleja,
es la solución más simple posible que es computacionalmente eficiente para este tipo de problema a gran escala (O(N^3)) 
comparado con la complejidad exponencial del BFS (O(2^N)).
*DRY (Don't Repeat Yourself): Dentro de `SolveDay10B`, la lógica de intercambio de filas y pivoteo se reutiliza sistemáticamente 
dentro del bucle de eliminación.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Variables como `pivotRow`, `freeVars` (variables libres) y `matrix` reflejan claramente 
conceptos de álgebra lineal, facilitando la lectura para quien conozca el dominio matemático[cite: 10].
*Separación de Intereses: El código separa claramente la fase de "Parsing" (convertir texto a objetos) de 
la fase de "Solving" (lógica pura), cumpliendo con la abstracción[cite: 17].