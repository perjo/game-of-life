# Game of Life 

Game of Life är en javaimplementation av 
[Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). 

Denna implementation använder ett oändligt rutnät av 2^64 * 2^64 celler där kanterna är sammankopplade. 
Detta innebär att

- punkten `(9223372036854775807, y)` är granne med `(-9223372036854775808, y)` 
- punkten `(x, 9223372036854775807)` granne med `(x, -9223372036854775808)`. 

(`-9223372036854775808 == Long.MIN_VALUE` och `9223372036854775807 == Long.MAX_VALUE`)

Förutom logik för Game of Life finns ett Swingbaserat gränssnitt som visar den del av rutnätet som ligger inom 
`(0,0)` till `(100000, 100000)`. 

Produktionskoden har endast beroende på [Guava](https://github.com/google/guava). För testerna används också 
 [JUnit 5](https://junit.org/junit5/), [AssertJ](https://assertj.github.io/doc/), 
[Mockito](https://site.mockito.org/) och [Jqwik](https://jqwik.net/).

Projektet kräver minst Java 14 (använder *records*).

## Användning 

Instruktionerna nedan förutsätter att du har Maven och Java installerade och tillgängliga på *PATH*. 

Stående i projektets rotkatalog, bygg projektet med

```
mvn clean package 
```

Kör sedan projektet med 

```
java -jar .\target\game-of-life-1.0-SNAPSHOT-jar-with-dependencies.jar
```

Projektet kommer med två sparade *seeds*, **glider** och **pulsar** (default). För att använda den förstnämnda, kör 

```
java -jar .\target\game-of-life-1.0-SNAPSHOT-jar-with-dependencies.jar glider
```

## Om lösningen 

- `GridCell.java` representerar en cell i det oändliga rutnätet. 

- `GameState.java` är ett ögonblick i spelet representerat som en mängd levande `GridCell`er. 
Denna klass implementerar de fyra grundreglerna i Game of Life. 

- `GameOfLife.java` är en representation av ett spel. Denna klass sätter 
upp ett initialt `GameState`.

- `ExampleSeed.java` är en enum hållande de seeds man kan starta projektet med. 

## Det grafiska gränsnittet 

- `GridVisualization.java` är en generisk komponent för att fylla i ett rutnät. 
- `GridPanel.java` används bara av `GridVisualization` och är panelen som rutnätet ritas på 
- `GridVisualizationModel.java` är en abstrakt klass som kan implementeras för att generera sekvenser av data att visas i ett rutnät. 
- `ModelChangeListener.java` är en implementation av en lyssnare på förändringar i `GridVisualizationModel`s (*observer pattern*). 

Dessa fyra klasser är helt oberoende av Game of Life och skulle kunna brytas ut till ett eget bibliotek. 

- `GameOfLifeVisualizationModel.java` är en implementation av `GridVisualizationModel` för att skapa Game of Life-data. Den innehåller en `GameOfLife`-instans och en timer för att be denna om regelbundna tillståndsövergångar. 
- `GameOfLifeApplication.java` är den körbara klassen. Dess `main`-metod väljer *seed* utifrån ett programargument, sätter upp en `GameOfLifeVisualizationModel` och skapar en `GridVisualization`-komponent som visas på skärmen.  





