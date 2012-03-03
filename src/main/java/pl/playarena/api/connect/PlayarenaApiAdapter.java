package pl.playarena.api.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import pl.playarena.api.Playarena;

public class PlayarenaApiAdapter implements ApiAdapter<Playarena> {

    public UserProfile fetchUserProfile(Playarena api) {
        // TODO Auto-generated method stub
        return null;
    }
    public void setConnectionValues(Playarena api, ConnectionValues values) {
        // TODO Auto-generated method stub
        
    }
    public boolean test(Playarena api) {
        // TODO Auto-generated method stub
        return false;
    }
    public void updateStatus(Playarena api, String message) {
        // TODO Auto-generated method stub
        
    }
}
