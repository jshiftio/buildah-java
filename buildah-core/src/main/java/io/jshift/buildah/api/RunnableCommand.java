package io.jshift.buildah.api;

import java.nio.file.Path;

public interface RunnableCommand<RETURN_TYPE> {

    RETURN_TYPE execute(Path directory);
    RETURN_TYPE execute();

}
