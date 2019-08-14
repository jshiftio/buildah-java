package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahMountCommand extends AbstractRunnableCommand<Void>{
    private static final String COMMAND_NAME = "mount";

    private List<String> containerIds;
    private GlobalParametersSupport globalParametersSupport;
    protected BuildahMountCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        if(containerIds != null) {
            for(String containerId : containerIds) {
                arguments.add(containerId);
            }
        }
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahMountCommand.Builder> {
        private BuildahMountCommand buildahMountCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahMountCommand = new BuildahMountCommand(buildahExecutor);
        }

        public BuildahMountCommand.Builder container(List<String> containerIds) {
            this.buildahMountCommand.containerIds = containerIds;
            return this;
        }

        public BuildahMountCommand build() {
            buildahMountCommand.globalParametersSupport = buildGlobalParameters();
            return buildahMountCommand;
        }
    }
}
