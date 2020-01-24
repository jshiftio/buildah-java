package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TagCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_tag_command() {

        List<String> tags = Arrays.asList("latest", "v1.0.0");
        final BuildahTagCommand buildahTagCommand = new BuildahTagCommand.Builder(buildahExecutor, "targetImg").tags(tags).build();
        List<String> cliCommand = buildahTagCommand.getCliCommand();
        assertThat(cliCommand).containsExactly(transform("tag targetImg latest v1.0.0"));

    }
}
