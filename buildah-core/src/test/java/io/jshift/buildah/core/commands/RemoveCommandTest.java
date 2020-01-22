package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class RemoveCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_remove_command() {

        final BuildahRemoveCommand buildahRemoveCommand = new BuildahRemoveCommand.Builder(buildahExecutor).all(true).build();

        List<String> cliCommand = buildahRemoveCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("rm --all"));
    }

    @Test
    public void should_execute_remove_image_command() {

        final BuildahRemoveImageCommand buildahRemoveImageCommand = new BuildahRemoveImageCommand.Builder(buildahExecutor).all(true).build();

        List<String> cliCommand = buildahRemoveImageCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("rmi --all"));
    }
}
