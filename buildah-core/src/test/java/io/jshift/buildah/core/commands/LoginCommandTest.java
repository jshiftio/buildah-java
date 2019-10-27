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
public class LoginCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_login_command() {

        final BuildahLoginCommand buildahLoginCommand = new BuildahLoginCommand.Builder("docker.io", buildahExecutor).username("user").password("pass").build();

        List<String> cliCommand = buildahLoginCommand.getCliCommand();

        assertThat(cliCommand)
                .containsExactly(transform("login --tls-verify=false -u user -p pass docker.io"));

    }
}
