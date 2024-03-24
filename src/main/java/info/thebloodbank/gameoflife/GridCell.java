package info.thebloodbank.gameoflife;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Set;

public record GridCell(BigInteger x, BigInteger y) {

    public GridCell(int x, int y) {
        this(BigInteger.valueOf(x), BigInteger.valueOf(y));
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
}
