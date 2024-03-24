package info.thebloodbank.gameoflife;

import java.util.stream.Stream;

public enum ExampleSeed {
    GLIDER("glider"),
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
