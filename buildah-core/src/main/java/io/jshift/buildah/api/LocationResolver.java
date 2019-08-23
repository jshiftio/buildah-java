package io.jshift.buildah.api;

import java.io.InputStream;

public interface LocationResolver {
    InputStream loadBuildahResource();

    InputStream loadRuncResource();

    String getBuildahName();

    String getRuncName();
}
