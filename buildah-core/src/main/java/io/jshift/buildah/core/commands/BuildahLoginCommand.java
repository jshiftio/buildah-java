package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.Buildah;
import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahLoginCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "login";
    private static final String USERNAME = "-u";
    private static final String PASSWORD = "-p";
    private static final String TLS_VERIFY = "--tls-verify=false";

    private String registryName;
    private String username;
    private String password;
    private Boolean tlsVerify = Boolean.TRUE;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahLoginCommand(CliExecutor buildahExecutor, String registryName) {
        super(buildahExecutor);
        this.registryName = registryName;
    }

    @Override
    public List<String> getCliCommand() {
        final List<String> arguments = new ArrayList<>();

        arguments.add(COMMAND_NAME);

        if(tlsVerify != null && tlsVerify.booleanValue()) {
            arguments.add(TLS_VERIFY);
        }

        if(username != null) {
            arguments.add(USERNAME);
            arguments.add(username);
        }

        if(password != null) {
            arguments.add(PASSWORD);
            arguments.add(password);
        }

        arguments.add(registryName);

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahLoginCommand.Builder> {
        private BuildahLoginCommand buildahLoginCommand;

        public Builder(String registryName, CliExecutor buildahExecutor) {
            this.buildahLoginCommand = new BuildahLoginCommand(buildahExecutor, registryName);
        }

        public BuildahLoginCommand.Builder tlsVerify(boolean tlsVerify) {
            this.buildahLoginCommand.tlsVerify = tlsVerify;
            return this;
        }

        public BuildahLoginCommand.Builder username(String username) {
            this.buildahLoginCommand.username = username;
            return this;
        }

        public BuildahLoginCommand.Builder password(String password) {
            this.buildahLoginCommand.password = password;
            return this;
        }

        public BuildahLoginCommand build() {
            buildahLoginCommand.globalParametersSupport = buildGlobalParameters();
            return buildahLoginCommand;
        }
    }


}
