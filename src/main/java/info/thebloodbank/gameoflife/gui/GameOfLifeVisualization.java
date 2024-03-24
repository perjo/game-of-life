package info.thebloodbank.gameoflife.gui;

import info.thebloodbank.gameoflife.ExampleSeed;
import info.thebloodbank.gameoflife.GameOfLife;
import info.thebloodbank.gameoflife.GridCell;
import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public final class GameOfLifeVisualization extends JFrame {

    public static final int VISUALIZATION_DIMENSION = 100000;
    public static final int DELAY_IN_MS = 500;
    private final GridPanel gridPanel;
    private final GameOfLife game;

    private GameOfLifeVisualization(final ExampleSeed exampleSeed) {

        setTitle("Game of Life");
        gridPanel = new GridPanel();

        setupGui();

        game = new GameOfLife(exampleSeed);

        Timer timer = new Timer(DELAY_IN_MS, e -> {
            updateGrid(game.nextState());
        });
        timer.start();
    }

    private void setupGui() {
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateGrid(Set<GridCell> gridCells) {
        Set<Point> highlighted = gridCells.stream()
                .filter(GameOfLifeVisualization::visibleInGui)
                .map(GameOfLifeVisualization::toPoint)
                .collect(Collectors.toSet());
        gridPanel.update(highlighted);
    }

    private static Point toPoint(GridCell gridCell) {
        return new Point((int) gridCell.getX(), (int) gridCell.getY());
    }

    private static boolean visibleInGui(GridCell gridCell) {
        return coordinateInVisualization(gridCell.getX())
                && coordinateInVisualization(gridCell.getY());

    }

    private static boolean coordinateInVisualization(long coordinate) {
        return coordinate <= VISUALIZATION_DIMENSION;
    }

    public static void main(String[] args) {
        final ExampleSeed exampleSeed = args.length == 0 ? ExampleSeed.PULSAR : ExampleSeed.fromString(args[0]);
        SwingUtilities.invokeLater(() -> showVisualization(exampleSeed));
    }

    private static void showVisualization(final ExampleSeed exampleSeed) {
        new GameOfLifeVisualization(exampleSeed);
    }
}
