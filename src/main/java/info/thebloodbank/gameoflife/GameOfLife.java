package info.thebloodbank.gameoflife;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GameOfLife {

    private GameState gameState;

    public GameOfLife(final ExampleSeed exampleSeed) {
        final Set<GridCell> seed = readSeed(exampleSeed);
        this.gameState = GameState.create(seed);
    }

    public Set<GridCell> nextState() {
        gameState = gameState.next();
        return gameState.getLiving();
    }

    private Set<GridCell> readSeed(final ExampleSeed exampleSeed) {
        try {
            final List<String> lines = Resources.readLines(Resources.getResource(this.getClass(),
                    "/seeds/" + exampleSeed.getFileName() + ".csv"), StandardCharsets.UTF_8);
            return lines.stream().map(this::lineToGridCell).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private GridCell lineToGridCell(final String line) {
        final String[] coords = line.split(",");
        Preconditions.checkArgument(
                coords.length == 2,
                "Invalid seed file! " +
                        "It should contain two coordinates on each line, separated by a comma sign.");

        return new GridCell(Long.parseLong(coords[0].trim()), Long.parseLong(coords[1].trim()));
    }

    @VisibleForTesting
    GameState getGameState() {
        return gameState;
    }
}
