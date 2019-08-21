package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahConfigCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "config";
    private static final String AUTHOR = "--author";
    private static final String ENV = "--env";
    private static final String LABEL = "--label";
    private static final String ANNOTATION = "--annotation";
    private static final String VOLUME = "--volume";
    private static final String WORKING_DIR = "--workingdir";
    private static final String PORT = "--port";
    private static final String ENTRYPOINT = "--entrypoint";

    private String author;
    private String containerId;
    private List<String> envList;
    private List<String> labelList;
    private List<String> annotationList;
    private String volumePath;
    private String workingDir;
    private List<String> portList;
    private List<String> entrypointList;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahConfigCommand(CliExecutor buildahExecutor, String containerId) {
        super(buildahExecutor);
        this.containerId = containerId;
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if(author != null) {
            arguments.add(AUTHOR);
            arguments.add(author);
        }

        if(envList != null) {
            for(String env:envList) {
                arguments.add(ENV);
                arguments.add(env);
            }
        }

        if(labelList != null) {
            for(String label:labelList) {
                arguments.add(LABEL);
                arguments.add(label);
            }
        }

        if(annotationList != null) {
            for(String annotation:annotationList) {
                arguments.add(ANNOTATION);
                arguments.add(annotation);
            }
        }

        if(volumePath != null) {
            arguments.add(VOLUME);
            arguments.add(volumePath);
        }

        if(workingDir != null) {
            arguments.add(WORKING_DIR);
            arguments.add(workingDir);
        }

        if(portList != null) {
            for(String port:portList) {
                arguments.add(PORT);
                arguments.add(port);
            }
        }

        if(entrypointList != null) {
            arguments.add(ENTRYPOINT);
            String entrypointString = "[";
            for(String e:entrypointList) {
                entrypointString += "\"e\"";
            }
            entrypointString+="]";
            arguments.add(entrypointString);
        }

        arguments.add(containerId);
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahConfigCommand.Builder> {

        private BuildahConfigCommand buildahConfigCommand;

        public Builder(CliExecutor buildahExecutor, String containerId) {
            this.buildahConfigCommand = new BuildahConfigCommand(buildahExecutor, containerId);
        }

        public BuildahConfigCommand.Builder authorInfo(String author) {
            this.buildahConfigCommand.author = author;
            return this;
        }

        public BuildahConfigCommand.Builder env(List<String> envList) {
            this.buildahConfigCommand.envList = envList;
            return this;
        }

        public BuildahConfigCommand.Builder label(List<String> labelList) {
            this.buildahConfigCommand.labelList = labelList;
            return this;
        }

        public BuildahConfigCommand.Builder annotation(List<String> annotationList) {
            this.buildahConfigCommand.annotationList = annotationList;
            return this;
        }

        public BuildahConfigCommand.Builder volume(String volumePath) {
            this.buildahConfigCommand.volumePath = volumePath;
            return this;
        }

        public BuildahConfigCommand.Builder workingDir(String workingDir) {
            this.buildahConfigCommand.workingDir = workingDir;
            return this;
        }

        public BuildahConfigCommand.Builder port(List<String> portList) {
            this.buildahConfigCommand.portList = portList;
            return this;
        }

        public BuildahConfigCommand.Builder entrypoint(List<String> entrypointList) {
            this.buildahConfigCommand.entrypointList = entrypointList;
            return this;
        }

        public BuildahConfigCommand build() {
            buildahConfigCommand.globalParametersSupport = buildGlobalParameters();
            return buildahConfigCommand;
        }
    }
}
