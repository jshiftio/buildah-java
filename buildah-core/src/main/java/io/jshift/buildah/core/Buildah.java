package io.jshift.buildah.core;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.LocationResolver;
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
        //buildahExecutor = new BuildahExecutor(this.buildahHome, this.buildahConfiguration);
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

}
