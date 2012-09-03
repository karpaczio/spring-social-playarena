package pl.playarena.api.impl;

import java.net.URI;

import org.apache.shindig.protocol.RestfulCollection;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import pl.nk.social.api.http.converter.json.TypeReferenceJacksonMessageConverter;
import pl.playarena.api.Playarena;
import pl.playarena.api.http.client.PlayarenaOAuthClientHttpRequestFactory;

public class PlayarenaTemplate extends AbstractOAuth2ApiBinding implements Playarena {

    protected static final String PLAYARENA_URL_BASE = "http://api.playarena.pl";
    private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
    public static final TypeReference<RestfulCollection<Profile>> PROFILES_TYPE_REFERENCE = new TypeReference<RestfulCollection<Profile>>() {
    };

    public Profile getCurrentUserProfile() {
        return this.getRestTemplate().getForObject(buildUri("/people/@me", EMPTY_PARAMETERS), Profile.class);
    }

    @SuppressWarnings("unchecked")
    public RestfulCollection<Profile> getCurrentUserFriends(Integer page, Integer itemsPerPage) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>(2);
        if (page != null) {
            params.add("page", page.toString());
        }
        if (itemsPerPage != null) {
            params.add("itemsPerPage", itemsPerPage.toString());
        }
        Object obj = this.getRestTemplate().getForObject(buildUri("/people/@friends", params),
                PROFILES_TYPE_REFERENCE.getClass());
        return (RestfulCollection<Profile>) obj;
    }

    public PlayarenaTemplate(String accessToken) {
        super(accessToken);
        ClientHttpRequestFactory factory = new PlayarenaOAuthClientHttpRequestFactory(getRestTemplate()
                .getRequestFactory(), accessToken);
        getRestTemplate().setRequestFactory(factory);

    }

    @Override
    protected MappingJacksonHttpMessageConverter getJsonMessageConverter() {
        MappingJacksonHttpMessageConverter converter = new TypeReferenceJacksonMessageConverter();
        converter.getObjectMapper().registerModule(new PlayarenaModule());
        return converter;
    }

    protected URI buildUri(String path, MultiValueMap<String, String> parameters) {
        return URIBuilder.fromUri(PLAYARENA_URL_BASE + path).queryParams(parameters).build();
    }

}
