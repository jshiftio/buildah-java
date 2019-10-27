package io.jshift.buildah.core.commands;


import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static io.jshift.buildah.core.commands.CommandTransformer.transform;

@ExtendWith(MockitoExtension.class)
public class AddCommandTest {

    @Mock
    private CliExecutor buildahExecutor;

    @Test
    public void should_execute_add_command() {
        final BuildahAddCommand buildahAddCommand = new BuildahAddCommand.Builder(buildahExecutor, "working-container", "/src/test").destination("/dest/test").build();
        final List<String> cliCommand = buildahAddCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("add working-container /src/test /dest/test"));
    }
}
