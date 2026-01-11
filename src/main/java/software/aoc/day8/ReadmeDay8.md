DIA8
---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico basado en la conectividad de redes en un espacio tridimensional.
El objetivo es procesar una lista de coordenadas (x, y, z) que representan nodos y agruparlos en circuitos conectados.
La lógica de conexión es "codiciosa" (Greedy): se priorizan las conexiones entre los puntos más cercanos entre sí.
En esta parte, se procesan un número limitado de conexiones (1000) y se busca el producto del tamaño de los tres grupos 
(circuitos) más grandes resultantes.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño destaca por el uso de estructuras de datos inmutables y la delegación de la lógica de grafos a una clase especializada.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
- [cite_start]En `loadCode`: Para centralizar la lectura de archivos[cite: 153].
- En `Point3D.parse`: Método estático que encapsula la lógica de conversión de String a Objeto, actuando como una fábrica ligera de puntos.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
[cite_start]*Iterator: Uso explícito mediante bucles `for` para recorrer la lista de conexiones y procesar la fusión de grupos[cite: 97].
*Strategy (Implícito): El uso de `Comparator.comparingLong` define la estrategia de ordenación de las conexiones basada
en la distancia.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Encapsulamiento de excepciones (`IOException` -> `RuntimeException`) en la capa de persistencia[cite: 197].

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: La clase `CircuitManager` oculta la complejidad del algoritmo "Union-Find" (Disjoint Set Union). 
El solucionador principal solo dice "conecta A con B" sin saber cómo se gestionan los árboles internos[cite: 16].
*Value Objects (Records): Se utilizan `record` de Java (`Point3D`, `Connection`) para modelar datos inmutables. 
Esto elimina el "boilerplate" y garantiza que los datos no cambien inesperadamente.
*Modularidad: La lógica se ha dividido en un paquete `auxiliar` reutilizable, 
reduciendo el acoplamiento entre la definición del problema y las herramientas para resolverlo[cite: 40].
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema evoluciona de una agrupación parcial a una conectividad total.
El objetivo ahora es continuar conectando los puntos más cercanos (en orden ascendente de distancia) 
hasta que **todos** los puntos formen parte de un único componente conectado (un solo circuito gigante).
El resultado final depende de las coordenadas de los dos últimos puntos que lograron cerrar el sistema en un solo grupo.
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Se reutiliza la arquitectura modular de la Parte A, demostrando la flexibilidad del diseño ante cambios
en los requisitos de parada del algoritmo.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Reutilización de `loadCode` y `Point3D.parse`[cite: 153].
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Se recorre la lista de conexiones ordenadas hasta cumplir la condición de parada (1 solo componente restante)[cite: 96].
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo encapsulado de excepciones[cite: 196].

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión:
- `SolveDay8B`: Se enfoca únicamente en el flujo del algoritmo (parsear -> ordenar -> conectar -> verificar fin).
- `CircuitManager`: Se enfoca únicamente en la gestión de conjuntos disjuntos.No sabe de coordenadas ni de archivos[cite: 259].
- `Point3D`: Se enfoca únicamente en datos espaciales y distancias geométricas.
*Bajo Acoplamiento: `SolveDay8B` no depende de la implementación interna de `CircuitManager` (arrays `parent` o `size`),
- solo de sus métodos públicos (`connect`, `getComponentCount`)[cite: 260].

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*DRY (Don't Repeat Yourself): Gracias a la extracción del paquete `software.aoc.day8.auxiliar`, 
las clases `Point3D`, `Connection` y `CircuitManager` se definen una sola vez y se usan en ambos días,
eliminando código duplicado[cite: 285].
*OCP (Principio Abierto/Cerrado): La clase `CircuitManager` estaba abierta a la extensión. 
Se añadió el campo `componentCount` y el método `getComponentCount`
para soportar la Parte B sin romper la funcionalidad de la Parte A[cite: 275].
*SRP (Principio de Responsabilidad Única): Cada clase tiene una única razón para cambiar. 
Si cambia la fórmula de la distancia, solo tocamos `Point3D`. [cite_start]Si cambia la lógica de unión de grupos,
solo tocamos `CircuitManager`[cite: 274].
*KISS (Keep It Simple, Stupid): Se utilizan `records` para los datos, que es la forma más simple y directa de definir
estructuras de datos en Java moderno, evitando clases verbosas innecesarias.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------*
Código Expresivo: El uso de `record` hace explícito que `Point3D` y `Connection` 
son portadores de datos inmutables[cite: 263].
*Nomenclatura Intencional: El método `distanceSqTo` indica claramente que devuelve la distancia
al cuadrado (para evitar raíces cuadradas costosas e innecesarias para ordenar), 
comunicando la intención de optimización en el propio nombre[cite: 10].