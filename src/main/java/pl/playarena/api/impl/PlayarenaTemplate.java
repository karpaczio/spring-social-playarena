package pl.playarena.api.impl;

import java.net.URI;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import pl.playarena.api.Playarena;
import pl.playarena.api.http.client.PlayarenaOAuthClientHttpRequestFactory;

public class PlayarenaTemplate extends AbstractOAuth2ApiBinding implements Playarena {

    private static final String PLAYARENA_URL_BASE = "http://api.playarena.pl:8080/gospa-0.1";
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();

    public Profile getCurrentUserProfile() {
        return this.getRestTemplate().getForObject(buildUri("/people/@me", EMPTY_PARAMETERS),
                Profile.class);
    }

    public PlayarenaTemplate(String accessToken) {
        super(accessToken);
        ClientHttpRequestFactory factory = new PlayarenaOAuthClientHttpRequestFactory(getRestTemplate()
                .getRequestFactory(), accessToken);
        getRestTemplate().setRequestFactory(factory);

    }
    
    @Override
    protected MappingJacksonHttpMessageConverter getJsonMessageConverter() {
        MappingJacksonHttpMessageConverter converter = super.getJsonMessageConverter();
        converter.getObjectMapper().registerModule(new PlayarenaModule());
        return converter;
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(PLAYARENA_URL_BASE + path).queryParams(parameters).build();
    }

}
