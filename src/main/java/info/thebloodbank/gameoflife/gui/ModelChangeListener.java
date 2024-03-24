package info.thebloodbank.gameoflife.gui;

import java.awt.Point;
import java.util.Set;

interface ModelChangeListener {
    void modelChanged(Set<Point> gridCells);
}
