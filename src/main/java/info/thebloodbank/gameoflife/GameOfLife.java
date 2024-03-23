package info.thebloodbank.gameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameOfLife {

    private GameState gameState;
    private final List<GridChangeListener> gridChangeListeners = new ArrayList<>();


    public GameOfLife(Set<GridCell> seed) {
        this.gameState = GameState.create(seed);
        fireGridChangeListeners();
    }

    public static GameOfLife create(Set<GridCell> seed) {
        return new GameOfLife(seed);
    }

    public void addGridChangeListener(final GridChangeListener listener) {
        gridChangeListeners.add(listener);
    }

    private void fireGridChangeListeners() {
        gridChangeListeners.forEach(gridChangeListener -> gridChangeListener.onGridChange(gameState.getLiving()));
    }


}
