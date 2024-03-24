package info.thebloodbank.gameoflife;

import java.util.stream.Stream;

public enum ExampleSeed {
    // See https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_animated_glider.gif
    GLIDER("glider"),

    // https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life#/media/File:Game_of_life_pulsar.gif
    PULSAR("pulsar");

    private final String fileName;

    ExampleSeed(final String fileName) {
        this.fileName = fileName;
    }

    public static ExampleSeed fromString(final String s) {
        return Stream.of(values())
                .filter(exampleSeed -> exampleSeed.fileName.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No matching example seed for name " + s));
    }

    String getFileName() {
        return fileName;
    }
}
