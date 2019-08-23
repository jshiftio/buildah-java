package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahRemoveImageCommand extends AbstractRunnableCommand<Void> {
    private static final String COMMAND_NAME = "rmi";
    private static final String ALL = "--all";
    private static final String PRUNE = "--prune";
    private static final String FORCE = "--force";

    private String imageId;
    private Boolean removeAll = Boolean.FALSE;
    private Boolean prune = Boolean.FALSE;
    private Boolean force = Boolean.FALSE;
    private GlobalParametersSupport globalParametersSupport;

    protected BuildahRemoveImageCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if(imageId != null) {
            arguments.add(imageId);
        }

        if(removeAll != null && removeAll.booleanValue()) {
            arguments.add(ALL);
        }

        if(prune != null && prune.booleanValue()) {
            arguments.add(PRUNE);
        }

        if(force != null && force.booleanValue()) {
            arguments.add(FORCE);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahRemoveImageCommand.Builder> {
        private BuildahRemoveImageCommand buildahRemoveImageCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahRemoveImageCommand = new BuildahRemoveImageCommand(buildahExecutor);
        }

        public BuildahRemoveImageCommand.Builder image(String imageId) {
            this.buildahRemoveImageCommand.imageId = imageId;
            return this;
        }

        public BuildahRemoveImageCommand.Builder all(boolean removeAll) {
            this.buildahRemoveImageCommand.removeAll = removeAll;
            return this;
        }

        public BuildahRemoveImageCommand.Builder prune(boolean prune) {
            this.buildahRemoveImageCommand.prune = prune;
            return this;
        }

        public BuildahRemoveImageCommand.Builder force(boolean force) {
            this.buildahRemoveImageCommand.force = force;
            return this;
        }

        public BuildahRemoveImageCommand build() {
            buildahRemoveImageCommand.globalParametersSupport = buildGlobalParameters();
            return buildahRemoveImageCommand;
        }
    }
}
