package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.jshift.buildah.core.commands.CommandTransformer.transform;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BudCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_bud_command() {

        List<String> dockerfileList = Arrays.asList("Dockerfile", "Dockerfile.fed");
        final BuildahBudCommand buildahBudCommand = new BuildahBudCommand.Builder(buildahExecutor, "dockerFileContext").dockerfileList(dockerfileList).targetImage("targetImg").build();
        List<String> cliCommand = buildahBudCommand.getCliCommand();
        assertThat(cliCommand).containsExactly(transform("bud --tls-verify=false --file Dockerfile --file Dockerfile.fed -t targetImg dockerFileContext"));

    }

    @Test
    public void should_add_build_arg() {

        Map<String, String> buildArgs = Collections.singletonMap("TEST", "TEST_VAL");
        List<String> dockerfileList = Collections.singletonList("Dockerfile");
        final BuildahBudCommand buildahBudCommand = new BuildahBudCommand.Builder(buildahExecutor, "dockerFileContext").dockerfileList(dockerfileList).targetImage("targetImg").buildArgs(buildArgs).build();
        List<String> cliCommand = buildahBudCommand.getCliCommand();
        assertThat(cliCommand).containsExactly(transform("bud --tls-verify=false --build-arg TEST=TEST_VAL --file Dockerfile -t targetImg dockerFileContext"));

    }
}
