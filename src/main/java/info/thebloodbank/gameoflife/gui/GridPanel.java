package info.thebloodbank.gameoflife.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class GridPanel extends JPanel {

    private Set<Point> highlightedCells = Set.of();

    public GridPanel() {
        final int dimensionInPixels = GameOfLifeVisualization.VISUALIZATION_DIMENSION * 5;
        setPreferredSize(new Dimension(dimensionInPixels, dimensionInPixels));
    }

    public void update(final Set<Point> highlightedCells) {
        this.highlightedCells = highlightedCells;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic2d = (Graphics2D) g;
        graphic2d.setColor(Color.BLACK);

        highlightedCells.forEach(cell -> highlightCell(graphic2d, cell));
    }

    private void highlightCell(Graphics2D graphic2d, Point point) {
        graphic2d.fill(new Rectangle((int) (point.getX() * 5), (int) (point.getY() * 5), 4, 4));
    }

}
