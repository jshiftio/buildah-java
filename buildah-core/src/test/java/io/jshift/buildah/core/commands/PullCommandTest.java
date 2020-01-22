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
public class PullCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_pull_command() {

        final BuildahPullCommand buildahPullCommand = new BuildahPullCommand.Builder("imageName", buildahExecutor).build();

        List<String> cliCommand = buildahPullCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("pull --tls-verify=false imageName"));
    }
}
