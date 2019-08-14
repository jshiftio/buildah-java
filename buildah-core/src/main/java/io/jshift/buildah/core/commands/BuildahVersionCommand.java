package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahVersionCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "version";

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahVersionCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahVersionCommand.Builder> {

        private BuildahVersionCommand buildahVersionCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahVersionCommand = new BuildahVersionCommand(buildahExecutor);
        }

        public BuildahVersionCommand build() {
            buildahVersionCommand.globalParametersSupport = buildGlobalParameters();
            return buildahVersionCommand;
        }
    }
}
