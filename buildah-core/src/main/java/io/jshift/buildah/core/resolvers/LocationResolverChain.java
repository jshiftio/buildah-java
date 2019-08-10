package io.jshift.buildah.core.resolvers;

import io.jshift.buildah.api.BuildahConfiguration;
import io.jshift.buildah.api.LocationResolver;

public class LocationResolverChain {

    static final String BUILDAH_VERSION = "buildah.version";
    static final String RUNC_VERSION = "runc.version";
    private static final String BUILDAH_BINARY_FORMAT = "buildah";
    private static final String RUNC_BINARY_FORMAT = "runc";
    OperatingSystemConfig operatingSystemConfig;

    public LocationResolverChain() {
        this.operatingSystemConfig = new OperatingSystemConfig(System.getProperty("os.name"));
    }

    public LocationResolver getLocationResolver(BuildahConfiguration buildahConfiguration) {

        final String buildahBinary = this.operatingSystemConfig.resolveBinary(BUILDAH_BINARY_FORMAT);
        final String runcBinary = this.operatingSystemConfig.resolveBinary(RUNC_BINARY_FORMAT);

        if (System.getProperty(BUILDAH_VERSION) != null && System.getProperty(RUNC_VERSION) != null) {
            return getUrlLocationResolver(buildahBinary, System.getProperty(BUILDAH_VERSION), runcBinary, System.getProperty(RUNC_VERSION));
        }

        if (buildahConfiguration.isBuildahVersionSet() && buildahConfiguration.isRuncVersionSet()) {
            return getUrlLocationResolver(buildahBinary, buildahConfiguration.getBuildahVersion(), runcBinary, buildahConfiguration.getRuncVersion());
        }

        return getClasspathLocationResolver(buildahBinary, runcBinary);
    }

    private LocationResolver getClasspathLocationResolver(String buildahBinary, String runcBinary) {
        return new ClasspathLocationResolver(buildahBinary, runcBinary);
    }

    private LocationResolver getUrlLocationResolver(String buildahBinary, String buildahVersion, String runcBinary, String runcVersion) {
        return new UrlLocationResolver(buildahBinary, buildahVersion, runcBinary, runcVersion);
    }

}
