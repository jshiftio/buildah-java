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
public class VersionCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_version_command() {

        final BuildahVersionCommand buildahVersionCommand = new BuildahVersionCommand.Builder(buildahExecutor).build();
        List<String> cliCommand = buildahVersionCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("version"));
    }
}
