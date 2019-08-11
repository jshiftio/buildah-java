package io.jshift.buildah.core.terminal;

public class ConsoleOutput {

    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private String currentColor;

    private ConsoleOutput(String currentColor) {
        this.currentColor = currentColor;
    }

    public static ConsoleOutput forStandardConsoleOutput() {
        return new ConsoleOutput(ANSI_RESET);
    }

    public static ConsoleOutput forErrorConsoleOutput() {
        return new ConsoleOutput(ANSI_RED);
    }

    public void print(String line) {
        // I want to sync over all Console outputs instances
        synchronized (ConsoleOutput.class) {
            System.out.println(this.currentColor + " " + line);
        }
    }

}
