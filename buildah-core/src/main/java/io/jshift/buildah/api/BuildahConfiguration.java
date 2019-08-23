package io.jshift.buildah.api;

import java.nio.file.Path;

public class BuildahConfiguration {

    private String buildahVersion;
    private Path localBuildah;
    private String runcVersion;
    private Path localRunc;

    private Path installationDir;

    private boolean printStandardStreamToConsole = true;
    private boolean printErrorStreamToConsole = true;

    public BuildahConfiguration() {

    }

    public boolean isInstallationDirSet() {
        return this.installationDir != null;
    }

    public boolean isLocalBuildahSet() {
        return this.localBuildah != null;
    }

    public boolean isBuildahVersionSet() {
        return this.buildahVersion != null && !this.buildahVersion.isEmpty();
    }

    public boolean isLocalRuncSet() {
        return this.localRunc != null;
    }

    public boolean isRuncVersionSet() {
        return this.runcVersion != null && !this.runcVersion.isEmpty();
    }

    public String getBuildahVersion() {
        return buildahVersion;
    }

    public void setBuildahVersion(String buildahVersion) {
        this.buildahVersion = buildahVersion;
    }

    public void setLocalBuildah(Path localBuildah) {
        this.localBuildah = localBuildah;
    }

    public Path getLocalBuildah() {
        return localBuildah;
    }

    public String getRuncVersion() {
        return runcVersion;
    }

    public void setRuncVersion(String runcVersion) {
        this.runcVersion = runcVersion;
    }

    public void setLocalRunc(Path localRunc) {
        this.localRunc = localRunc;
    }

    public Path getLocalRunc() {
        return localRunc;
    }

    public Path getInstallationDir() {
        return installationDir;
    }

    public void setInstallationDir(Path installationDir) {
        this.installationDir = installationDir;
    }

    public boolean isPrintStandardStreamToConsole() {
        return printStandardStreamToConsole;
    }

    public void setPrintStandardStreamToConsole(boolean printStandardStreamToConsole) {
        this.printStandardStreamToConsole = printStandardStreamToConsole;
    }

    public boolean isPrintErrorStreamToConsole() {
        return printErrorStreamToConsole;
    }

    public void setPrintErrorStreamToConsole(boolean printErrorStreamToConsole) {
        this.printErrorStreamToConsole = printErrorStreamToConsole;
    }}
