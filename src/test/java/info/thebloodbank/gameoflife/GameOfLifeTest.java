package info.thebloodbank.gameoflife;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameOfLifeTest {

    @Test
    void testInitialization() {
        GameOfLife gameOfLife = new GameOfLife(ExampleSeed.PULSAR);

        assertThat(gameOfLife.getGameState().getLiving()).hasSize(48);
    }

}
