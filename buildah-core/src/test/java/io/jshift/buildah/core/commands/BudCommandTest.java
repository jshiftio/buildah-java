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
public class BudCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_bud_command() {

        List<String> dockerfileList = new ArrayList<>();
        dockerfileList.add("Dockerfile");
        dockerfileList.add("Dockerfile.fed");
        final BuildahBudCommand buildahBudCommand = new BuildahBudCommand.Builder(buildahExecutor, "dockerFileContext").dockerfileList(dockerfileList).targetImage("targetImg").build();
        List<String> cliCommand = buildahBudCommand.getCliCommand();
        assertThat(cliCommand).containsExactly(transform("bud --tls-verify=false --file Dockerfile --file Dockerfile.fed -t targetImg dockerFileContext"));

    }
}
