package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;

public class BuildahCommitCommand extends AbstractRunnableCommand<Void> {

    private static final String COMMAND_NAME = "commit";
    private static final String REM_CONT_AFTER_COMMIT = "--rm";
    private static final String DISABLE_COMPRESSION = "--disable-compression";
    private static final String TLS_VERIFY = "--tls-verify=false";
    private static final String CERT_DIR = "--cert-dir";
    private static final String CREDENTIALS = "--creds";
    private static final String AUTH_FILE = "--authfile";

    private String containerId;
    private String newImageName;
    private Boolean remContAfterCommit = Boolean.FALSE;
    private Boolean disableCompression = Boolean.FALSE;
    private Boolean tlsVerify = Boolean.TRUE;
    private String certDir;
    private String credentials;
    private String authfile;

    private GlobalParametersSupport globalParametersSupport;

    protected BuildahCommitCommand(CliExecutor buildahExecutor, String containerId) {
        super(buildahExecutor);
        this.containerId = containerId;
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

        if(remContAfterCommit != null && remContAfterCommit.booleanValue()) {
            arguments.add(REM_CONT_AFTER_COMMIT);
        }

        if(disableCompression != null && disableCompression.booleanValue()) {
            arguments.add(DISABLE_COMPRESSION);
        }

        arguments.add(containerId);

        if(newImageName != null) {
            arguments.add(newImageName);
        }

        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahCommitCommand.Builder> {
        private BuildahCommitCommand buildahCommitCommand;

        public Builder(String containerId, CliExecutor buildahExecutor) {
            this.buildahCommitCommand = new BuildahCommitCommand(buildahExecutor, containerId);
        }

        public BuildahCommitCommand.Builder withImageName(String newImageName) {
            this.buildahCommitCommand.newImageName = newImageName;
            return this;
        }

        public BuildahCommitCommand.Builder contRemAfterCommit(boolean remContAfterCommit) {
            this.buildahCommitCommand.remContAfterCommit = remContAfterCommit;
            return this;
        }

        public BuildahCommitCommand.Builder disablecompression(boolean disableCompression) {
            this.buildahCommitCommand.disableCompression = disableCompression;
            return this;
        }

        public BuildahCommitCommand.Builder tlsVerification(boolean tlsVerify) {
            this.buildahCommitCommand.tlsVerify = tlsVerify;
            return this;
        }

        public BuildahCommitCommand.Builder authfile(String authfile) {
            this.buildahCommitCommand.authfile = authfile;
            return this;
        }

        public BuildahCommitCommand.Builder certDir(String certDir) {
            this.buildahCommitCommand.certDir = certDir;
            return this;
        }

        public BuildahCommitCommand.Builder credentials(String credentials) {
            this.buildahCommitCommand.credentials = credentials;
            return this;
        }

        public BuildahCommitCommand build() {
            buildahCommitCommand.globalParametersSupport = buildGlobalParameters();
            return buildahCommitCommand;
        }
    }
}
