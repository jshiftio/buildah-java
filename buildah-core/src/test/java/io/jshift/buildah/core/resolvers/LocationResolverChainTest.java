package io.jshift.buildah.core.resolvers;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.LocationResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationResolverChainTest {

    @Test
    public void should_use_classpath_locator_by_default() {

        // Given

        final LocationResolverChain locationResolverChain = new LocationResolverChain();

        // When

        final LocationResolver locationResolver = locationResolverChain.getLocationResolver(new BuildahConfiguration());

        // Then

        assertThat(locationResolver)
                .isInstanceOf(ClasspathLocationResolver.class);
    }

    @Test
    public void should_use_url_locator_if_version_set() {

        // Given

        final LocationResolverChain locationResolverChain = new LocationResolverChain();

        // When

        final BuildahConfiguration buildahConfiguration = new BuildahConfiguration();
        buildahConfiguration.setBuildahVersion("0.0.19");
        buildahConfiguration.setRuncVersion("0.0.19");
        final LocationResolver locationResolver = locationResolverChain.getLocationResolver(buildahConfiguration);

        // Then

        assertThat(locationResolver)
                .isInstanceOf(UrlLocationResolver.class);

    }

    @Test
    public void should_use_url_locator_if_version_system_property_set() {

        // Given

        final LocationResolverChain locationResolverChain = new LocationResolverChain();

        // When


        System.setProperty(LocationResolverChain.BUILDAH_VERSION, "0.0.19");
        System.setProperty(LocationResolverChain.RUNC_VERSION, "0.0.19");
        final BuildahConfiguration buildahConfiguration = new BuildahConfiguration();
        final LocationResolver locationResolver = locationResolverChain.getLocationResolver(buildahConfiguration);

        // Then

        assertThat(locationResolver)
                .isInstanceOf(UrlLocationResolver.class);
    }

    @AfterEach
    public void unset_system_property() {
        System.clearProperty(LocationResolverChain.BUILDAH_VERSION);
        System.clearProperty(LocationResolverChain.RUNC_VERSION);
    }
}
