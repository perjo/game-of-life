package info.thebloodbank.gameoflife.gui;

import static info.thebloodbank.gameoflife.gui.GameOfLifeVisualizationModel.VISUALIZATION_DIMENSION;
import static org.mockito.Mockito.verify;

import info.thebloodbank.gameoflife.GridCell;
import java.awt.Point;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisualizationModelTest {

    @Mock
    private ModelChangeListener mockListener;

    @Test
    void onlyPointsWithinGuiDimensionsAreReturned() {
        final GameOfLifeVisualizationModel visualizationModel = new GameOfLifeVisualizationModel(
                Set.of(
                        new GridCell(VISUALIZATION_DIMENSION, 10),
                        new GridCell(VISUALIZATION_DIMENSION, 11),
                        new GridCell(VISUALIZATION_DIMENSION, 12)
                )
        );
        visualizationModel.addModelChangeListener(mockListener);
        visualizationModel.doTransition();

        verify(mockListener).modelChanged(Set.of(
                new Point(VISUALIZATION_DIMENSION, 11),
                new Point(VISUALIZATION_DIMENSION - 1, 11)
        ));
    }

}
