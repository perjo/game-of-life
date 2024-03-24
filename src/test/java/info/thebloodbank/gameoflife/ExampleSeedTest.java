package info.thebloodbank.gameoflife;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class ExampleSeedTest {

    @Test
    void fromStringReturnsMatchingElement() {
        final ExampleSeed seed = ExampleSeed.fromString("pulsar");

        assertThat(seed).isSameAs(ExampleSeed.PULSAR);
    }

    @Test
    void fromStringThrowsExceptionIfNoMatchingElement() {
        assertThatIllegalArgumentException().isThrownBy(() -> ExampleSeed.fromString("strange"));
    }
}
