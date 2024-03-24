package info.thebloodbank.gameoflife;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;


// TODO: make record 
public class GridCell {
    private final BigInteger x;
    private final BigInteger y;

    public GridCell(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    public GridCell(int x, int y) {
        this.x = BigInteger.valueOf(x);
        this.y = BigInteger.valueOf(y);
    }

    public Set<GridCell> getNeighbors() {
        return Set.of(
                // TODO: check BigInteger boundaries
                new GridCell(x.subtract(BigInteger.ONE), y.subtract(BigInteger.ONE)),
                new GridCell(x.subtract(BigInteger.ONE), y),
                new GridCell(x.subtract(BigInteger.ONE), y.add(BigInteger.ONE)),

                new GridCell(x, y.subtract(BigInteger.ONE)),
                new GridCell(x, y.add(BigInteger.ONE)),

                new GridCell(x.add(BigInteger.ONE), y.subtract(BigInteger.ONE)),
                new GridCell(x.add(BigInteger.ONE), y),
                new GridCell(x.add(BigInteger.ONE), y.add(BigInteger.ONE))
        );
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GridCell gridCell = (GridCell) object;
        return Objects.equals(x, gridCell.x) && Objects.equals(y, gridCell.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "GridCell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
