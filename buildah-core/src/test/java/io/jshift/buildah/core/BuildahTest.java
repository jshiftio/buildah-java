package io.jshift.buildah.core;

import io.jshift.buildah.core.commands.BuildahImagesCommand;
import io.jshift.buildah.core.commands.BuildahListContainersCommand;
import io.jshift.buildah.core.commands.BuildahVersionCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BuildahTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_display_version() {

        final Buildah buildah = new Buildah(buildahExecutor);
        final BuildahVersionCommand buildahVersionCommand = buildah.version().build();

        buildahVersionCommand.execute();

        verify(buildahExecutor).execute(buildahVersionCommand);
    }


    @Test
    public void should_list_containers() {

        final Buildah buildah = new Buildah(buildahExecutor);
        final BuildahListContainersCommand buildahListContainersCommand = buildah.listContainers().build();

        buildahListContainersCommand.execute();

        verify(buildahExecutor).execute(buildahListContainersCommand);
    }

    @Test
    public void should_list_images() {

        final Buildah buildah = new Buildah(buildahExecutor);
        final BuildahImagesCommand buildahImagesCommand = buildah.listImages().build();

        buildahImagesCommand.execute();

        verify(buildahExecutor).execute(buildahImagesCommand);
    }


}
