package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahCopyCommand extends AbstractRunnableCommand<Void>{

    private static final String COMMAND_NAME = "copy";
    private static final String CHOWN = "--chown";

    private String containerId;
    private String source;
    private String destination;
    private String chown;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahCopyCommand(CliExecutor buildahExecutor, String containerId, String source) {
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

    public static class Builder extends GlobalParametersSupport.Builder<BuildahCopyCommand.Builder> {
        private BuildahCopyCommand buildahCopyCommand;

        public Builder(CliExecutor buildahExecutor, String containerId, String source) {
            this.buildahCopyCommand = new BuildahCopyCommand(buildahExecutor, containerId, source);
        }

        public BuildahCopyCommand.Builder destination(String destination) {
            this.buildahCopyCommand.destination = destination;
            return this;
        }

        public BuildahCopyCommand.Builder chown(String chown) {
            this.buildahCopyCommand.chown = chown;
            return this;
        }

        public BuildahCopyCommand build() {
            buildahCopyCommand.globalParametersSupport = buildGlobalParameters();
            return buildahCopyCommand;
        }
    }
}
