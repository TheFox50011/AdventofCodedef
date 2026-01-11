---------------------------------------------------------
PARTE A
-----------------------------------------------------------
-------------------
BASE DEL PROBLEMA
-------------------
Este paquete contiene una solución en Java para un reto  algorítmico de Advent basado en rotaciones sobre un dial circular de 100 posiciones.
El objetivo es calcular una "contraseña" basada en cuántas veces el puntero aterriza en la posición 0.
La posicion inicial es 50 y los movimientos se procesan una lista de instrucciones de rotación (derecha R o izquierda L).
------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Al ser un codigo tan simple es complicado aplicar varios patrones tanto estructurales como creacionales al igual que los diseños
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:  en el metodo LoadRotations. En lugar de instanciar un lector de archivos directamente en el main, 
pasamos la creación de la lista de datos a un método estático que encapsula la E/S.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Patrón Iterator: Mediante el bucle for-each de Java. Esto nos permite recorrer la colección 
de rotaciones sin exponer la estructura interna de la lista.
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En LoadRotations, se adapta IOException a RuntimeException para no ensuciar el main.

-------------------------------------------------------------------------------------------------------------------------------------
DISEÑOS 
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: SolveDay1A expone solo lo esencial (CalculatePassword), ocultando los detalles mediante  un private (applyRotation).
*Modularidad: El código está dividido en clases con responsabilidades únicas, lo que facilita la modificación y el mantenimiento.
*Clean Code: Se evitan comentarios excesivos mediante el uso de nomenclatura clara, haciendo el código autoexplicativo.
--------------------------------------------------------------------------------------------------------------------------
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
---------------------------------------------------------------------------------------------------------------------
PARTE B
-------------------------------------------------------------------------------------------------------------
El problema es el mismo que la parte a pero la diferencia es que hay contar cuantas veces pasa por el 0 en vez de
las veces que apunta a 0:
Hay que destacar que la segunda Parte no tiene mucha diferencia entre la parte A en cuanto a patrones y diseños usados

------------------------------
PATRONES Y DISEÑOS UTILIZADOS
------------------------------
Al ser un codigo tan simple es complicado aplicar varios patrones tanto estructurales como creacionales al igual que los diseños
-------------------------------------------------------------------------------------------------------------------------------------
-PATRONES CREACIONALES
-------------------------------------------------------------------------------------------------------------------------------------
*Static Factory Method:  en LoadRotations al igual que en la *PARTE A* ,
en lugar de instanciar un lector de archivos directamente en el main,
pasamos la creación de la lista de datos a un método estático que encapsula la E/S.
--------------------------------------------------------------------------------------------------------------------------------------
-PATRONES COMPORTAMIENTO
-------------------------------------------------------------------------------------------------------------------------------------
*Patrón Iterator: Mediante el bucle for-each de Java. Esto nos permite recorrer la colección
de rotaciones sin exponer la estructura interna de la lista al igual que en la *PARTE A*
---------------------------------------------------------------------------------------------------------------------------------------
-PATRONES ESTRUCTURAL
-------------------------------------------------------------------------------------------------------------------------------------
*Adapter: En LoadRotations, se adapta IOException a RuntimeException para no ensuciar el main al igual que en la *PARTE A*

-------------------------------------------------------------------------------------------------------------------------------------
-FUNDAMENTOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*Abstracción: SolveDay1A expone solo lo esencial (CalculatePassword), ocultando los detalles mediante  un private (applyRotation).
*Modularidad: El código está dividido en clases con responsabilidades únicas, lo que facilita la modificación y el mantenimiento.
En esta Parte lo podemos encontrar en en el método privado moveOneClick.
*Codigo Expresivo
*AltaCohesion
--------------------------------------------------------------------------------------------------------------------------
-DISEÑO
--------------------------------------------------------------------------------------------------------------------------
*Clean Code: Se evitan comentarios excesivos mediante el uso de nomenclatura clara, haciendo el código autoexplicativo.
-------------------------------------------------------------------------------------------------------------------------------------
-PRINCIPIOS DE DISEÑO
-------------------------------------------------------------------------------------------------------------------------------------
*SRP (Principio de Responsabilidad Única): Separación estricta de responsabilidades.
'Day1Main': Su única razón para cambiar es si se modifica la forma de entrada/salida (ej. leer de consola en vez de archivo).
'SolveDay1': Su única razón para cambiar es si varían las reglas matemáticas del movimiento (ej. si el dial cambia de tamaño 100 a 200).
*DRY (Don't Repeat Yourself): La lógica matemática de la rotación (sumar/restar y aplicar módulo) está encapsulada en 
    un único método privado ('applyRotation' en Parte A, 'moveOneClick' en Parte B). El bucle principal reutiliza esta lógica en cada iteración en lugar de duplicar la fórmula.
*KISS (Keep It Simple, Stupid): Se ha evitado la sobre-ingeniería.
    En lugar de crear una jerarquía de clases para 'Direction' (con polimorfismo para Izquierda/Derecha), se utiliza un simple 'switch expression' con tipos primitivos ('char', 'int'), lo cual es suficiente y más eficiente para este problema.
*YAGNI (You Aren't Gonna Need It): El código no implementa validaciones o estructuras para casos no solicitados 
    (como movimientos diagonales o diales multidimensionales), manteniéndose fiel a los requisitos exactos del enunciado.