package io.jshift.buildah.core.resolvers;

import io.jshift.buildah.api.LocationResolver;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLocationResolver implements LocationResolver {

    static final String RELEASE_URL = "https://github.com/theexplorist/Buildah-Binaries/raw/master/buildah%20binary/%s";


    private String buildahName;
    private String runcName;
    private URL buildahUrl;
    private URL runcUrl;

    public UrlLocationResolver(String buildahUrl, String runcUrl) {
        this.buildahName = buildahUrl.substring(buildahUrl.lastIndexOf('/') + 1);
        this.runcName = runcUrl.substring(runcUrl.lastIndexOf('/') + 1);
        try {
            this.buildahUrl = new URL(buildahUrl);
            this.runcUrl = new URL(runcUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public UrlLocationResolver(String buildahBinary, String buildahVersion, String runcBinary, String runcVersion) {
        this.buildahName = buildahBinary;
        this.runcName = runcBinary;
        try {
            this.buildahUrl = new URL(String.format(RELEASE_URL, buildahVersion, buildahBinary));
            this.runcUrl = new URL(String.format(RELEASE_URL, runcVersion, runcBinary));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public InputStream loadBuildahResource() {
        try {
            return new BufferedInputStream(buildahUrl.openStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public InputStream loadRuncResource() {
        try {
            return new BufferedInputStream(runcUrl.openStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
