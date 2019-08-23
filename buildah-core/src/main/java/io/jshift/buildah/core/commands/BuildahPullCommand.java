package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahPullCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "pull";
    private static final String CERT_DIR = "--cert-dir";
    private static final String CREDENTIALS = "--creds";
    private static final String AUTH_FILE = "--authfile";
    private static final String TLS_VERIFY = "--tls-verify=false";

    private String imageName;
    private String certDir;
    private String credentials;
    private String authfile;
    private Boolean tlsVerify = Boolean.TRUE;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahPullCommand(CliExecutor buildahExecutor, String imageName) {
        super(buildahExecutor);
        this.imageName = imageName;
    }

    @Override
    public List<String> getCliCommand() {

        final List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        if(authfile != null) {
            arguments.add(AUTH_FILE);
            arguments.add(authfile);
        }

        if(certDir != null) {
            arguments.add(CERT_DIR);
            arguments.add(certDir);
        }

        if(tlsVerify != null && tlsVerify.booleanValue()) {
            arguments.add(TLS_VERIFY);
        }

        if(credentials != null) {
            arguments.add(CREDENTIALS);
            arguments.add(credentials);
        }

        arguments.add(imageName);
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahPullCommand.Builder> {
        private BuildahPullCommand buildahPullCommand;

        public Builder(String imageName, CliExecutor buildahExecutor) {
            this.buildahPullCommand = new BuildahPullCommand(buildahExecutor, imageName);
        }

        public BuildahPullCommand.Builder tlsVerification(boolean tlsVerify) {
            this.buildahPullCommand.tlsVerify = tlsVerify;
            return this;
        }

        public BuildahPullCommand.Builder authfile(String authfile) {
            this.buildahPullCommand.authfile = authfile;
            return this;
        }

        public BuildahPullCommand.Builder certDir(String certDir) {
            this.buildahPullCommand.certDir = certDir;
            return this;
        }

        public BuildahPullCommand.Builder credentials(String credentials) {
            this.buildahPullCommand.credentials = credentials;
            return this;
        }

        public BuildahPullCommand build() {
            buildahPullCommand.globalParametersSupport = buildGlobalParameters();
            return buildahPullCommand;
        }

    }
}
