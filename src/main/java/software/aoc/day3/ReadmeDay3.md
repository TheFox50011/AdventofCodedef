DIA 3
---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto algorítmico de Advent enfocado en optimización de valores numéricos (Joltaje).
El objetivo es procesar una lista de "bancos" (cadenas de dígitos) y encontrar, para cada banco, la combinación de dos dígitos
que maximice el "Joltaje".
En esta parte, el "joltaje" de un par de dígitos se calcula tomando el primer dígito como decenas y el segundo como unidades 
(ej. dígitos '3' y '5' forman 35).
Se busca el valor máximo posible combinando cualquier par de dígitos en el orden en que aparecen.


------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
El código hace un uso intensivo de la API de Streams de Java 8, lo que introduce un estilo más funcional y declarativo en comparación con los días anteriores.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: en LoadCode. Al igual que en días previos, encapsula la complejidad de abrir, leer y convertir el archivo en una Lista de Strings, devolviéndola limpia al consumidor.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Pipeline de Procesamiento (Streams): En lugar de bucles for explícitos, se utiliza un flujo de operaciones (map, flatMap, max) para transformar los datos. Esto es una forma moderna del patrón Iterator y Filter.
*Estrategia (Implícito): Las operaciones `mapToInt` y `flatMap` definen la estrategia de transformación de los datos paso a paso.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En LoadCode, se adapta la IOException verificada a una RuntimeException no verificada, simplificando el manejo de errores en el flujo principal.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS
-------------------------------------------------------------------------------------------------------------------------------------
*Programación Funcional: Se prioriza el uso de expresiones lambda y method references (SolveDay3A::maxJoltageForBank) sobre la programación imperativa clásica.
*Abstracción: El método público calculateTotalJoltage orquesta el proceso, mientras que maxJoltageForBank oculta la lógica combinatoria específica.
*Inmutabilidad: Al usar Streams, se favorece el procesamiento de datos sin modificar las estructuras originales.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
-------------------------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema evoluciona hacia un reto de selección de subconjuntos. Ahora, para cada banco de dígitos, debemos eliminar dígitos estratégicamente hasta quedarnos con exactamente 12 dígitos.
El objetivo es maximizar el valor numérico resultante formado por esos 12 dígitos, manteniendo su orden relativo original.
Este es un problema clásico de "pila monótona" (monotonic stack) o algoritmo voraz (greedy).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Aunque la estructura externa se mantiene, la lógica interna cambia de funcional pura a algorítmica imperativa para optimizar el rendimiento.
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method: Se mantiene en LoadCode para la carga transparente de datos desde el sistema de archivos.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Iterator: Se utiliza tanto en el Stream principal (para procesar cada línea) como en el bucle 'for-each' interno que recorre los caracteres del banco de dígitos.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: Manejo encapsulado de excepciones de E/S, consistente con el resto del proyecto.

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: La compleja lógica de decisión sobre qué dígitos eliminar se encapsula totalmente en 'maxJoltageForBank'. El cliente solo ve una entrada (String) y una salida (valor máximo).
*Modularidad: Separación estricta entre la capa de persistencia (LoadCode), la lógica de negocio (SolveDay3B) y la ejecución (Main).
*Alta Cohesión: La clase SolveDay3B se dedica exclusivamente a resolver el problema del "mayor número posible con N dígitos", sin mezclar responsabilidades de UI o configuración.

-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única):
- 'MainDay3B': Responsable solo de la orquestación de alto nivel.
- 'SolveDay3B': Responsable solo de la algoritmia matemática.
  *KISS (Keep It Simple, Stupid): Se utiliza un enfoque voraz con una pila (StringBuilder) que es O(N), mucho más simple y eficiente que intentar generar todas las combinaciones posibles recursivamente.
  *DRY (Don't Repeat Yourself): La constante DIGITS_TO_KEEP = 12 se define una sola vez, evitando números mágicos dispersos por el código y facilitando cambios futuros.

--------------------------------------------------------------------------------------------------------------------------
-DISEÑO DE CÓDIGO (CLEAN CODE)
--------------------------------------------------------------------------------------------------------------------------
*Nomenclatura Intencional: Variables como 'digitsToRemove' y 'stack' comunican claramente la intención del algoritmo de eliminación.
*Uso de Constantes: 'DIGITS_TO_KEEP' hace el código más legible que usar el literal '12' directamente.
*Eficiencia: El uso de StringBuilder como una pila mutable evita la sobrecarga de crear múltiples objetos String inmutables en cada iteración.