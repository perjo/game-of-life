package info.thebloodbank.gameoflife.gui;

import info.thebloodbank.gameoflife.GameState;
import info.thebloodbank.gameoflife.GridCell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.math.BigInteger;
import java.util.Set;
import java.util.stream.Collectors;

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

        // TODO: move seed to file
        gameState = GameState.create(Set.of(
                new GridCell(10, 10),
                new GridCell(11, 10),
                new GridCell(12, 10),

                new GridCell(16, 10),
                new GridCell(17, 10),
                new GridCell(18, 10),

                new GridCell(8, 12),
                new GridCell(13, 12),

                new GridCell(15, 12),
                new GridCell(20, 12),

                new GridCell(8, 13),
                new GridCell(13, 13),

                new GridCell(15, 13),
                new GridCell(20, 13),

                new GridCell(8, 14),
                new GridCell(13, 14),

                new GridCell(15, 14),
                new GridCell(20, 14),

                new GridCell(10, 15),
                new GridCell(11, 15),
                new GridCell(12, 15),

                new GridCell(16, 15),
                new GridCell(17, 15),
                new GridCell(18, 15),

                new GridCell(10, 17),
                new GridCell(11, 17),
                new GridCell(12, 17),

                new GridCell(16, 17),
                new GridCell(17, 17),
                new GridCell(18, 17),

                new GridCell(8, 18),
                new GridCell(13, 18),

                new GridCell(15, 18),
                new GridCell(20, 18),

                new GridCell(8, 19),
                new GridCell(13, 19),

                new GridCell(15, 19),
                new GridCell(20, 19),

                new GridCell(8, 20),
                new GridCell(13, 20),

                new GridCell(15, 20),
                new GridCell(20, 20),

                new GridCell(10, 22),
                new GridCell(11, 22),
                new GridCell(12, 22),

                new GridCell(16, 22),
                new GridCell(17, 22),
                new GridCell(18, 22)

        ));

        repaint();
        Timer timer = new Timer(2000, e -> {
            gameState = gameState.next();
            updateGrid(gameState.getLiving());
        });

        timer.start();

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
