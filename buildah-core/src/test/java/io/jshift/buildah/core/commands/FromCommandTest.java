package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class FromCommandTest {

    @Mock
    private CliExecutor buildahExecutor;

    @Test
    public void should_execute_from_command() {
        final BuildahFromCommand buildahFromCommand = new BuildahFromCommand.Builder("scratch", buildahExecutor).build();
        final List<String> cliCommand = buildahFromCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("from scratch"));
    }
}
