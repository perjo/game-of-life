package info.thebloodbank.gameoflife.gui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

/**
 * En generisk komponent för att plotta punkter i ett rutnät.
 */
final class GridVisualization extends JFrame {

    private final GridPanel gridPanel;

    GridVisualization(final String title, final GridVisualizationModel gridVisualizationModel) {
        gridVisualizationModel.addModelChangeListener(this::updateGrid);
        gridVisualizationModel.start();
        gridPanel = new GridPanel();
        setupGui(title);
    }

    private void setupGui(final String title) {
        setTitle(title);
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateGrid(Set<Point> points) {
        gridPanel.update(points);
    }

}
