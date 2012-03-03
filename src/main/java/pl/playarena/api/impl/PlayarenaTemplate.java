package pl.playarena.api.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import pl.playarena.api.Playarena;

public class PlayarenaTemplate extends AbstractOAuth2ApiBinding implements Playarena {

    private String accessToken;

    public PlayarenaTemplate(String accessToken) {
        super();
        this.accessToken = accessToken;
    }
}
