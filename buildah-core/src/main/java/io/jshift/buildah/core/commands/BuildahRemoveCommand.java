package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahRemoveCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "rm";
    private static final String REMOVE_ALL = "--all";

    private String containerId;
    private Boolean removeAll = Boolean.FALSE;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahRemoveCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if(containerId != null) {
            arguments.add(containerId);
        }

        if(removeAll != null && removeAll.booleanValue()) {
            arguments.add(REMOVE_ALL);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahRemoveCommand.Builder> {
        private BuildahRemoveCommand buildahRemoveCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahRemoveCommand = new BuildahRemoveCommand(buildahExecutor);
        }

        public BuildahRemoveCommand.Builder containerId(String containerId) {
            this.buildahRemoveCommand.containerId = containerId;
            return this;
        }

        public BuildahRemoveCommand.Builder all(boolean removeAll) {
            this.buildahRemoveCommand.removeAll = removeAll;
            return this;
        }

        public BuildahRemoveCommand build() {
            buildahRemoveCommand.globalParametersSupport = buildGlobalParameters();
            return buildahRemoveCommand;
        }
    }
}
