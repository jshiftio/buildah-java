package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahListContainersCommand extends AbstractRunnableCommand<BuildahListContainersCommand>{

    private static final String COMMAND_NAME = "containers";

    private static final String ALL_CONTAINERS = "--all";
    private static final String JSON_FORMAT = "--json";
    private static final String ONLY_ID = "--quiet";

    private Boolean allContainers = Boolean.FALSE;
    private Boolean jsonFormat = Boolean.FALSE;
    private Boolean onlyId = Boolean.FALSE;

    private GlobalParametersSupport globalParametersSupport;

    private BuildahListContainersCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if (allContainers != null && allContainers.booleanValue()) {
            arguments.add(ALL_CONTAINERS);
        }

        if(jsonFormat != null && jsonFormat.booleanValue()) {
            arguments.add(JSON_FORMAT);
        }

        if(onlyId != null && onlyId.booleanValue()) {
            arguments.add(ONLY_ID);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahListContainersCommand.Builder> {
        private BuildahListContainersCommand buildahListContainersCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahListContainersCommand = new BuildahListContainersCommand(buildahExecutor);
        }

        public BuildahListContainersCommand.Builder allContainers(boolean allContainers) {
            this.buildahListContainersCommand.allContainers = allContainers;
            return this;
        }

        public BuildahListContainersCommand.Builder jsonFormat(boolean jsonFormat) {
            this.buildahListContainersCommand.jsonFormat = jsonFormat;
            return this;
        }

        public BuildahListContainersCommand.Builder onlyId(boolean onlyId) {
            this.buildahListContainersCommand.onlyId = onlyId;
            return this;
        }

        public BuildahListContainersCommand build() {
            buildahListContainersCommand.globalParametersSupport = buildGlobalParameters();
            return buildahListContainersCommand;
        }
    }
}
