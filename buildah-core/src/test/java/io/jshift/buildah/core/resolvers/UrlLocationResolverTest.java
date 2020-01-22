package io.jshift.buildah.core.resolvers;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

public class UrlLocationResolverTest {

    public static final String REMOTE_URL1 = "https://gist.githubusercontent.com/lordofthejars/ac2823cec7831697d09444bbaa76cd50/raw/e4b43f1b6494766dfc635b5959af7730c1a58a93/deployment.yaml";
    public static final String REMOTE_URL2 = "https://gist.githubusercontent.com/lordofthejars/540c48f353ed9f6c21fd818d11c56633/raw/f47e19b6db5d132b93cc1433e9e22ff7be4a411e/Dockerfile";

    @Test
    public void should_download_content_from_url() throws IOException {

        assumeThat(
                isUpAndRunning(
                        REMOTE_URL1)
        ).isTrue();

        assumeThat(
                isUpAndRunning(
                        REMOTE_URL2)
        ).isTrue();

        // Given

        final UrlLocationResolver urlLocationResolver = new UrlLocationResolver(REMOTE_URL1, REMOTE_URL2);

        // When

        final InputStream inputStream1 = urlLocationResolver.loadBuildahResource();
        final InputStream inputStream2 = urlLocationResolver.loadRuncResource();

        // Then

        assertThat(inputStream1)
                .isNotNull();

        assertThat(inputStream2)
                .isNotNull();
    }

    private boolean isUpAndRunning(String url) throws IOException {
        final URL request = new URL(url);

        HttpURLConnection requestConnection =  ( HttpURLConnection )  request.openConnection ();
        requestConnection.setRequestMethod ("HEAD");
        requestConnection.connect () ;
        int code = requestConnection.getResponseCode() ;
        requestConnection.disconnect();
        return code >= HttpURLConnection.HTTP_OK && code < HttpURLConnection.HTTP_MULT_CHOICE;
    }
}
