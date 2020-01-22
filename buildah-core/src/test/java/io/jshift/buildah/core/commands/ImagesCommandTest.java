package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;

public class ImagesCommandTest {

    @Mock
    private CliExecutor buildahExecutor;

    @Test
    public void should_execute_list_containers_command() {

        final BuildahImagesCommand buildahImagesCommand = new BuildahImagesCommand.Builder(buildahExecutor).jsonFormat(true).onlyId(true).build();

        List<String> cliCommand = buildahImagesCommand.getCliCommand();

        assertThat(cliCommand)
                .containsExactly(transform("images --json --quiet"));

    }
}
