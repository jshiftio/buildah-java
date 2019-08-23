package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahAddCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "add";
    private static final String CHOWN = "--chown";

    private String containerId;
    private String source;
    private String destination;
    private String chown;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahAddCommand(CliExecutor buildahExecutor, String containerId, String source) {
        super(buildahExecutor);
        this.containerId = containerId;
        this.source = source;
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if(chown != null) {
            arguments.add(CHOWN);
            arguments.add(chown);
        }

        arguments.add(containerId);
        arguments.add(source);

        if(destination != null) {
            arguments.add(destination);
        }
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahAddCommand.Builder> {
        private BuildahAddCommand buildahAddCommand;

        public Builder(CliExecutor buildahExecutor, String containerId, String source) {
            this.buildahAddCommand = new BuildahAddCommand(buildahExecutor, containerId, source);
        }

        public BuildahAddCommand.Builder destination(String destination) {
            this.buildahAddCommand.destination = destination;
            return this;
        }

        public BuildahAddCommand.Builder chown(String chown) {
            this.buildahAddCommand.chown = chown;
            return this;
        }

        public BuildahAddCommand build() {
            buildahAddCommand.globalParametersSupport = buildGlobalParameters();
            return buildahAddCommand;
        }
    }
}
