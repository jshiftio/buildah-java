package io.jshift.buildah.core;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.LocationResolver;
import io.jshift.buildah.core.commands.BuildahAddCommand;
import io.jshift.buildah.core.commands.BuildahBudCommand;
import io.jshift.buildah.core.commands.BuildahCommitCommand;
import io.jshift.buildah.core.commands.BuildahConfigCommand;
import io.jshift.buildah.core.commands.BuildahCopyCommand;
import io.jshift.buildah.core.commands.BuildahFromCommand;
import io.jshift.buildah.core.commands.BuildahImagesCommand;
import io.jshift.buildah.core.commands.BuildahInspectCommand;
import io.jshift.buildah.core.commands.BuildahListContainersCommand;
import io.jshift.buildah.core.commands.BuildahLoginCommand;
import io.jshift.buildah.core.commands.BuildahLogoutCommand;
import io.jshift.buildah.core.commands.BuildahMountCommand;
import io.jshift.buildah.core.commands.BuildahPullCommand;
import io.jshift.buildah.core.commands.BuildahPushCommand;
import io.jshift.buildah.core.commands.BuildahRemoveCommand;
import io.jshift.buildah.core.commands.BuildahRemoveImageCommand;
import io.jshift.buildah.core.commands.BuildahRunCommand;
import io.jshift.buildah.core.commands.BuildahTagCommand;
import io.jshift.buildah.core.commands.BuildahVersionCommand;
import io.jshift.buildah.core.resolvers.LocationResolverChain;

import java.io.IOException;
import java.nio.file.Path;

public class Buildah {

    private final BuildahConfiguration buildahConfiguration;

    private InstallManager installManager = new InstallManager();
    private CliExecutor buildahExecutor;

    protected Path buildahHome;
    protected Path runcHome;

    LocationResolverChain locationResolverChain;

    public Buildah() {
        this(new BuildahConfiguration());
    }

    public Buildah(final BuildahConfiguration buildahConfiguration) {
        this.buildahConfiguration = buildahConfiguration;
        this.locationResolverChain = new LocationResolverChain();
        install();
        buildahExecutor = new BuildahExecutor(this.buildahHome, this.runcHome, this.buildahConfiguration);
    }

    public Buildah(CliExecutor buildahExecutor) {
        this.buildahConfiguration = new BuildahConfiguration();
        this.buildahExecutor = buildahExecutor;
    }

    protected void install() {
        try {
            if (this.buildahConfiguration.isLocalBuildahSet() && this.buildahConfiguration.isLocalRuncSet()) {
                buildahHome = this.buildahConfiguration.getLocalBuildah();
                runcHome = this.buildahConfiguration.getLocalRunc();
            } else {
                final LocationResolver locationResolver = this.locationResolverChain.getLocationResolver(buildahConfiguration);
                buildahHome = buildahHome == null ? installManager.install(locationResolver.getBuildahName(), locationResolver.loadBuildahResource(), buildahConfiguration) : buildahHome;
                runcHome = runcHome == null ? installManager.install(locationResolver.getRuncName(), locationResolver.loadRuncResource(), buildahConfiguration) : runcHome;
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public BuildahFromCommand.Builder createContainer(String baseImageName) {
        return new BuildahFromCommand.Builder(baseImageName, this.buildahExecutor);
    }

    public BuildahListContainersCommand.Builder listContainers() {
        return new BuildahListContainersCommand.Builder(this.buildahExecutor);
    }

    public BuildahImagesCommand.Builder listImages() {
        return new BuildahImagesCommand.Builder(this.buildahExecutor);
    }

    public BuildahRunCommand.Builder run(String containerName, String commandRun) {
        return new BuildahRunCommand.Builder(containerName, commandRun, this.buildahExecutor);
    }

    public BuildahVersionCommand.Builder version() {
        return new BuildahVersionCommand.Builder(buildahExecutor);
    }

    public BuildahLoginCommand.Builder login(String registryName) {
        return new BuildahLoginCommand.Builder(registryName, buildahExecutor);
    }

    public BuildahLogoutCommand.Builder logout() {
        return new BuildahLogoutCommand.Builder(buildahExecutor);
    }

    public BuildahPushCommand.Builder push(String imageId) {
        return new BuildahPushCommand.Builder(buildahExecutor, imageId);
    }

    public BuildahCommitCommand.Builder commit(String containerId) {
        return new BuildahCommitCommand.Builder(containerId, buildahExecutor);
    }

    public BuildahConfigCommand.Builder config(String containerId) {
        return new BuildahConfigCommand.Builder(buildahExecutor, containerId);
    }

    public BuildahAddCommand.Builder add(String containerId, String source) {
        return new BuildahAddCommand.Builder(buildahExecutor, containerId, source);
    }

    public BuildahCopyCommand.Builder copy(String containerId, String source) {
        return new BuildahCopyCommand.Builder(buildahExecutor, containerId, source);
    }

    public BuildahRemoveCommand.Builder rm() {
        return new BuildahRemoveCommand.Builder(buildahExecutor);
    }

    public BuildahRemoveImageCommand.Builder rmi() {
        return new BuildahRemoveImageCommand.Builder(buildahExecutor);
    }

    public BuildahInspectCommand.Builder inspect() {
        return new BuildahInspectCommand.Builder(buildahExecutor);
    }

    public BuildahMountCommand.Builder mount() {
        return new BuildahMountCommand.Builder(buildahExecutor);
    }

    public BuildahBudCommand.Builder bud(String context) {
        return new BuildahBudCommand.Builder(buildahExecutor, context);
    }

    public BuildahPullCommand.Builder pull(String imageName) {
        return new BuildahPullCommand.Builder(imageName, buildahExecutor);
    }

    public BuildahTagCommand.Builder tag(String imageName) {
        return new BuildahTagCommand.Builder(buildahExecutor, imageName);
    }
}
