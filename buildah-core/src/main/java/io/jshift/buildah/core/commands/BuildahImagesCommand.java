package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahImagesCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "images";

    private static final String ALL_IMAGES = "--all";
    private static final String DIGESTS = "--digests";
    private static final String JSON_FORMAT = "--json";
    private static final String ONLY_ID = "--quiet";

    private Boolean allImages = Boolean.FALSE;
    private Boolean digests = Boolean.FALSE;
    private Boolean jsonFormat = Boolean.FALSE;
    private Boolean onlyId = Boolean.FALSE;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahImagesCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if (allImages != null && allImages.booleanValue()) {
            arguments.add(ALL_IMAGES);
        }

        if(jsonFormat != null && jsonFormat.booleanValue()) {
            arguments.add(JSON_FORMAT);
        }

        if(onlyId != null && onlyId.booleanValue()) {
            arguments.add(ONLY_ID);
        }

        if(digests != null && digests.booleanValue()) {
            arguments.add(DIGESTS);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahImagesCommand.Builder> {
        private  BuildahImagesCommand buildahImagesCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahImagesCommand = new BuildahImagesCommand(buildahExecutor);
        }

        public BuildahImagesCommand.Builder allImages(boolean allImages) {
            this.buildahImagesCommand.allImages = allImages;
            return this;
        }

        public BuildahImagesCommand.Builder jsonFormat(boolean jsonFormat) {
            this.buildahImagesCommand.jsonFormat = jsonFormat;
            return this;
        }

        public BuildahImagesCommand.Builder digest(boolean digests) {
            this.buildahImagesCommand.digests = digests;
            return this;
        }

        public BuildahImagesCommand.Builder onlyId(boolean onlyId) {
            this.buildahImagesCommand.onlyId = onlyId;
            return this;
        }

        public BuildahImagesCommand build() {
            buildahImagesCommand.globalParametersSupport = buildGlobalParameters();
            return buildahImagesCommand;
        }

    }
}
