package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahPushCommand extends AbstractRunnableCommand<Void>{

    private static final String COMMAND_NAME = "push";
    private static final String CREDS = "--creds";
    private static final String TLS_VERIFY = "--tls-verify=false";
    private static final String CERT_DIR = "--cert-dir";
    private static final String AUTH_FILE = "--authfile";


    private String imageId;
    private String  registry;
    private String credentials;
    private Boolean tlsVerify = Boolean.TRUE;
    private String certDir;
    private String authfile;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahPushCommand(CliExecutor buildahExecutor, String imageId) {
        super(buildahExecutor);
        this.imageId = imageId;
    }

    @Override
    public List<String> getCliCommand() {

        final List<String> arguments = new ArrayList<>();
        arguments.add(COMMAND_NAME);

        if(certDir != null) {
            arguments.add(CERT_DIR);
            arguments.add(certDir);
        }

        if(authfile != null) {
            arguments.add(AUTH_FILE);
            arguments.add(authfile);
        }

        if(tlsVerify != null && tlsVerify.booleanValue()) {
            arguments.add(TLS_VERIFY);
        }

        if(credentials != null) {
            arguments.add(CREDS);
            arguments.add(credentials);
        }

        arguments.add(imageId);

        if(registry != null) {
            arguments.add(registry);
        }
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahPushCommand.Builder> {
        private BuildahPushCommand buildahPushCommand;

        public Builder(CliExecutor buildahExecutor, String imageId) {
            this.buildahPushCommand = new BuildahPushCommand(buildahExecutor, imageId);
        }

        public BuildahPushCommand.Builder tlsVerify(boolean tlsVerify) {
            this.buildahPushCommand.tlsVerify = tlsVerify;
            return this;
        }

        public BuildahPushCommand.Builder creds(String credentials) {
            this.buildahPushCommand.credentials = credentials;
            return this;
        }

        public BuildahPushCommand.Builder registry(String registry) {
            this.buildahPushCommand.registry = registry;
            return this;
        }

        public BuildahPushCommand.Builder authfile(String authfile) {
            this.buildahPushCommand.authfile = authfile;
            return this;
        }

        public BuildahPushCommand.Builder certDir(String certDir) {
            this.buildahPushCommand.certDir = certDir;
            return this;
        }

        public BuildahPushCommand build() {
            buildahPushCommand.globalParametersSupport = buildGlobalParameters();
            return  buildahPushCommand;
        }
    }
}
