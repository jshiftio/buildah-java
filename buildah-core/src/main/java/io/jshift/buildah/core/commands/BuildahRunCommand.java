package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahRunCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "run";

    private String containerName;
    private String commandRun;
    private GlobalParametersSupport globalParametersSupport;
    protected BuildahRunCommand(CliExecutor buildahExecutor, String containerName, String commandRun) {
        super(buildahExecutor);
        this.containerName = containerName;
        this.commandRun = commandRun;
    }


    @Override
    public List<String> getCliCommand() {
        List<String> arguments = new ArrayList();
        arguments.add(COMMAND_NAME);
        arguments.add(this.containerName);
        arguments.add(this.commandRun);

        if (this.globalParametersSupport != null) {
            arguments.addAll(this.globalParametersSupport.getCliCommand());
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahRunCommand.Builder> {
        private BuildahRunCommand buildahRunCommand;

        public Builder(String containerName, String commandRun, CliExecutor buildahExecutor) {
            this.buildahRunCommand = new BuildahRunCommand(buildahExecutor, containerName, commandRun);
        }

        public BuildahRunCommand build() {
            this.buildahRunCommand.globalParametersSupport = this.buildGlobalParameters();
            return this.buildahRunCommand;
        }
    }
}
