---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto deL Advent of code el cual es una simulación de partículas 
o haces de luz en una cuadrícula.
El objetivo es simular el descenso de "haces" que comienzan en posiciones marcadas con 'S'.
Las reglas de la física de este mundo son:
1. El haz desciende fila por fila.
2. Si encuentra un espacio vacío ('.') o el inicio ('S'), continúa recto hacia abajo.
3. Si encuentra un divisor ('^'), el haz se "divide", generando dos nuevos haces en la siguiente fila: 
uno a la izquierda (col - 1) y otro a la derecha (col + 1).
El objetivo de esta parte es contar cuántos eventos de división (splits) ocurren en total durante la simulación.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño utiliza una simulación iterativa estado a estado, mejorada con estructuras de control modernas para la toma de decisiones.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: En `loadCode`, centralizando la creación de la lista de entrada y la gestión de E/S.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Uso explícito mediante bucles `for` para procesar el grid fila a fila y los haces activos.
*State (Simplificado): La variable `activeBeams` representa el estado del sistema en un momento dado t (fila actual), 
y se calcula el estado t+1 en base a las reglas.
*Branching (Switch Expression): Se utiliza `switch` con sintaxis de flecha (`->`) para seleccionar la estrategia 
de movimiento (dividir vs continuar) según el carácter de la celda. Esto reemplaza cadenas complejas de `if-else`.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Encapsulamiento de excepciones (`IOException` -> `RuntimeException`) en la capa de persistencia.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Estructuras de Datos Adecuadas: Se utiliza un `Set<Integer>` (`HashSet`) para rastrear los haces. 
Esto maneja automáticamente la "fusión" de haces: si dos divisores envían un haz a la misma celda, el Set evita duplicados, 
simplificando la lógica de colisiones.
*Abstracción: El método `findStartColumns` separa la lógica de inicialización del bucle principal de simulación.
*Modularidad: `SolveDay7A` encapsula toda la física de la simulación, exponiendo solo el resultado final.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema cambia conceptualmente de "dónde están los haces" a "cuántas líneas temporales existen".
Cada vez que un haz se divide, el número de líneas temporales se duplica. Dado que esto crece exponencialmente (2^n),
una simulación paso a paso como en la Parte A no seria posible.
La solución implementa **Programación Dinámica (Dynamic Programming)**: en lugar de rastrear cada haz individual, 
contamos *cuántos* haces llegan a cada celda `(r, c)` acumulando los valores de la fila anterior.
El objetivo es sumar todas las líneas temporales que logran "salir" del grid.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se reemplaza la colección de objetos (`Set`) por una matriz numérica de acumulación (`long[][]`)
optimizando el rendimiento, manteniendo la coherencia estructural con la Parte A mediante el uso de `switch`.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Se mantiene en `loadCode`.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Memoization / Tabulation (Dynamic Programming): La matriz `dp[r][c]` actúa como una memoria tabular donde almacenamos la suma de caminos.
*Iterator: Bucles anidados para barrer la matriz y propagar los valores.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo de errores de archivo consistente con el resto del proyecto.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Eficiencia Algorítmica: El cambio de simulación (O(2^N)) a programación dinámica (O(N*M)) es un fundamento clave de diseño 
para escalabilidad.
*Alta Cohesión: La lógica de propagación de valores está contenida estrictamente dentro del bucle de cálculo.
*Abstracción de Datos: Se abstrae el concepto de "línea temporal" a un simple número entero (`long`), 
eliminando la necesidad de objetos complejos.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*OCP (Principio Abierto/Cerrado): El uso del `switch` facilita la extensión. 
Si se añade un nuevo tipo de obstáculo (ej. '#'), solo se añade un nuevo `case`
sin necesidad de modificar la estructura de flujo existente ni cadenas de `if` frágiles.
*KISS (Keep It Simple, Stupid): El uso de una matriz `long[][]` es la forma más simple
y directa de implementar DP para una cuadrícula.
*SRP (Principio de Responsabilidad Única):
- `MainDay7B`: Entrada/Salida.
- `SolveDay7B`: Lógica matemática de acumulación.
  *DRY (Don't Repeat Yourself): La lógica de verificación de bordes se reutiliza dentro de los casos del `switch`.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO
--------------------------------------------------------------------------------------------------------------------------
*Código Expresivo (Switch Expressions): El uso de `switch (tile) { case '^' -> ... }` hace que la intención del código 
sea inmediatamente obvia: estamos despachando lógica basada en el tipo de baldosa.
*Consistencia: Ambas partes (A y B) utilizan la misma estructura de control para resolver la lógica de obstáculos, 
facilitando la lectura.