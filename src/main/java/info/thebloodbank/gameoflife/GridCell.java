package info.thebloodbank.gameoflife;

import java.util.Set;

public record GridCell(long x, long y) {

    public Set<GridCell> findNeighbors() {

        final long left = x - 1;
        final long right = x + 1;
        final long below = y - 1;
        final long above = y + 1;

        return Set.of(
                new GridCell(left, below),
                new GridCell(left, y),
                new GridCell(left, above),

                new GridCell(x, below),
                new GridCell(x, above),

                new GridCell(right, below),
                new GridCell(right, y),
                new GridCell(right, above)
        );
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
}
