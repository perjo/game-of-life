package info.thebloodbank.gameoflife;

import java.util.Set;

public interface GridChangeListener {

    void onGridChange(Set<GridCell> gridCells);
}
