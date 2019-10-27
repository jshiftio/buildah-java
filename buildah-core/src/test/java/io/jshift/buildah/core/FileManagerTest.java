package io.jshift.buildah.core;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class FileManagerTest {

    @Test
    public void should_copy_file_from_inputstream_to_temp_file() throws IOException {

        // Given
        final InputStream resourceAsStream = new ByteArrayInputStream("Hello Buildah".getBytes());
        final FileManager fileManager = new FileManager("hello.txt", resourceAsStream);

        // When
        final Path copyToTemp = fileManager.copyToTemp();

        // Then
        assertThat(copyToTemp)
                .exists()
                .hasContent("Hello Buildah");
    }
}
