package io.jshift.buildah.core;

import io.jshift.buildah.api.LocationResolver;
import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.core.resolvers.LocationResolverChain;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InstallManagerTest {

    @Mock
    LocationResolverChain locationResolverChain;

    @Mock
    LocationResolver locationResolver;

    @Test
    public void should_install_file_with_executable_permission() throws IOException {

        // Given

        when(locationResolverChain.getLocationResolver(any(BuildahConfiguration.class))).thenReturn(locationResolver);
        when(locationResolver.getBuildahName()).thenReturn("hellobuildah.txt");
        when(locationResolver.loadBuildahResource()).thenReturn(new ByteArrayInputStream("binaries/hello.txt".getBytes()));

        InstallManager installManager = new InstallManager();
        installManager.locationResolverChain = locationResolverChain;

        // When
        final Path installPath = installManager.install(new BuildahConfiguration());

        assertThat(installPath)
                .exists()
                .isExecutable();
    }

    @Test
    public void should_install_file_in_concrete_location(@TempDir Path installationDir) throws IOException {

        // Given

        when(locationResolverChain.getLocationResolver(any(BuildahConfiguration.class))).thenReturn(locationResolver);
        when(locationResolver.getBuildahName()).thenReturn("hellobuildah.txt");
        when(locationResolver.loadBuildahResource()).thenReturn(new ByteArrayInputStream("binaries/hello.txt".getBytes()));

        InstallManager installManager = new InstallManager();
        installManager.locationResolverChain = locationResolverChain;

        // When

        final BuildahConfiguration buildahConfiguration = new BuildahConfiguration();
        buildahConfiguration.setInstallationDir(installationDir);

        final Path installPath = installManager.install(buildahConfiguration);

        // Then

        assertThat(installPath).startsWith(installationDir);

    }

    @Test
    public void should_uninstall_file() throws IOException {

        // Given

        when(locationResolverChain.getLocationResolver(any(BuildahConfiguration.class))).thenReturn(locationResolver);
        when(locationResolver.getBuildahName()).thenReturn("hellobuildah.txt");
        when(locationResolver.loadBuildahResource()).thenReturn(new ByteArrayInputStream("binaries/hello.txt".getBytes()));

        InstallManager installManager = new InstallManager();
        installManager.locationResolverChain = locationResolverChain;

        // When
        final Path installPath = installManager.install(new BuildahConfiguration());
        installManager.uninstall(installPath);

        // Then

        assertThat(installPath)
                .doesNotExist();
    }
}