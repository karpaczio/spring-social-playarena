package pl.playarena.api.http.client;

import java.io.IOException;
import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;

public class PlayarenaOAuthClientHttpRequestFactory implements ClientHttpRequestFactory {

    private ClientHttpRequestFactory delegate;
    private String accessToken;

    public PlayarenaOAuthClientHttpRequestFactory(ClientHttpRequestFactory delegate, String accessToken) {
        this.delegate = delegate;
        this.accessToken = accessToken;

    }

    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        String s = uri.toString();
        StringBuffer newUri = new StringBuffer(s);

        if (s.contains("?")) {
            newUri.append("&");
        } else {
            newUri.append("?");
        }
        newUri.append("access_token=").append(accessToken).append("&format=json");
        return delegate.createRequest(URI.create(newUri.toString()), httpMethod);
    }

}
