package info.thebloodbank.gameoflife.gui;

import com.google.common.base.Preconditions;
import info.thebloodbank.gameoflife.GameState;
import info.thebloodbank.gameoflife.GridCell;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class GameOfLifeVisualization extends JFrame {

    public static final long VISUALISATION_DIMENSION = 10000L;
    private final GridPanel gridPanel;
    private GameState gameState;

    GameOfLifeVisualization() {
        setTitle("Game of Life");
        gridPanel = new GridPanel();

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(new LineBorder(Color.BLACK, 2));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(1200, 1200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final Set<GridCell> seed = readSeed();

        gameState = GameState.create(seed);

        repaint();
        Timer timer = new Timer(500, e -> {
            gameState = gameState.next();
            updateGrid(gameState.getLiving());
        });

        timer.start();

    }

    private Set<GridCell> readSeed() {
        try (Stream<String> lines = Files.lines(Paths.get(getClass().getResource("/seeds/glider.csv").toURI()))) {
            return lines.map(this::lineToGridCell).collect(Collectors.toSet());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private GridCell lineToGridCell(final String line) {
        final String[] coords = line.split(",");
        Preconditions.checkArgument(
                coords.length == 2,
                "Invalid seed file! It should contains to coordinates on each line, separated by a comma sign.");

        return new GridCell(new BigInteger(coords[0].trim()), new BigInteger(coords[1].trim()));
    }

    private void updateGrid(Set<GridCell> gridCells) {
        Set<Point> highlighted = gridCells.stream()
                .filter(GameOfLifeVisualization::visibleInGui)
                .map(GameOfLifeVisualization::toPoint)
                .collect(Collectors.toSet());
        gridPanel.update(highlighted);
    }

    private static Point toPoint(GridCell gridCell) {
        return new Point(gridCell.getX().intValue(), gridCell.getY().intValue());
    }

    private static boolean visibleInGui(GridCell gridCell) {
        return coordinateInVisualization(gridCell.getX())
                && coordinateInVisualization(gridCell.getY());

    }

    private static boolean coordinateInVisualization(BigInteger coordinate) {
        return coordinate.compareTo(BigInteger.valueOf(VISUALISATION_DIMENSION)) < 0;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(GameOfLifeVisualization::showVisualization);
    }

    private static void showVisualization() {
        new GameOfLifeVisualization();
    }


}
