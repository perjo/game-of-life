package info.thebloodbank.gameoflife.gui;

import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import info.thebloodbank.gameoflife.GameState;
import info.thebloodbank.gameoflife.GridCell;
import java.awt.BorderLayout;
import java.awt.Point;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public final class GameOfLifeVisualization extends JFrame {

    public static final long VISUALISATION_DIMENSION = 100000L;
    public static final int DELAY_IN_MS = 500;
    private final GridPanel gridPanel;
    private GameState gameState;

    private GameOfLifeVisualization(final ExampleSeed exampleSeed) {

        setTitle("Game of Life");
        gridPanel = new GridPanel();

        JScrollPane scrollPane = new JScrollPane(gridPanel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Set<GridCell> seed = readSeed(exampleSeed);
        gameState = GameState.create(seed);

        repaint();
        Timer timer = new Timer(DELAY_IN_MS, e -> {
            gameState = gameState.next();
            updateGrid(gameState.getLiving());
        });
        timer.start();

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
                "Invalid seed file! It should contains to coordinates on each line, separated by a comma sign.");

        return new GridCell(Long.parseLong(coords[0].trim()), Long.parseLong(coords[1].trim()));
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
        return coordinate <= VISUALISATION_DIMENSION;
    }

    public static void main(String[] args) {
        final ExampleSeed exampleSeed = args.length == 0 ? ExampleSeed.PULSAR : ExampleSeed.fromString(args[0]);
        javax.swing.SwingUtilities.invokeLater(() -> showVisualization(exampleSeed));
    }

    private static void showVisualization(final ExampleSeed exampleSeed) {
        new GameOfLifeVisualization(exampleSeed);
    }

    private enum ExampleSeed {
        GLIDER("glider"),
        PULSAR("pulsar");

        private final String fileName;

        ExampleSeed(final String fileName) {
            this.fileName = fileName;
        }

        public static ExampleSeed fromString(final String s) {
            return Stream.of(values())
                    .filter(exampleSeed -> exampleSeed.fileName.equals(s))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No matching example seed for name " + s));
        }

        public String getFileName() {
            return fileName;
        }
    }


}
