package pl.playarena.api.connect;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import pl.playarena.api.Playarena;
import pl.playarena.api.impl.Profile;

public class PlayarenaApiAdapter implements ApiAdapter<Playarena> {

    private static final XLogger LOGGER = XLoggerFactory.getXLogger(PlayarenaApiAdapter.class);

    public UserProfile fetchUserProfile(Playarena api) {
        String name = api.getCurrentUserProfile().getName(); 
        return new UserProfileBuilder().setName(name).build();
    }

    public void setConnectionValues(Playarena api, ConnectionValues values) {
        Profile person = api.getCurrentUserProfile();
        values.setDisplayName(person.getName());
        values.setProviderUserId(person.getId());
        values.setProfileUrl(person.getProfileUrl());
        values.setImageUrl(person.getThumbnailUrl());

    }

    public boolean test(Playarena api) {
        try {
            api.getCurrentUserProfile();
            return true;
        } catch (ApiException e) {
            LOGGER.info("Connection test failed. ", e);
            return false;
        }
    }

    public void updateStatus(Playarena api, String message) {
        // TODO Auto-generated method stub

    }
}
