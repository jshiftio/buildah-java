package io.jshift.buildah.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileManager {

    private InputStream content;

    private String name;

    FileManager(String name, InputStream content) {
        this.content = content;
        this.name = name;
    }
    Path copyToLocation(Path location) throws IOException {
        final Path output = location.resolve(this.name);
        try(final InputStream stream = this.content) {
            Files.copy(stream, output, StandardCopyOption.REPLACE_EXISTING);
        }
        return output;
    }

    Path copyToTemp() throws IOException {
        final Path buildahTempDirectory = Files.createTempDirectory("buildah");
        return copyToLocation(buildahTempDirectory);
    }



}
