DIA 5
---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent basado en la gestión de intervalos numéricos (Ranges).
El objetivo es procesar una lista de rangos válidos (ej. "50-100") y una lista de identificadores individuales ("ingredientes").
Se debe determinar cuántos de esos identificadores son "frescos", es decir, cuántos caen dentro de al menos uno de los rangos especificados.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño destaca por la extracción de la lógica de dominio a una clase común, limpiando la lógica del controlador principal.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
- En `loadCode`: Para la carga de archivos.
- En `Range.fromString`: Método estático en la clase compartida que encapsula el parseo de strings ("start-end") para fabricar objetos.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Streams : Uso declarativo de `filter` y `anyMatch` para procesar la lista de IDs contra los rangos.
*Strategy : La lógica de validación `range.contains(id)` actúa como la estrategia de filtrado intercambiable.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo de excepciones de E/S encapsulado en `loadCode`, adaptando `IOException` a `RuntimeException`.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: La clase compartida `Range` oculta la complejidad matemática de comprobar límites (`contains`).
El cliente (`SolveDay5A`) ignora la implementación interna.
*Modularidad: Al mover `Range` a un paquete externo/común, `SolveDay5A` actúa como un consumidor de un módulo independiente
reduciendo la duplicidad.
*Clean Code: Uso de métodos auxiliares (`parseInput`) para mantener el nivel de abstracción del método principal consistente.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema escala a la gestión masiva de números. Se pide calcular la *cantidad total* de valores enteros cubiertos por los rangos.
Dado que los rangos pueden solaparse (ej. "1-10" y "5-15"), se requiere un algoritmo de fusión (Merge Intervals) para evitar conteos dobles.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se aprovecha la reutilización de la clase `Range` común, extendiendo su uso con lógica de comparación y fusión en el solucionador.

-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Reutilización de `Range.fromString` para la creación consistente de objetos desde texto.

--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Comparable: La clase `Range` implementa `Comparable<Range>` permitiendo el uso de `Collections.sort()` basado en el "orden natural" (inicio del rango) sin necesidad de comparadores externos.
*Iterator: Uso explícito en el algoritmo de fusión para recorrer la lista ordenada y comparar secuencialmente el elemento `current` con `next`.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL   
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Encapsulamiento de excepciones en la lectura de archivos.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión: La clase `Range` agrupa estrictamente la lógica de intervalos (contención, tamaño, intersección). No contiene lógica de negocio del puzzle (como leer archivos o imprimir), lo que la hace altamente cohesiva.
*Reusabilidad: Al extraer `Range` a un paquete externo, se convierte en un componente reutilizable para ambas partes (A y B) y potenciales futuros problemas, eliminando código duplicado.
*Bajo Acoplamiento: `SolveDay5B` depende de la interfaz pública de `Range` 
no de sus campos internos (`start`/`end`) permitiendo cambios en la representación interna de `Range` sin romper el solucionador.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*DRY (Don't Repeat Yourself): Al unificar `Range` en una sola clase externa se ha eliminado la duplicación de lógica (constructores
getters, lógica de overlap) que existía anteriormente entre los paquetes A y B.
*SRP (Principio de Responsabilidad Única):
- `Range`: Integridad de datos matemáticos del intervalo.
- `SolveDay5B`: Algoritmo de fusión y cálculo de totales.
*OCP (Open/Closed Principle): La clase `Range` está cerrada a modificaciones (es final e inmutable en sus propiedades) 
pero abierta a ser utilizada en distintos contextos de ordenación gracias a `Comparable`.
*KISS (Keep It Simple, Stupid): El algoritmo de fusión utiliza un enfoque lineal iterativo sobre una lista ordenada
evitando la complejidad de estructuras de árbol.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Inmutabilidad: `Range` se comporta como un Value Object inmutable. Operaciones como `merge` devuelven
una nueva instancia en lugar de modificar la existente, previniendo efectos colaterales.
*Nomenclatura Intencional: Métodos como `overlapsOrAdjoins` comunican explícitamente
la regla de negocio compleja (solapamiento o adyacencia) sin necesidad de comentarios.