package info.thebloodbank.gameoflife.gui;

import info.thebloodbank.gameoflife.ExampleSeed;
import javax.swing.SwingUtilities;

public class GameOfLifeApplication {

    public static void main(String[] args) {
        final ExampleSeed exampleSeed = args.length == 0 ? ExampleSeed.PULSAR : ExampleSeed.fromString(args[0]);
        final GameOfLifeVisualizationModel visualizationModel = new GameOfLifeVisualizationModel(exampleSeed);
        SwingUtilities.invokeLater(() -> new GridVisualization("Game of Life", visualizationModel));
    }
}
