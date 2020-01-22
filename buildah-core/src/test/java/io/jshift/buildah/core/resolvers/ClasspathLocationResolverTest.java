package io.jshift.buildah.core.resolvers;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ClasspathLocationResolverTest {

    @Test
    public void should_load_resource_from_classpath() throws IOException {

        // Given

        final ClasspathLocationResolver classpathLocationResolver = new ClasspathLocationResolver("hellobuildah.txt", "hellorunc.txt");

        // When

        final InputStream helloBuildahResource = classpathLocationResolver.loadBuildahResource();
        final InputStream helloRuncResource = classpathLocationResolver.loadRuncResource();

        // Then

       assertThat(helloBuildahResource).isNotNull();
       assertThat(helloRuncResource).isNotNull();

       final String buildahResult = readContent(helloBuildahResource);
       final String runcResult = readContent(helloRuncResource);

       assertThat(buildahResult).isEqualTo("Hello Buildah");
       assertThat(runcResult).isEqualTo("Hello Runc");

    }

    private String readContent(InputStream helloResource) throws IOException {
        final String result = new BufferedReader(new InputStreamReader(helloResource))
                .lines().collect(Collectors.joining("\n"));
        helloResource.close();
        return result;
    }
}
