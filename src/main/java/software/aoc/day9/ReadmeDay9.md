---------------------------------------------------------
DIA 9
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico basado en geometría computacional.
El objetivo es procesar una lista de coordenadas ("azulejos rojos") y encontrar el área rectangular más grande
que se puede formar eligiendo dos puntos cualesquiera de la lista como vértices opuestos.
En esta parte, se asume que cualquier par de puntos forma un rectángulo válido, y el área se calcula de forma directa: 
(ancho + 1) * (alto + 1).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño es directo y pragmático, enfocado en iterar pares de puntos utilizando estructuras de datos inmutables y limpias.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:
- En `loadCode`: Centraliza la lectura de archivos y gestión de excepciones.
- Uso implícito en la creación de listas dentro de `parseInput`, encapsulando la conversión de Strings a objetos `Point`.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Uso explícito mediante bucles anidados (`for i`, `for j`) para recorrer la lista de puntos
y generar todas las combinaciones de pares posibles sin exponer la estructura interna más allá de lo necesario.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En `loadCode`, se adapta la excepción verificada `IOException` a una `RuntimeException` no verificada, 
simplificando la interfaz para el cliente (`MainDay9A`).

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Value Objects (Records): Se utiliza el `record Point(int x, int y)` para modelar las coordenadas. Esto garantiza inmutabilidad,
igualdad por valor y semántica de datos pura sin "boilerplate".
*Abstracción: El método `solve` orquesta el flujo (parsear -> calcular -> maximizar) delegando el cálculo matemático específico 
al método privado `calculateArea`. Esto oculta los detalles aritméticos del flujo principal.
*KISS (Keep It Simple, Stupid): Para esta parte, una solución de fuerza bruta O(N^2) es suficiente y mucho más legible 
que optimizaciones prematuras.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema aumenta drásticamente en complejidad. Ahora, el rectángulo formado por dos puntos solo es válido si está **completamente contenido** dentro del polígono definido por los bordes.
Esto requiere verificar si todos los "píxeles" dentro del rectángulo son válidos (no son exterior).
Dado que las coordenadas pueden ser enormes, se implementan lo siguiente:
1. **Compresión de Coordenadas**: Mapear un espacio gigante a un grid manejable conservando la topología.
2. **(BFS)**: Para distinguir qué partes del grid comprimido son "exterior" y cuáles "interior".
3. **Prefix Sum 2D (Tablas de Suma)**: Para verificar la validez de un área rectangular en tiempo constante O(1).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El diseño se vuelve altamente modular. Se ha extraído toda la complejidad algorítmica a una clase auxiliar `Coordinates`
dejando el solucionador limpio.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Reutilización de `loadCode` para la persistencia.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Se utiliza tanto en la compresión de coordenadas como en el barrido final de pares de puntos en `SolveDay9B`.
*Command (Conceptual): Las operaciones estáticas en `Coordinates` (`drawCompressedBoundary`, `fillInterior`, `buildPrefixSum`) actúan como comandos secuenciales que transforman el estado del grid paso a paso.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo encapsulado de excepciones de E/S.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Alta Cohesión:
- `Coordinates`: Agrupa estrictamente la lógica de manipulación del espacio (compresión, flood fill, prefix sums). No sabe nada de archivos ni del flujo del problema.
- `SolveDay9B`: Se enfoca únicamente en el flujo de negocio (coordinar los pasos y buscar el máximo).
*Bajo Acoplamiento: `SolveDay9B` utiliza los métodos estáticos de `Coordinates` como una librería de utilidades.
- Si cambiamos el algoritmo de `fillInterior` (ej. de BFS a DFS), el solucionador no necesita cambios.
*Separación de Intereses (SoC): Se separa claramente la **Preparación del Grid** (compleja y técnica) de 
la **Consulta del Grid** (lógica de negocio).

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
- `Point`: Datos.
- `Coordinates`: Algoritmia espacial y matemática.
- `SolveDay9B`: Orquestación y reglas de negocio (validación de rectángulo).
*DRY (Don't Repeat Yourself): La lógica para mapear coordenadas reales a índices comprimidos y viceversa 
se centraliza mediante los mapas (`mapX`, `mapY`) y métodos reutilizables en `Coordinates`.
*Eficiencia (Performance Design): El uso de **Prefix Sums** es una decisión de diseño crítica
Transforma la verificación de un rectángulo de una operación lineal O(W*H) a una constante O(1), haciendo viable la solución.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Nombres como `getRelevantCoordinates`, `drawCompressedBoundary` y `fillInterior` en la clase auxiliar
comunican claramente qué transformación se está aplicando a los datos.
*Funciones Pequeñas: La extracción de la lógica a `Coordinates` ha permitido que el método `solve` principal 
se lea como un guion de alto nivel, sin estar contaminado por bucles `while` de BFS o matrices anidadas de sumas.