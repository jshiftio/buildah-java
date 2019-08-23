package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.Buildah;
import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahLogoutCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "logout";
    private static final String ALL_LOGOUT = "--all";
    private static final String AUTH_FILE = "--authfile";

    private String registryName;
    private Boolean allLogout = Boolean.FALSE;
    private String authFile;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahLogoutCommand(CliExecutor buildahExecutor) {
        super(buildahExecutor);
    }

    @Override
    public List<String> getCliCommand() {

        final List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        if(allLogout != null && allLogout.booleanValue()) {
            arguments.add(ALL_LOGOUT);
        }

        if(authFile != null) {
            arguments.add(AUTH_FILE);
            arguments.add(authFile);
        }

        if(registryName != null) {
            arguments.add(registryName);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahLogoutCommand.Builder> {
        private BuildahLogoutCommand buildahLogoutCommand;

        public Builder(CliExecutor buildahExecutor) {
            this.buildahLogoutCommand = new BuildahLogoutCommand(buildahExecutor);
        }

        public BuildahLogoutCommand.Builder registryName(String registryName) {
            this.buildahLogoutCommand.registryName = registryName;
            return this;
        }

        public BuildahLogoutCommand.Builder authFile(String authFile) {
            this.buildahLogoutCommand.authFile = authFile;
            return this;
        }

        public BuildahLogoutCommand.Builder allLogout(boolean allLogout) {
            this.buildahLogoutCommand.allLogout = allLogout;
            return this;
        }

        public BuildahLogoutCommand build() {
            buildahLogoutCommand.globalParametersSupport = buildGlobalParameters();
            return buildahLogoutCommand;
        }
    }
}
