# Pregunta 3 : Buddy System
Este programa ejecuta una implementación de Buddy System basada en listas mutables de Kotlin que alojan los valores de los bloques vacíos y los tomados por algún identificador. La clase que controla el Buddy System se encuentra en el archivo BuddySystem.kt, el programa puede ser llamado al correro el archivo main.kt y en sampleTests.kt se encuentran las pruebas que se realizaron en el unit test.
Además se encuentra en el archivo BuddySystemProject.zip el proyecto de Gradle completo que se utilizo para correr las pruebas y la cobertura del código.

## ¿Cómo correr el programa?
El programa puede correrse de manera muy sencilla mediante los comandos:

    kotlinc BuddySystem.kt main.kt -include-runtime -d main.jar
    java -jar main.jar
    
Se debe tener instalado Kotlin en el computador, en su última versión.

## Unit Test
Durante el unit test se dieron los siguientes resultados: 

<img src="/docs/logo.png" alt="My cool logo"/>

Por lo que podemos ver que se pasaron todos los tests que se encuentran en el archivo sampleTests.kt

## Code Coverage

Una vez realizado el unit test se verificó la cobertura del código con ayuda de Kover, un plugin de Gradle para Kotlin que permite realizar tests y conseguir el code coverage de código tanto para Java como para Kotlin.
