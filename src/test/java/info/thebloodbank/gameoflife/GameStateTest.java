package info.thebloodbank.gameoflife;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameStateTest {

    public static Stream<Arguments> moreThanThreeNeighborsSetup() {
        return Stream.of(
                // Four neighbors
                Arguments.of(
                        new GridCell(10, 10),
                        Set.of(
                                new GridCell(9, 9),
                                new GridCell(9, 11),
                                new GridCell(11, 9),
                                new GridCell(11, 11)
                        )),
                // Five neighbors
                Arguments.of(
                        new GridCell(10, 10),
                        Set.of(
                                new GridCell(9, 9),
                                new GridCell(9, 10),
                                new GridCell(9, 11),
                                new GridCell(11, 9),
                                new GridCell(11, 11)
                        )),
                // Six neighbors
                Arguments.of(
                        new GridCell(10, 10),
                        Set.of(
                                new GridCell(9, 9),
                                new GridCell(9, 10),
                                new GridCell(9, 11),
                                new GridCell(10, 11),
                                new GridCell(11, 9),
                                new GridCell(11, 11)
                        )),
                // Seven neighbors
                Arguments.of(
                        new GridCell(10, 10),
                        Set.of(
                                new GridCell(9, 9),
                                new GridCell(9, 10),
                                new GridCell(9, 11),
                                new GridCell(10, 11),
                                new GridCell(11, 9),
                                new GridCell(11, 10),
                                new GridCell(11, 11)
                        )),
                // Eight neighbors
                Arguments.of(
                        new GridCell(10, 10),
                        Set.of(
                                new GridCell(9, 9),
                                new GridCell(9, 10),
                                new GridCell(9, 11),
                                new GridCell(10, 9),
                                new GridCell(10, 11),
                                new GridCell(11, 9),
                                new GridCell(11, 10),
                                new GridCell(11, 11)
                        ))
        );
    }

    @Test
    void cellWithoutNeighborsDies() {
        final GameState initialState = GameState.create(Set.of(new GridCell(10, 10)));
        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).isEmpty();
    }

    @Test
    void cellWithJustOneNeighborDies() {
        final GameState initialState = GameState.create(Set.of(
                new GridCell(10, 10),
                new GridCell(10, 11)
        ));
        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).isEmpty();
    }

    @Test
    void cellWithTwoNeighborsSurvives() {
        GridCell surviving = new GridCell(10, 10);
        final GameState initialState = GameState.create(Set.of(
                new GridCell(9, 9),
                surviving,
                new GridCell(11, 11)
        ));
        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).containsExactly(surviving);
    }

    @Test
    void cellWithThreeNeighborsSurvives() {
        GridCell surviving = new GridCell(10, 10);
        final GameState initialState = GameState.create(Set.of(
                new GridCell(9, 9),
                surviving,
                new GridCell(11, 11),
                new GridCell(11, 9)
        ));
        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).contains(surviving);
    }

    @ParameterizedTest
    @MethodSource("moreThanThreeNeighborsSetup")
    void cellWithMoreThanFourNeighborsDies(final GridCell cellExpectedToDie, final Set<GridCell> neighbors) {
        final GameState initialState = GameState.create(Sets.union(Set.of(cellExpectedToDie), neighbors));

        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).doesNotContain(cellExpectedToDie);
    }

    @Test
    void deadCellWithExactlyThreeNeighborsBecomesLive() {
        final GameState initialState = GameState.create(Set.of(
                new GridCell(9, 9),
                new GridCell(11, 11),
                new GridCell(11, 9)
        ));

        GameState nextState = initialState.next();

        assertThat(nextState.getLiving()).contains(new GridCell(10, 10));
    }

}
