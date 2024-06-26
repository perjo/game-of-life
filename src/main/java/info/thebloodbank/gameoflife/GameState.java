package info.thebloodbank.gameoflife;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

class GameState {
    private final Set<GridCell> living;

    private GameState(Set<GridCell> living) {
        this.living = living;
    }

    public static GameState create(Set<GridCell> seed) {
        return new GameState(seed);
    }

    GameState next() {
        Set<GridCell> surviving = living.stream()
                .filter(this::willSurvive)
                .collect(Collectors.toSet());

        Set<GridCell> reproduced = living.stream()
                .flatMap(gridCell -> gridCell.findNeighbors().stream())
                .distinct()
                .filter(this::willBeReproduced)
                .collect(Collectors.toSet());

        return new GameState(Sets.union(surviving, reproduced));
    }

    private boolean willBeReproduced(GridCell cell) {
        int numberOfLivingNeighbors = numberOfLivingNeighbors(cell);
        return numberOfLivingNeighbors == 3;
    }

    private boolean willSurvive(GridCell cell) {
        int numberOfLivingNeighbors = numberOfLivingNeighbors(cell);
        return numberOfLivingNeighbors >= 2 && numberOfLivingNeighbors <= 3;
    }

    private int numberOfLivingNeighbors(GridCell cell) {
        Set<GridCell> neighbors = cell.findNeighbors();
        return Sets.intersection(living, neighbors).size();
    }

    Set<GridCell> getLiving() {
        return Collections.unmodifiableSet(living);
    }

}
