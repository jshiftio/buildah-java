package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildahTagCommand extends AbstractRunnableCommand<Void> {
    private static final String COMMAND_NAME = "tag";

    private List<String> tags;
    private String imageName;

    private GlobalParametersSupport globalParametersSupport;
    protected BuildahTagCommand(CliExecutor buildahExecutor, String imageName) {
        super(buildahExecutor);
        this.imageName = imageName;
    }

    @Override
    public List<String> getCliCommand() {
        List<String> arguments = new ArrayList();
        arguments.add(COMMAND_NAME);
        arguments.add(imageName);

        if(tags != null) {
            for(String tag: tags) {
                arguments.add(tag);
            }
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahTagCommand.Builder> {
        private BuildahTagCommand buildahTagCommand;

        public Builder(CliExecutor buildahExecutor, String imageName) {
            this.buildahTagCommand = new BuildahTagCommand(buildahExecutor, imageName);
        }

        public  BuildahTagCommand.Builder tags(List<String> tags) {
            this.buildahTagCommand.tags = tags;
            return this;
        }

        public BuildahTagCommand build() {
            buildahTagCommand.globalParametersSupport = buildGlobalParameters();
            return buildahTagCommand;
        }
    }
}
