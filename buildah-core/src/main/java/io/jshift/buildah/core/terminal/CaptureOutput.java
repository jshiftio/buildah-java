package io.jshift.buildah.core.terminal;

import java.util.ArrayList;
import java.util.List;

public class CaptureOutput {

    private List<String> output = new ArrayList<>();

    public void capture(String line) {
        this.output.add(line);
    }

    public List<String> getOutput() {
        return output;
    }
}
