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
public class CopyCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_copy_command() {

        final BuildahCopyCommand buildahCopyCommand = new BuildahCopyCommand.Builder(buildahExecutor, "containerId", "/src/test").destination("/dest/test").build();
        List<String> cliCommand = buildahCopyCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("copy containerId /src/test /dest/test"));
    }
}
