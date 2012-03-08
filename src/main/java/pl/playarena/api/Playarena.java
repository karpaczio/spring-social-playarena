package pl.playarena.api;

import org.springframework.social.ApiBinding;

import pl.playarena.api.impl.Profile;

public interface Playarena extends ApiBinding {

    Profile getCurrentUserProfile();
}
