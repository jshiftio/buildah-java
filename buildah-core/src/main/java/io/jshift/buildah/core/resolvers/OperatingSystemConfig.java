package io.jshift.buildah.core.resolvers;

public class OperatingSystemConfig {

    private String osName;

    OperatingSystemConfig(String osName) {
        this.osName = osName;
    }

    String resolveBinary(String format) {
        final OperatingSystemFamily operativeSystem = OperatingSystemFamily.resolve(this.osName);

        String extension = "";

        return String.format(format, operativeSystem.getLabel(), extension);

    }
}
