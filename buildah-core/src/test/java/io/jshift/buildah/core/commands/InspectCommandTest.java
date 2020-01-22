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
public class InspectCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_inspect_command() {

        final BuildahInspectCommand buildahInspectCommand = new BuildahInspectCommand.Builder(buildahExecutor).type("image").image("imageId").build();

        List<String> cliCommand = buildahInspectCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("inspect --type image imageId"));
    }
}
