package info.thebloodbank.gameoflife;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.Set;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

class GridCellTest {
    @Test
    void findNeighbors() {
        final GridCell gridCell = new GridCell(100, 100);
        final Set<GridCell> neighbors = gridCell.findNeighbors();

        assertThat(neighbors).containsExactlyInAnyOrder(
                new GridCell(99, 99),
                new GridCell(100, 99),
                new GridCell(101, 99),

                new GridCell(99, 100),
                new GridCell(101, 100),

                new GridCell(99, 101),
                new GridCell(100, 101),
                new GridCell(101, 101)
        );
    }
    @Test
    void findNeighborsAcceptsNegativeCoordinates() {
        final GridCell gridCell = new GridCell(0, 0);
        final Set<GridCell> neighbors = gridCell.findNeighbors();

        assertThat(neighbors).containsExactlyInAnyOrder(
                new GridCell(-1, -1),
                new GridCell(0, -1),
                new GridCell(1, -1),

                new GridCell(-1, 0),
                new GridCell(1, 0),

                new GridCell(-1, 1),
                new GridCell(0, 1),
                new GridCell(1, 1)
        );
    }

    @Test
    void findNeighborsRotatesOverLimitsForLongDatatype1() {
        final GridCell gridCell = new GridCell(Long.MIN_VALUE, Long.MIN_VALUE);
        final Set<GridCell> neighbors = gridCell.findNeighbors();

        assertThat(neighbors).containsExactlyInAnyOrder(
                new GridCell(Long.MAX_VALUE, Long.MAX_VALUE),
                new GridCell(Long.MIN_VALUE, Long.MAX_VALUE),
                new GridCell(Long.MIN_VALUE + 1, Long.MAX_VALUE),

                new GridCell(Long.MAX_VALUE, Long.MIN_VALUE),
                new GridCell(Long.MIN_VALUE + 1, Long.MIN_VALUE),

                new GridCell(Long.MAX_VALUE, Long.MIN_VALUE + 1),
                new GridCell(Long.MIN_VALUE, Long.MIN_VALUE + 1),
                new GridCell(Long.MIN_VALUE + 1, Long.MIN_VALUE + 1)
        );
    }

    @Test
    void findNeighborsRotatesOverLimitsForLongDatatype2() {
        final GridCell gridCell = new GridCell(Long.MAX_VALUE, Long.MAX_VALUE);
        final Set<GridCell> neighbors = gridCell.findNeighbors();

        assertThat(neighbors).containsExactlyInAnyOrder(
                new GridCell(Long.MAX_VALUE - 1, Long.MAX_VALUE - 1),
                new GridCell(Long.MAX_VALUE, Long.MAX_VALUE - 1),
                new GridCell(Long.MIN_VALUE, Long.MAX_VALUE - 1),

                new GridCell(Long.MAX_VALUE - 1, Long.MAX_VALUE),
                new GridCell(Long.MIN_VALUE, Long.MAX_VALUE),

                new GridCell(Long.MAX_VALUE - 1, Long.MIN_VALUE),
                new GridCell(Long.MAX_VALUE, Long.MIN_VALUE),
                new GridCell(Long.MIN_VALUE, Long.MIN_VALUE)
        );
    }

    @Property
    void allCellsHaveEightNeighbors(@ForAll long x, @ForAll long y) {
        final GridCell gridCell = new GridCell(x, y);
        final Set<GridCell> neighbors = gridCell.findNeighbors();

        assertThat(neighbors).hasSize(8);
    }

}
