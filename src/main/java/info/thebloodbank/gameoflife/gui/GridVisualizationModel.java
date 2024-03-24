package info.thebloodbank.gameoflife.gui;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;
 abstract class GridVisualizationModel {

    private final Set<ModelChangeListener> modelChangeListeners = new HashSet<>();

    void fireModelChanged(final Set<Point> points) {
        modelChangeListeners.forEach(modelChangeListener -> modelChangeListener.modelChanged(points));
    }

    void addModelChangeListener(final ModelChangeListener modelChangeListener) {
        modelChangeListeners.add(modelChangeListener);
    }

    abstract void start();
}
