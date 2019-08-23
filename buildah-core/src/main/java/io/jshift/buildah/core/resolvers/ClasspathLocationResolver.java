package io.jshift.buildah.core.resolvers;

import io.jshift.buildah.api.LocationResolver;

import java.io.InputStream;

public class ClasspathLocationResolver implements LocationResolver {

    private static final String PACKAGE_LOCATION = "binaries";

    private String fileclasspathBuildah;
    private String fileclasspathRunc;
    private String buildahName;
    private String runcName;

    public ClasspathLocationResolver(String buildahBinary, String runcBinary) {
        this.fileclasspathBuildah = PACKAGE_LOCATION + "/" + buildahBinary;
        this.buildahName = buildahBinary;
        this.fileclasspathRunc = PACKAGE_LOCATION + "/" + runcBinary;
        this.runcName = runcBinary;
    }

    @Override
    public InputStream loadBuildahResource() {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(this.fileclasspathBuildah);
    }

    @Override
    public InputStream loadRuncResource() {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(this.fileclasspathRunc);
    }

    @Override
    public String getBuildahName() {
        return buildahName;
    }

    @Override
    public String getRuncName() {
        return runcName;
    }
}
