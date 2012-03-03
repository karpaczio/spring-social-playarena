package pl.playarena.api.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import pl.playarena.api.Playarena;

public class PlayarenaConnectionFactory extends OAuth2ConnectionFactory<Playarena> {

    public PlayarenaConnectionFactory(String clientKey, String clientSecret) {

        super("playarena", new PlayarenaServiceProvider(clientKey, clientSecret), new PlayarenaApiAdapter());
    }

}
