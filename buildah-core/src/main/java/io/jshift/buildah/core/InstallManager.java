package io.jshift.buildah.core;

import io.jshift.buildah.api.BuildahConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class InstallManager {

    private Path path;

    public InstallManager() {

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

    void uninstall() throws IOException {
        if (Files.exists(path)) {
            Files.delete(path);
        }
    }
}
