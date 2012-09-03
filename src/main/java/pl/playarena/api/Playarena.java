package pl.playarena.api;

import org.apache.shindig.protocol.RestfulCollection;
import org.springframework.social.ApiBinding;

import pl.playarena.api.impl.Profile;

public interface Playarena extends ApiBinding {

    Profile getCurrentUserProfile();
    
    RestfulCollection<Profile> getCurrentUserFriends(Integer page, Integer itemsPerPage);
}
