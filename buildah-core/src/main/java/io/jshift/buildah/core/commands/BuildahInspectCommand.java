package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahInspectCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "inspect";
    private static final String TYPE = "--type";

    private String type;
    private String imageId;
    private String containerId;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahInspectCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        if(type != null) {
            arguments.add(TYPE);
            arguments.add(type);
        }

        if (imageId != null) {
            arguments.add(imageId);
        }

        if(containerId != null) {
            arguments.add(containerId);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahInspectCommand.Builder> {
        private BuildahInspectCommand buildahInspectCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahInspectCommand = new BuildahInspectCommand(buildahExecutor);
        }

        public BuildahInspectCommand.Builder type(String type) {
            this.buildahInspectCommand.type = type;
            return this;
        }

        public BuildahInspectCommand.Builder image(String imageId) {
            this.buildahInspectCommand.imageId = imageId;
            return this;
        }

        public BuildahInspectCommand.Builder container(String containerId) {
            this.buildahInspectCommand.containerId = containerId;
            return this;
        }

        public BuildahInspectCommand build() {
            buildahInspectCommand.globalParametersSupport = buildGlobalParameters();
            return buildahInspectCommand;
        }
    }
}
