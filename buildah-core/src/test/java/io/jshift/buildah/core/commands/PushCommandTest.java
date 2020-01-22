package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PushCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_push_command() {

        final BuildahPushCommand buildahPushCommand = new BuildahPushCommand.Builder(buildahExecutor, "imageId").creds("user:pass").registry("registryName").build();

        List<String> cliCommand = buildahPushCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("push --tls-verify=false --creds user:pass imageId registryName"));
    }
}
