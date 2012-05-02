package pl.playarena.api.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import pl.playarena.api.Playarena;
import pl.playarena.api.impl.PlayarenaTemplate;

public class PlayarenaServiceProvider extends AbstractOAuth2ServiceProvider<Playarena> {

    public PlayarenaServiceProvider(String clientKey, String clientSecret) {
        super(new OAuth2Template(clientKey, clientSecret, "http://api.playarena.pl",
                "http://api.playarena.pl/accessToken"));
    }

    @Override
    public Playarena getApi(String accessToken) {
        return new PlayarenaTemplate(accessToken);
    }

}
