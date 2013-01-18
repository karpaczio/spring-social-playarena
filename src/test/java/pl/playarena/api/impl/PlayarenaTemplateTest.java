package pl.playarena.api.impl;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import org.apache.shindig.protocol.RestfulCollection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.test.client.MockRestServiceServer;
import org.springframework.util.LinkedMultiValueMap;

public class PlayarenaTemplateTest {

    protected MockRestServiceServer mockServer;
    protected PlayarenaTemplate playarena;

    @Before
    public void setup() {
        this.playarena = new PlayarenaTemplate("accesstoken");
        this.mockServer = MockRestServiceServer.createServer(this.playarena.getRestTemplate());
    }

    @Test
    public void getCurrentUserFriends() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        this.mockServer
                .expect(requestTo(this.playarena
                        .buildUri("/people/@friends", new LinkedMultiValueMap<String, String>())))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("friends"), responseHeaders));
        RestfulCollection<Profile> friends = this.playarena.getCurrentUserFriends(null, null);

        assertEquals(2, friends.getItemsPerPage());
        assertEquals(1, friends.getStartIndex());
        assertEquals(2, friends.getTotalResults());
        Profile friend = friends.getEntry().get(1);
        assertEquals("37844", friend.getId());
    }
    
    @Test
    public void getCurrentUserTeams() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        this.mockServer
                .expect(requestTo(this.playarena
                        .buildUri("/people/@teams", new LinkedMultiValueMap<String, String>())))
                .andExpect(method(GET)).andRespond(withResponse(jsonResource("teams"), responseHeaders));
        RestfulCollection<Team> teams = this.playarena.getCurrentUserTeams(null, null);

        assertEquals(2, teams.getItemsPerPage());
        assertEquals(0, teams.getStartIndex());
        assertEquals(2, teams.getTotalResults());
        Team team = teams.getEntry().get(0);
        assertEquals("6505", team.getId());
    }

    protected Resource jsonResource(String filename) {
        return new ClassPathResource("/" + filename + ".json", getClass());
    }
}
