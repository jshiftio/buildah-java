package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahRunCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "run";
    private static final String TTY = "--tty";
    private static final String HOSTNAME = "--hostname";

    private Boolean tty = Boolean.FALSE;
    private String containerName;
    private String commandRun;
    private List<String> commandOptions;
    private String hostname;
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

        if(hostname != null) {
            arguments.add(HOSTNAME);
            arguments.add(hostname);
        }

        if(tty != null && tty.booleanValue()) {
            arguments.add(TTY);
        }

        arguments.add(this.containerName);
        arguments.add(this.commandRun);

        if(commandOptions != null) {
            arguments.addAll(commandOptions);
        }
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahRunCommand.Builder> {
        private BuildahRunCommand buildahRunCommand;

        public Builder(String containerName, String commandRun, CliExecutor buildahExecutor) {
            this.buildahRunCommand = new BuildahRunCommand(buildahExecutor, containerName, commandRun);
        }

        public BuildahRunCommand.Builder commandOptions(List<String> commandOptions) {
            this.buildahRunCommand.commandOptions = commandOptions;
            return this;
        }

        public BuildahRunCommand.Builder tty(boolean tty) {
            this.buildahRunCommand.tty = tty;
            return this;
        }

        public BuildahRunCommand.Builder hostname(String hostname) {
            this.buildahRunCommand.hostname = hostname;
            return this;
        }

        public BuildahRunCommand build() {
            buildahRunCommand.globalParametersSupport = this.buildGlobalParameters();
            return buildahRunCommand;
        }
    }
}
