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
public class LogoutCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_exectute_logout_command() {
        final BuildahLogoutCommand buildahLogoutCommand = new BuildahLogoutCommand.Builder(buildahExecutor).registryName("docker.io").build();
        List<String> cliCommand = buildahLogoutCommand.getCliCommand();

        assertThat(cliCommand)
                .containsExactly(transform("logout docker.io"));
    }
}
