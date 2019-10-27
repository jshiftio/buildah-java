package io.jshift.buildah.core;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.LocationResolver;
import io.jshift.buildah.core.resolvers.LocationResolverChain;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class InstallManager {

    private Path path;

    //for testing only
    LocationResolverChain locationResolverChain;

    public InstallManager() {

    }

    //Visible for testing purpose only
    public Path install(BuildahConfiguration buildahConfiguration) throws IOException {
        final LocationResolver locationResolver = this.locationResolverChain.getLocationResolver(buildahConfiguration);
        return install(locationResolver.getBuildahName(), locationResolver.loadBuildahResource(), buildahConfiguration);
    }

    public Path install(String name, InputStream content, BuildahConfiguration buildahConfiguration) throws IOException {


        final FileManager fileManager = new FileManager(name, content);

        if (buildahConfiguration.isInstallationDirSet()) {
            path = fileManager.copyToLocation(buildahConfiguration.getInstallationDir());
        } else {
            path = fileManager.copyToTemp();
        }


        FilePermission.execPermission(path);

        return path;
    }

    public void uninstall(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
