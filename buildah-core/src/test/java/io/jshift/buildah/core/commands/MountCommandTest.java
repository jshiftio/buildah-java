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
public class MountCommandTest {

    @Mock
    CliExecutor buildahExecutor;

    @Test
    public void should_execute_mount_command() {

        List<String> conatinerIdList = new ArrayList<>();
        conatinerIdList.add("container1");
        conatinerIdList.add("container2");
        final BuildahMountCommand buildahMountCommand = new BuildahMountCommand.Builder(buildahExecutor).container(conatinerIdList).build();
        List<String> cliCommand = buildahMountCommand.getCliCommand();

        assertThat(cliCommand).containsExactly(transform("mount container1 container2"));
    }
}
