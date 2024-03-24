package info.thebloodbank.gameoflife.gui;

import com.google.common.annotations.VisibleForTesting;
import info.thebloodbank.gameoflife.ExampleSeed;
import info.thebloodbank.gameoflife.GameOfLife;
import info.thebloodbank.gameoflife.GridCell;
import java.awt.Point;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.Timer;
 class GameOfLifeVisualizationModel extends GridVisualizationModel {

    public static final int VISUALIZATION_DIMENSION = 100000;

    public static final int DELAY_IN_MS = 500;

    private final GameOfLife game;

    GameOfLifeVisualizationModel(ExampleSeed exampleSeed) {
        game = new GameOfLife(exampleSeed);
    }

    GameOfLifeVisualizationModel(Set<GridCell> seed) {
        game = new GameOfLife(seed);
    }

    void start() {
        Timer timer = new Timer(DELAY_IN_MS, e -> doTransition());
        timer.start();
    }

    @VisibleForTesting
    void doTransition() {
         final Set<GridCell> gridCells = game.nextState();
         Set<Point> highlighted = gridCells.stream()
                 .filter(GameOfLifeVisualizationModel::visibleInGui)
                 .map(GameOfLifeVisualizationModel::toAwtPoint)
                 .collect(Collectors.toSet());
         fireModelChanged(highlighted);
     }

     private static boolean visibleInGui(GridCell gridCell) {
        return coordinateWithinBounds(gridCell.getX())
                && coordinateWithinBounds(gridCell.getY());
    }

    private static boolean coordinateWithinBounds(long coordinate) {
        return coordinate <= VISUALIZATION_DIMENSION;
    }

    private static Point toAwtPoint(GridCell gridCell) {
        return new Point((int) gridCell.getX(), (int) gridCell.getY());
    }

}
