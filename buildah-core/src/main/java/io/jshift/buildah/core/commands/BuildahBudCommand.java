package io.jshift.buildah.core.commands;

import io.jshift.buildah.core.CliExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BuildahBudCommand extends AbstractRunnableCommand<Void> {
    private static final String COMMAND_NAME = "bud";
    private static final String FILE = "--file";
    private static final String TARGET_IMAGE = "-t";
    private static final String TLS_VERIFY = "--tls-verify=false";
    private static final String RUNTIME_FLAG = "--runtime-flag";
    private static final String CERT_DIR = "--cert-dir";
    private static final String CREDENTIALS = "--creds";
    private static final String AUTH_FILE = "--authfile";
    private static final String ULIMIT = "-ulimit";
    private static final String MEMORY = "--memory";
    private static final String CPU_PERIOD = "--cpu-period";
    private static final String CPU_QUOTA = "--cpu-quota";
    private static final String BUILD_ARG = "--build-arg";
    private static final String BUILD_ARG_FMT = "%s=%s";

    List<String> dockerfileList;
    private String dockerfileContext;
    private String targetImage;
    private Boolean tlsVerify = Boolean.TRUE;
    private Boolean runtimeFlag = Boolean.FALSE;
    private String certDir;
    private String credentials;
    private String authfile;
    private List<String> ulimitOptionList;
    private String memory;
    private String cpuPeriod;
    private String cpuQuota;
    private Map<String, String> buildArgs;

    private GlobalParametersSupport globalParametersSupport;
    protected BuildahBudCommand(CliExecutor buildahExecutor, String dockerfileContext) {
        super(buildahExecutor);
        this.dockerfileContext = dockerfileContext;
    }

    @Override
    public List<String> getCliCommand() {
        List<String> arguments = new ArrayList();
        arguments.add(COMMAND_NAME);

        if(authfile != null) {
            arguments.add(AUTH_FILE);
            arguments.add(authfile);
        }

        if(memory != null) {
            arguments.add(MEMORY);
            arguments.add(memory);
        }

        if(cpuPeriod != null) {
            arguments.add(CPU_PERIOD);
            arguments.add(cpuPeriod);
        }

        if(cpuQuota != null) {
            arguments.add(CPU_QUOTA);
            arguments.add(cpuQuota);
        }

        if(ulimitOptionList != null) {
            for(String option: ulimitOptionList) {
                arguments.add(ULIMIT);
                arguments.add(option);
            }
        }

        if(certDir != null) {
            arguments.add(CERT_DIR);
            arguments.add(certDir);
        }

        if(credentials != null) {
            arguments.add(CREDENTIALS);
            arguments.add(credentials);
        }

        if(runtimeFlag != null && runtimeFlag.booleanValue()) {
            arguments.add(RUNTIME_FLAG);
        }

        if(tlsVerify != null && tlsVerify.booleanValue()) {
            arguments.add(TLS_VERIFY);
        }

        if(buildArgs != null) {
            for(Map.Entry<String, String> buildArg : buildArgs.entrySet()) {
              arguments.add(BUILD_ARG);
              arguments.add(String.format(BUILD_ARG_FMT, buildArg.getKey(), buildArg.getValue()));
            }
        }

        if(dockerfileList != null) {
            for(String dockerfile : dockerfileList) {
                arguments.add(FILE);
                arguments.add(dockerfile);
            }
        }

        if(targetImage != null) {
            arguments.add(TARGET_IMAGE);
            arguments.add(targetImage);
        }

        arguments.add(dockerfileContext);
        return arguments;
    }

    public static class Builder extends GlobalParametersSupport.Builder<BuildahBudCommand.Builder> {
        private BuildahBudCommand buildahBudCommand;

        public Builder(CliExecutor buildahExecutor, String context) {
            this.buildahBudCommand = new BuildahBudCommand(buildahExecutor, context);
        }

        public  BuildahBudCommand.Builder authfile(String authfile) {
            this.buildahBudCommand.authfile = authfile;
            return this;
        }

        public BuildahBudCommand.Builder memory(String memory) {
            this.buildahBudCommand.memory = memory;
            return this;
        }

        public BuildahBudCommand.Builder cpuPeriod(String cpuPeriod) {
            this.buildahBudCommand.cpuPeriod = cpuPeriod;
            return this;
        }

        public BuildahBudCommand.Builder cpuQuota(String cpuQouta) {
            this.buildahBudCommand.cpuQuota = cpuQouta;
            return this;
        }

        public BuildahBudCommand.Builder ulimitOptionList(List<String> ulimitOptionList) {
            this.buildahBudCommand.ulimitOptionList = ulimitOptionList;
            return this;
        }

        public BuildahBudCommand.Builder certDir(String certDir) {
            this.buildahBudCommand.certDir = certDir;
            return this;
        }

        public BuildahBudCommand.Builder credentials(String credentials) {
            this.buildahBudCommand.credentials = credentials;
            return this;
        }

        public BuildahBudCommand.Builder runtimeFlag(boolean runtimeFlag) {
            this.buildahBudCommand.runtimeFlag = runtimeFlag;
            return this;
        }

        public BuildahBudCommand.Builder tlsVerify(boolean tlsVerify) {
            this.buildahBudCommand.tlsVerify = tlsVerify;
            return this;
        }

        public BuildahBudCommand.Builder dockerfileList(List<String> dockerfileList) {
            this.buildahBudCommand.dockerfileList = dockerfileList;
            return this;
        }

        public BuildahBudCommand.Builder targetImage(String targetImage) {
            this.buildahBudCommand.targetImage = targetImage;
            return this;
        }

        public BuildahBudCommand.Builder buildArgs(Map<String, String> buildArgs) {
            this.buildahBudCommand.buildArgs = buildArgs;
            return this;
        }

        public BuildahBudCommand build() {
            buildahBudCommand.globalParametersSupport = buildGlobalParameters();
            return buildahBudCommand;
        }
    }
}
