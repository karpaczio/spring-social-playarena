package pl.playarena.api;

import org.apache.shindig.protocol.RestfulCollection;
import org.springframework.social.ApiBinding;

import pl.playarena.api.impl.Profile;
import pl.playarena.api.impl.Team;
import pl.playarena.api.impl.MsgResponse;
import pl.playarena.api.impl.TeamMsgResponse;

public interface Playarena extends ApiBinding {

    Profile getCurrentUserProfile();
    
    RestfulCollection<Profile> getCurrentUserFriends(Integer page, Integer itemsPerPage);
    
    RestfulCollection<Team>  getCurrentUserTeams(Integer page, Integer itemsPerPage);
    
    MsgResponse sendPrivateMessege(Integer recipientId, String title, String body);
    
    TeamMsgResponse sendTeamMessege(Integer teamId, String title, String body);
    
    TeamMsgResponse sendAllTeamsMessege(String title, String body);
}
