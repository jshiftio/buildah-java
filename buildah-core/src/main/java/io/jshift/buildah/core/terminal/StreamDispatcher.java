package io.jshift.buildah.core.terminal;

import org.zeroturnaround.exec.stream.LogOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class StreamDispatcher extends LogOutputStream {

    private List<Consumer<String>> consumers;

    public StreamDispatcher(Consumer<String>... consumers) {
        this.consumers = new ArrayList<>();
        for (Consumer<String> consumer : consumers) {
            this.consumers.add(consumer);
        }
    }

    public void addConsumer(Consumer<String> consumer) {
        this.consumers.add(consumer);
    }

    @Override
    protected void processLine(String line) {
        for (Consumer<String> consumer : consumers) {
            consumer.accept(line);
        }
    }
}
