package info.thebloodbank.gameoflife.gui;

import static info.thebloodbank.gameoflife.gui.GameOfLifeVisualizationModel.VISUALIZATION_DIMENSION;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;
import javax.swing.JPanel;

class GridPanel extends JPanel {

    private Set<Point> highlightedCells = Set.of();

    GridPanel() {
        final int dimensionInPixels = VISUALIZATION_DIMENSION * 5;
        setPreferredSize(new Dimension(dimensionInPixels, dimensionInPixels));
    }

    void update(final Set<Point> highlightedCells) {
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
