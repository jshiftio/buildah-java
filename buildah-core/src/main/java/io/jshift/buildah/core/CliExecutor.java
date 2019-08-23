package io.jshift.buildah.core;

import io.jshift.buildah.api.Command;

import java.nio.file.Path;
import java.util.List;

public interface CliExecutor {
    List<String> execute(Command command);

    List<String> execute(Path buildahDirectory, Path runcDirctory, Command command);
}
