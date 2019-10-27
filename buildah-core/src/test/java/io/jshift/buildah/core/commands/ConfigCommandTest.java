package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ConfigCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_config_command() {

        List<String> ports = new ArrayList<>();
        ports.add("8008");
        ports.add("8090");
        final BuildahConfigCommand buildahConfigCommand = new BuildahConfigCommand.Builder(buildahExecutor, "containerName").workingDir("workDir").port(ports).build();
        List<String> cliCommand = buildahConfigCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("config --workingdir workDir --port 8008 --port 8090 containerName"));
    }
}
