# Game of Life 

Game of Life är en javaimplementation av 
[Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). 

Den representerar ett oändligt rutnät av 2^64 * 2^64 celler där kanterna är sammankopplade. Detta innebär att

- punkten `(9223372036854775807, y)` är granne med `(-9223372036854775808, y)` 
- punkten `(x, 9223372036854775807)` granne med `(x, -9223372036854775808)`. 

(-9223372036854775808 = `Long.MIN_VALUE` och 9223372036854775807 = `Long.MAX_VALUE`)

Gränssnittet visar den del av rutnätet som ligger inom `(0,0)` till `(100000, 100000)`;

Projektet har endast beroenden på [Guava](https://github.com/google/guava) 
(för att få tillgång till praktiska mängdoperationer) samt på [JUnit 5](https://junit.org/junit5/), 
[AssertJ](https://assertj.github.io/doc/) och [Jqwik](https://jqwik.net/) för tester.

Projektet kräver minst Java 14 (använder *records*).

## Användning 

Instruktionerna nedan förutsätter att du har Maven och Java installerade och tillgängliga på PATH. 

Stående i projektets rotkatalog, bygg projektet med

```
mvn clean package 
```

Kör sedan projektet med 

```
java -jar .\target\game-of-life-1.0-SNAPSHOT-jar-with-dependencies.jar
```

