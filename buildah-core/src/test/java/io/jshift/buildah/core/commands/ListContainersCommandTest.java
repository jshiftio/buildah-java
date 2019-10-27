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
public class ListContainersCommandTest {

    @Mock
    private CliExecutor buildahExecutor;

    @Test
    public void should_execute_list_containers_command() {

        final BuildahListContainersCommand buildahListContainersCommand = new BuildahListContainersCommand.Builder(buildahExecutor).jsonFormat(true).onlyId(true).build();

        List<String> cliCommand = buildahListContainersCommand.getCliCommand();

        assertThat(cliCommand)
                .containsExactly(transform("containers --json --quiet"));

    }
}
